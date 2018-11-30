package com.foo.grouping;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

/**
 * 分组排序的前提是数据必须按照某种方式已经有序的存在
 */
public class OrderGroupingComparator extends WritableComparator {
    public OrderGroupingComparator() {
        super(Order.class, true);
    }


    @Override
    public int compare(WritableComparable a, WritableComparable b) {
        Order orderA = (Order) a;
        Order orderB = (Order) b;
        return orderA.getOrderId() - orderB.getOrderId();
    }
}
