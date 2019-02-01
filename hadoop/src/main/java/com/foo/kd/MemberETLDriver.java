package com.foo.kd;

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

import java.io.File;
import java.io.IOException;

/**
 * 会员事实表清洗类，对应hive.f_member
 */
public class MemberETLDriver {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        args = new String[10];
        args[0] = "D:\\data\\rds\\shop\\20190103";
        args[1] = "D:\\data\\hive\\shop\\20190103";

        File outFile = new File(args[1]);
        if (outFile.exists()) {
            FileUtils.deleteDirectory(outFile);
        }

        Configuration configuration = new Configuration();
        Job job = Job.getInstance(configuration);

        job.setJarByClass(MemberETLDriver.class);
        job.setMapperClass(MemberETLMapper.class);
        job.setMapOutputKeyClass(Shop.class);
        job.setMapOutputValueClass(NullWritable.class);

        job.setNumReduceTasks(0);

        FileInputFormat.setInputPaths(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        boolean completion = job.waitForCompletion(true);
        System.exit(completion ? 0 : 1);
    }


    public static class MemberETLMapper extends Mapper<LongWritable, Text, Member, NullWritable> {
        private Member k = new Member();
        private NullWritable v = NullWritable.get();


        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            String line = value.toString();

            String[] fields = line.split("\\0001");
            if (fields.length != 30) {
                return;
            }

            //校验数据
            Long id = ValidataUtil.valiLong(fields[1]);//三方 member_id
            Integer sex = ValidataUtil.valiInt(fields[2]);
            Double saveMoney = ValidataUtil.valiDouble(fields[10]);
            Double consumerMoney = ValidataUtil.valiDouble(fields[10]);
            Integer saveCount = ValidataUtil.valiInt(fields[12]);
            Integer consumerCount = ValidataUtil.valiInt(fields[13]);
            Integer source = ValidataUtil.valiInt(fields[14]);
            Long dateKey = ValidataUtil.valiTimestamp(fields[30]);
            Long shopKey = ValidataUtil.valiLong(fields[5]);

            //封装数据
            k.setKey(id);
            k.setSex(sex);
            k.setSaveMoney(saveMoney);
            k.setConsumerMoney(consumerMoney);
            k.setSaveCount(saveCount);
            k.setConsumerCount(consumerCount);
            k.setSource(source);
            k.setDateKey(dateKey);
            k.setShopKey(shopKey);

            context.write(k, v);
        }
    }
}
