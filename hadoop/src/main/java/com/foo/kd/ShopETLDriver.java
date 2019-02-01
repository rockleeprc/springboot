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
 * 门店事实表清洗类，对应hive.f_shop
 */
public class ShopETLDriver {
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

        job.setJarByClass(ShopETLDriver.class);
        job.setMapperClass(ShopETLDriver.ShopETLMapper.class);
        job.setMapOutputKeyClass(Shop.class);
        job.setMapOutputValueClass(NullWritable.class);

        job.setNumReduceTasks(0);

        FileInputFormat.setInputPaths(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        boolean completion = job.waitForCompletion(true);
        System.exit(completion ? 0 : 1);
    }


    public static class ShopETLMapper extends Mapper<LongWritable, Text, Shop, NullWritable> {
        private Shop k = new Shop();
        private NullWritable v = NullWritable.get();


        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            String line = value.toString();

            String[] fields = line.split("\\0001");
            if (fields.length != 29) {
                return;
            }

            //校验数据
            Long id = ValidataUtil.valiLong(fields[0]);// data_shop_id
            Long province = ValidataUtil.valiLong(fields[2]);
            Long city = ValidataUtil.valiLong(fields[3]);
            Long district = ValidataUtil.valiLong(fields[4]);
            Integer source = ValidataUtil.valiInt(fields[25]);
            Long company = ValidataUtil.valiLong(fields[18]);
            Long group = ValidataUtil.valiLong(fields[20]);
            Long brand = ValidataUtil.valiLong(fields[24]);
            Long dateKey = ValidataUtil.valiTimestamp(fields[15]);

            //封装数据
            k.setKey(id);
            k.setProvinceKey(province);
            k.setCityKey(city);
            k.setDistrictKey(district);
            k.setSource(source);
            k.setBrandKey(brand);
            k.setGroupKey(group);
            k.setCompanyKey(company);
            k.setDateKey(dateKey);

            context.write(k, v);
        }
    }
}
