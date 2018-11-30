package com.foo.mapjoin;

import com.foo.util.FileUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

public class MapJoinDriver {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException, URISyntaxException {
        args = new String[]{"F:\\data\\join\\input", "F:\\data\\join\\output"};

        File outFile = new File(args[1]);
        if (outFile.exists()) {
            FileUtils.deleteDirectory(outFile);
        }

        Configuration configuration = new Configuration();
        Job job = Job.getInstance(configuration);

        job.setJarByClass(MapJoinDriver.class);
        job.setMapperClass(MapJoinDriver.JoinMap.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(NullWritable.class);

        job.addCacheFile(new URI("file:///F:/data/join/input/pd.txt"));
        job.setNumReduceTasks(0);//mapjoin不需要reduce

        FileInputFormat.setInputPaths(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        boolean completion = job.waitForCompletion(true);
        System.exit(completion ? 0 : 1);
    }

    public static class JoinMap extends Mapper<LongWritable, Text, Text, NullWritable> {

        private Map<String, String> prod = new HashMap<>();

        @Override
        protected void setup(Context context) throws IOException, InterruptedException {
            URI[] uris = context.getCacheFiles();
            String filePath = uris[0].getPath().toString();
            try (BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(filePath)));) {
                String line = null;
                while ((line = bufferedReader.readLine()) != null) {
                    String[] fileds = line.split("\t");
                    prod.put(fileds[0], fileds[1]);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        private Text k = new Text();

        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            //1001	01	1
            //1002	02	2

            String[] fields = value.toString().split("\t");
            if (fields.length != 3) {
                return;
            }

            String prodName = prod.get(fields[1]);
            if (prodName == null) {
                context.getCounter("mismatch", "false").increment(1);
            } else {
                context.getCounter("match", "true").increment(1);
            }

            k.set(value.toString() + "\t" + prodName);
            context.write(k, NullWritable.get());
        }
    }
}
