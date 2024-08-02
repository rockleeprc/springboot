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
 * 订单事实表清洗类，对应hive.f_order
 */
public class OrderETLDriver {
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

        job.setJarByClass(OrderETLDriver.class);
        job.setMapperClass(OrderETLMapper.class);
        job.setMapOutputKeyClass(Shop.class);
        job.setMapOutputValueClass(NullWritable.class);

        job.setNumReduceTasks(0);

        FileInputFormat.setInputPaths(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        boolean completion = job.waitForCompletion(true);
        System.exit(completion ? 0 : 1);
    }


    public static class OrderETLMapper extends Mapper<LongWritable, Text, Order, NullWritable> {
        private Order k = new Order();
        private NullWritable v = NullWritable.get();


        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            String line = value.toString();

            String[] fields = line.split("\\0001");
            if (fields.length != 37) {
                return;
            }

            //校验数据
            Long orderNo = ValidataUtil.valiLong(fields[15]);//三方订单id
            String deviceId = ValidataUtil.valiString(fields[24]);
            String clientIp = ValidataUtil.valiString(fields[25]);
            Double totalPrice = ValidataUtil.valiDouble(fields[28]);
            Double payPrice = ValidataUtil.valiDouble(fields[29]);
            Double couponPrice = ValidataUtil.valiDouble(fields[30]);
            Double refundPrice = ValidataUtil.valiDouble(fields[31]);
            Double receivedPrice = ValidataUtil.valiDouble(fields[32]);
            Integer source = ValidataUtil.valiInt(fields[33]);
            Long shopKey = ValidataUtil.valiLong(fields[4]);
            Long brandKey = ValidataUtil.valiLong(fields[14]);
            Long companyKey = ValidataUtil.valiLong(fields[8]);
            Long groupKey = ValidataUtil.valiLong(fields[10]);
            Long dateKey = ValidataUtil.valiTimestamp(fields[1]);
            Long memberKey = ValidataUtil.valiLong(fields[16]);

            //封装数据
            k.setKey(orderNo);
            k.setDeviceId(deviceId);
            k.setClientIp(clientIp);
            k.setTotalPrice(totalPrice);
            k.setPayPrice(payPrice);
            k.setCouponPrice(couponPrice);
            k.setRefundPrice(refundPrice);
            k.setReceivedPrice(receivedPrice);
            k.setSource(source);
            k.setShopKey(shopKey);
            k.setBrandKey(brandKey);
            k.setCompanyKey(companyKey);
            k.setGroupKey(groupKey);
            k.setDateKey(dateKey);
            k.setMemberKey(memberKey);

            context.write(k, v);
        }
    }
}
