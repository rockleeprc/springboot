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
 * 菜品事实表清洗类，对应hive.f_dish
 */
public class DishETLDriver {
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

        job.setJarByClass(DishETLDriver.class);
        job.setMapperClass(DishETLMapper.class);
        job.setMapOutputKeyClass(Shop.class);
        job.setMapOutputValueClass(NullWritable.class);

        job.setNumReduceTasks(0);

        FileInputFormat.setInputPaths(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        boolean completion = job.waitForCompletion(true);
        System.exit(completion ? 0 : 1);
    }


    public static class DishETLMapper extends Mapper<LongWritable, Text, Dish, NullWritable> {
        private Dish k = new Dish();
        private NullWritable v = NullWritable.get();


        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            String line = value.toString();

            String[] fields = line.split("\\0001");
            if (fields.length != 34) {
                return;
            }

            //校验数据
            Long id = ValidataUtil.valiLong(fields[18]);
            Integer source = ValidataUtil.valiInt(fields[30]);
            Long shopKey = ValidataUtil.valiLong(fields[4]);
            Long companyKey = ValidataUtil.valiLong(fields[9]);
            Long groupKey = ValidataUtil.valiLong(fields[10]);
            Long brandKey = ValidataUtil.valiLong(fields[14]);
            Double payPrice = ValidataUtil.valiDouble(fields[15]);
            Double couponPrice = ValidataUtil.valiDouble(fields[16]);
            Double originPrice = ValidataUtil.valiDouble(fields[18]);
            Integer dishNum = ValidataUtil.valiInt(fields[25]);
            Double originalTotalPrice = ValidataUtil.valiDouble(fields[26]);
            Double payTotalPrice = ValidataUtil.valiDouble(fields[27]);
            Double couponTotalPrice = ValidataUtil.valiDouble(fields[28]);
            Long dateKey = ValidataUtil.valiTimestamp(fields[1]);

            //封装数据
            k.setKey(id);
            k.setSource(source);
            k.setShopKey(shopKey);
            k.setCompanyKey(companyKey);
            k.setGroupKey(groupKey);
            k.setBrandKey(brandKey);
            k.setPayPrice(payPrice);
            k.setCouponPrice(couponPrice);
            k.setOriginPrice(originPrice);
            k.setDishNum(dishNum);
            k.setOriginalTotalPrice(originalTotalPrice);
            k.setPayTotalPrice(payTotalPrice);
            k.setCouponTotalPrice(couponTotalPrice);
            k.setDateKey(dateKey);

            context.write(k, v);
        }
    }
}
