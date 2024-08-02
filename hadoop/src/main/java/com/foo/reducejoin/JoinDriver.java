package com.foo.reducejoin;

import com.foo.util.FileUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class JoinDriver {

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        args = new String[10];
        args[0] = "F:\\data\\join\\input";
        args[1] = "F:\\data\\join\\output";

        File outFile = new File(args[1]);
        if (outFile.exists()) {
            FileUtils.deleteDirectory(outFile);
        }

        Configuration configuration = new Configuration();
        Job job = Job.getInstance(configuration);

        job.setJarByClass(JoinDriver.class);
        job.setMapperClass(JoinMapper.class);
        job.setReducerClass(JoinReduce.class);

        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(JoinBean.class);
        job.setOutputKeyClass(JoinBean.class);
        job.setOutputValueClass(NullWritable.class);

        FileInputFormat.setInputPaths(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        boolean completion = job.waitForCompletion(true);
        System.exit(completion ? 0 : 1);
    }

    public static class JoinMapper extends Mapper<LongWritable, Text, Text, JoinBean> {
        private String fileName;
        private Text k = new Text();
        private JoinBean v = new JoinBean();


        @Override
        protected void setup(Context context) throws IOException, InterruptedException {
            FileSplit fileSplit = (FileSplit) context.getInputSplit();
            fileName = fileSplit.getPath().getName();
        }

        /**
         * map输出的key一定是外键，只有key相同了才能进入同一个reduce里
         *
         * @param key
         * @param value
         * @param context
         * @throws IOException
         * @throws InterruptedException
         */
        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

            String line = value.toString();
            String[] fields = line.split("\t");

            if ("order.txt".startsWith(fileName)) {
//                1001	01	1
                v.setOrderId(fields[0]);
                v.setPid(fields[1]);
                v.setAmount(Integer.parseInt(fields[2]));

                v.setFlag("order");
                v.setpName("");
            }
            if ("pd.txt".startsWith(fileName)) {
//                01	小米
                v.setPid(fields[0]);
                v.setpName(fields[1]);

                v.setFlag("pd");
                v.setOrderId("");
                v.setAmount(0);
            }
            k.set(v.getPid());
            context.write(k, v);
        }
    }

    public static class JoinReduce extends Reducer<Text, JoinBean, JoinBean, NullWritable> {

        @Override
        protected void reduce(Text key, Iterable<JoinBean> values, Context context) throws IOException, InterruptedException {
            List<JoinBean> list = new ArrayList<>();
            JoinBean joinBean = new JoinBean();

            for (JoinBean jb : values) {
                if ("order".equals(jb.getFlag())) {
                    JoinBean temp = new JoinBean();
                    try {
                        BeanUtils.copyProperties(temp, jb);
                        list.add(temp);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        BeanUtils.copyProperties(joinBean, jb);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
            }

            for (JoinBean jb : list) {
                jb.setpName(joinBean.getpName());
                context.write(jb, NullWritable.get());
            }
        }

    }
}
