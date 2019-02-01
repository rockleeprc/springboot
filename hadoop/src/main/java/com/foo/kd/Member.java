package com.foo.kd;

import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class Member implements WritableComparable<Member> {

    private Long key;
    private Integer sex;
    private Double saveMoney;
    private Double consumerMoney;
    private Integer saveCount;
    private Integer consumerCount;
    private Integer source;
    private Long dateKey;
    private Long shopKey;


    @Override

    public int compareTo(Member shop) {
        return this.key.compareTo(shop.getKey());
    }

    @Override
    public void write(DataOutput out) throws IOException {
        out.writeLong(key);
        out.writeInt(sex);
        out.writeDouble(saveMoney);
        out.writeDouble(consumerMoney);
        out.writeInt(saveCount);
        out.writeInt(consumerCount);
        out.writeInt(source);
        out.writeLong(dateKey);
        out.writeLong(shopKey);
    }

    @Override
    public void readFields(DataInput in) throws IOException {
        key = in.readLong();
        sex = in.readInt();
        saveMoney = in.readDouble();
        consumerMoney = in.readDouble();
        saveCount = in.readInt();
        consumerCount = in.readInt();
        source = in.readInt();
        dateKey = in.readLong();
        shopKey = in.readLong();
    }

    @Override
    public String toString() {
        return key +
                Constant.TOSTRING_SEPERATOR + sex +
                Constant.TOSTRING_SEPERATOR + saveMoney +
                Constant.TOSTRING_SEPERATOR + consumerMoney +
                Constant.TOSTRING_SEPERATOR + saveCount +
                Constant.TOSTRING_SEPERATOR + consumerCount +
                Constant.TOSTRING_SEPERATOR + source +
                Constant.TOSTRING_SEPERATOR + dateKey +
                Constant.TOSTRING_SEPERATOR + shopKey;
    }

    public Long getKey() {
        return key;
    }

    public void setKey(Long key) {
        this.key = key;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Double getSaveMoney() {
        return saveMoney;
    }

    public void setSaveMoney(Double saveMoney) {
        this.saveMoney = saveMoney;
    }

    public Double getConsumerMoney() {
        return consumerMoney;
    }

    public void setConsumerMoney(Double consumerMoney) {
        this.consumerMoney = consumerMoney;
    }

    public Integer getSaveCount() {
        return saveCount;
    }

    public void setSaveCount(Integer saveCount) {
        this.saveCount = saveCount;
    }

    public Integer getConsumerCount() {
        return consumerCount;
    }

    public void setConsumerCount(Integer consumerCount) {
        this.consumerCount = consumerCount;
    }

    public Integer getSource() {
        return source;
    }

    public void setSource(Integer source) {
        this.source = source;
    }

    public Long getDateKey() {
        return dateKey;
    }

    public void setDateKey(Long dateKey) {
        this.dateKey = dateKey;
    }

    public Long getShopKey() {
        return shopKey;
    }

    public void setShopKey(Long shopKey) {
        this.shopKey = shopKey;
    }
}
