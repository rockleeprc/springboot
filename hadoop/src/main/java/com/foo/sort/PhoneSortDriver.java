package com.foo.sort;

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

/**
 * Bean作为Map的key输出排序，需要实现WritableComparable
 * <p>
 * Map输出Key默认字典顺序
 * <p>
 * Partitioner Reduce输出个数     Partitioner Reduce输出个数
 * Partitioner 数量从0开始 part-r-00000、part-r-00001、part-r-00002
 * <p>
 * Combiner 不适合求平均值，适合汇总
 * input-->MapTask-->缓冲区排序-->Partitioner-->Combiner-->溢写-->ReduceTask-->output
 */
public class PhoneSortDriver {

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        args = new String[10];
        args[0] = "F:\\data\\phone\\output";
        args[1] = "F:\\data\\phone\\outputsort";

        File outFile = new File(args[1]);
        if (outFile.exists()) {
            FileUtils.deleteDirectory(outFile);
        }

        Configuration configuration = new Configuration();
        Job job = Job.getInstance(configuration);

        job.setJarByClass(PhoneSortDriver.class);
        job.setMapperClass(PhoneSortMapper.class);
        job.setReducerClass(PhoneSortReducer.class);

        job.setMapOutputKeyClass(PhoneFlowSort.class);
        job.setMapOutputValueClass(LongWritable.class);
        job.setOutputKeyClass(LongWritable.class);
        job.setOutputValueClass(PhoneFlowSort.class);

//        job.setPartitionerClass(ProvincePartiioner.class);
//        job.setNumReduceTasks(3);

        FileInputFormat.setInputPaths(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        boolean completion = job.waitForCompletion(true);
        System.exit(completion ? 0 : 1);

    }

    /**
     * PhoneFlowSort最为key会排序
     */
    public static class PhoneSortMapper extends Mapper<LongWritable, Text, PhoneFlowSort, LongWritable> {
        private LongWritable mapValue = new LongWritable();
        private PhoneFlowSort mapKey = new PhoneFlowSort();

        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            //84188413	4116	1432	5548
            String line = value.toString();
            String[] elements = line.split("\t");

            if (elements.length != 4)
                return;

            mapValue.set(Long.parseLong(elements[0]));
            mapKey.set(Long.parseLong(elements[1]), Long.parseLong(elements[2]));
            context.write(mapKey, mapValue);
        }
    }

    public static class PhoneSortReducer extends Reducer<PhoneFlowSort, LongWritable, LongWritable, PhoneFlowSort> {

        @Override
        protected void reduce(PhoneFlowSort key, Iterable<LongWritable> values, Context context) throws IOException, InterruptedException {
            for (LongWritable value : values) {
                context.write(value, key);
            }
        }
    }
}
