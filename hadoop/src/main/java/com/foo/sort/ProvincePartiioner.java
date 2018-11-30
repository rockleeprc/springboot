package com.foo.sort;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.mapreduce.Partitioner;

/**
 * Partitioner的泛型是Mapper的输入出
 */
public class ProvincePartiioner extends Partitioner<PhoneFlowSort, LongWritable> {


    @Override
    public int getPartition(PhoneFlowSort key, LongWritable value, int numPartitions) {
        int partition = 2;//默认其它
        String phone = String.valueOf(value.get());
        String phoneArea = phone.substring(0, 3);
        if ("135".equals(phoneArea)) {
            partition = 0;
        } else if ("136".equals(phoneArea)) {
            partition = 1;
        }

        return partition;
    }
}
