package com.foo.grouping;

import com.foo.phone.PhoneDriver;
import com.foo.phone.PhoneFlow;
import com.foo.util.FileUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
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

public class ProdDriver {

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        args = new String[10];
        args[0] = "F:\\data\\prod\\input";
        args[1] = "F:\\data\\prod\\output";

        File outFile = new File(args[1]);
        if (outFile.exists()) {
            FileUtils.deleteDirectory(outFile);
        }

        Configuration configuration = new Configuration();
        Job job = Job.getInstance(configuration);

        job.setJarByClass(ProdDriver.class);
        job.setMapperClass(ProdMapper.class);
        job.setReducerClass(ProdReduce.class);

        job.setMapOutputKeyClass(Order.class);
        job.setMapOutputValueClass(NullWritable.class);
        job.setOutputKeyClass(Order.class);
        job.setOutputValueClass(NullWritable.class);

        job.setGroupingComparatorClass(OrderGroupingComparator.class);

        FileInputFormat.setInputPaths(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        boolean completion = job.waitForCompletion(true);
        System.exit(completion ? 0 : 1);
    }

    public static class ProdMapper extends Mapper<LongWritable, Text, Order, NullWritable> {
        private Order order = new Order();

        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            String line = value.toString();
            String[] fields = line.split("\t");

            order.setOrderId(Integer.parseInt(fields[0]));
            order.setPrice(Double.parseDouble(fields[2]));

            context.write(order, NullWritable.get());
        }
    }

    public static class ProdReduce extends Reducer<Order, NullWritable, Order, NullWritable> {
        @Override
        protected void reduce(Order key, Iterable<NullWritable> values, Context context) throws IOException, InterruptedException {
            //TODO 循环values时key为什么会变化

            for (NullWritable nw : values) {
                System.out.println("-----------------------" + key);
                context.write(key, NullWritable.get());
            }
            System.out.println("============================");
        }
    }
}
