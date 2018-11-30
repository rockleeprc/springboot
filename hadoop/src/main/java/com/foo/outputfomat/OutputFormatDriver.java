package com.foo.outputfomat;

import com.foo.util.FileUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.File;
import java.io.IOException;
import java.util.StringTokenizer;

public class OutputFormatDriver {

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        args = new String[10];
        args[0] = "F:\\data\\outputformat\\input";
        args[1] = "F:\\data\\outputformat\\output";

        File outFile = new File(args[1]);
        if (outFile.exists()) {
            FileUtils.deleteDirectory(outFile);
        }

        Configuration entries = new Configuration();
        Job job = Job.getInstance(entries);

        job.setJarByClass(OutputFormatDriver.class);
        job.setMapperClass(FormatMapper.class);
        job.setReducerClass(FormatReducer.class);
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(NullWritable.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(NullWritable.class);

        job.setOutputFormatClass(FilterOutputForamt.class);

        FileInputFormat.setInputPaths(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        boolean completion = job.waitForCompletion(true);
        System.exit(completion ? 0 : 1);

    }

    public static class FormatMapper extends Mapper<LongWritable, Text, Text, NullWritable> {
        private Text mapkey = new Text();


        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            StringTokenizer tokenizer = new StringTokenizer(value.toString());
            while (tokenizer.hasMoreElements()) {
                String token = tokenizer.nextToken();
                mapkey.set(token);
                context.write(mapkey, NullWritable.get());
            }
        }
    }

    public static class FormatReducer extends Reducer<Text, NullWritable, Text, NullWritable> {
        private Text word = new Text();
        private String separator = "\r\n";

        @Override
        protected void reduce(Text key, Iterable<NullWritable> values, Context context) throws IOException, InterruptedException {
            String str = key.toString() + separator;
            word.set(str);
            for (NullWritable value : values) {
                context.write(word, NullWritable.get());
            }
        }
    }
}
