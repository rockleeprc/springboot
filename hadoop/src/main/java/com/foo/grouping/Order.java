package com.foo.grouping;

import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class Order implements WritableComparable<Order> {

    private int orderId;
    private double price;

    @Override
    public String toString() {
        return orderId + "\t" + price;
    }

    /**
     * orderId升序
     * price降序
     *
     * @param o
     * @return
     */
    @Override
    public int compareTo(Order o) {
        if (this.orderId > o.getOrderId()) {
            return 1;
        } else if (this.orderId < o.getOrderId()) {
            return -1;
        } else {
            if (price > o.getPrice()) {
                return -1;
            } else if (price < o.getPrice()) {
                return 1;
            }
        }
        return 0;
    }

    @Override
    public void write(DataOutput out) throws IOException {
        out.writeInt(orderId);
        out.writeDouble(price);
    }

    @Override
    public void readFields(DataInput in) throws IOException {
        orderId = in.readInt();
        price = in.readDouble();
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
