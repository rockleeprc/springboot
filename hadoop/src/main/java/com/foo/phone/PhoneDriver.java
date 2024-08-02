package com.foo.phone;

import com.foo.util.FileUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.File;
import java.io.IOException;

public class PhoneDriver {

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        args = new String[10];
        args[0] = "F:\\data\\phone\\input";
        args[1] = "F:\\data\\phone\\output";

        File outFile = new File(args[1]);
        if (outFile.exists()) {
            FileUtils.deleteDirectory(outFile);
        }

        Configuration configuration = new Configuration();
        Job job = Job.getInstance(configuration);

        job.setJarByClass(PhoneDriver.class);
        job.setMapperClass(PhoneMapper.class);
        job.setReducerClass(PhoneReducer.class);

        job.setMapOutputKeyClass(LongWritable.class);
        job.setMapOutputValueClass(PhoneFlow.class);
        job.setOutputKeyClass(LongWritable.class);
        job.setOutputValueClass(PhoneFlow.class);

        FileInputFormat.setInputPaths(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        boolean completion = job.waitForCompletion(true);
        System.exit(completion ? 0 : 1);

    }

    public static class PhoneMapper extends Mapper<LongWritable, Text, LongWritable, PhoneFlow> {
        private LongWritable mapKey = new LongWritable();
        private PhoneFlow mapValue = new PhoneFlow();

        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            String line = value.toString();
            String[] elements = line.split("\t");

            if (elements.length != 7)
                return;

            mapKey.set(Long.parseLong(elements[1]));
            mapValue.set(Long.parseLong(elements[4]), Long.parseLong(elements[5]));
            context.write(mapKey, mapValue);
        }
    }

    public static class PhoneReducer extends Reducer<LongWritable, PhoneFlow, LongWritable, PhoneFlow> {
        private PhoneFlow reduceValue = new PhoneFlow();

        @Override
        protected void reduce(LongWritable key, Iterable<PhoneFlow> values, Context context) throws IOException, InterruptedException {
            long upFlow = 0;
            long downFlow = 0;

            for (PhoneFlow pf : values) {
                upFlow += pf.getUpFlow();
                downFlow += pf.getDownFlow();
            }
            reduceValue.set(upFlow, downFlow);
            context.write(key, reduceValue);
        }

    }
}
