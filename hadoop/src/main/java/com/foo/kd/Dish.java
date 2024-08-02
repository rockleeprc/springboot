package com.foo.kd;

import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class Dish implements WritableComparable<Dish> {

    private Long key;
    private Integer source;
    private Long shopKey;
    private Long companyKey;
    private Long groupKey;
    private Long brandKey;
    private Double payPrice;
    private Double couponPrice;
    private Double originPrice;
    private Integer dishNum;
    private Double originalTotalPrice;
    private Double payTotalPrice;
    private Double couponTotalPrice;
    private Long dateKey;


    @Override

    public int compareTo(Dish shop) {
        return this.key.compareTo(shop.getKey());
    }

    @Override
    public void write(DataOutput out) throws IOException {
        out.writeLong(key);
        out.writeInt(source);
        out.writeLong(shopKey);
        out.writeLong(companyKey);
        out.writeLong(groupKey);
        out.writeLong(brandKey);
        out.writeDouble(payPrice);
        out.writeDouble(originPrice);
        out.writeInt(dishNum);
        out.writeDouble(originalTotalPrice);
        out.writeDouble(payTotalPrice);
        out.writeDouble(couponTotalPrice);
        out.writeLong(dateKey);
    }

    @Override
    public void readFields(DataInput in) throws IOException {
        key = in.readLong();
        source = in.readInt();
        shopKey = in.readLong();
        companyKey = in.readLong();
        groupKey = in.readLong();
        brandKey = in.readLong();
        payPrice = in.readDouble();
        couponPrice = in.readDouble();
        originPrice = in.readDouble();
        dishNum = in.readInt();
        originalTotalPrice = in.readDouble();
        payTotalPrice = in.readDouble();
        couponTotalPrice = in.readDouble();
        dateKey = in.readLong();
    }

    @Override
    public String toString() {
        return key +
                Constant.TOSTRING_SEPERATOR + source +
                Constant.TOSTRING_SEPERATOR + shopKey +
                Constant.TOSTRING_SEPERATOR + companyKey +
                Constant.TOSTRING_SEPERATOR + groupKey +
                Constant.TOSTRING_SEPERATOR + brandKey +
                Constant.TOSTRING_SEPERATOR + payPrice +
                Constant.TOSTRING_SEPERATOR + couponPrice +
                Constant.TOSTRING_SEPERATOR + originPrice +
                Constant.TOSTRING_SEPERATOR + dishNum +
                Constant.TOSTRING_SEPERATOR + originalTotalPrice +
                Constant.TOSTRING_SEPERATOR + payTotalPrice +
                Constant.TOSTRING_SEPERATOR + couponTotalPrice +
                Constant.TOSTRING_SEPERATOR + dateKey;
    }

    public Long getKey() {
        return key;
    }

    public void setKey(Long key) {
        this.key = key;
    }

    public Integer getSource() {
        return source;
    }

    public void setSource(Integer source) {
        this.source = source;
    }

    public Long getShopKey() {
        return shopKey;
    }

    public void setShopKey(Long shopKey) {
        this.shopKey = shopKey;
    }

    public Long getCompanyKey() {
        return companyKey;
    }

    public void setCompanyKey(Long companyKey) {
        this.companyKey = companyKey;
    }

    public Long getGroupKey() {
        return groupKey;
    }

    public void setGroupKey(Long groupKey) {
        this.groupKey = groupKey;
    }

    public Long getBrandKey() {
        return brandKey;
    }

    public void setBrandKey(Long brandKey) {
        this.brandKey = brandKey;
    }

    public Double getPayPrice() {
        return payPrice;
    }

    public void setPayPrice(Double payPrice) {
        this.payPrice = payPrice;
    }

    public Double getCouponPrice() {
        return couponPrice;
    }

    public void setCouponPrice(Double couponPrice) {
        this.couponPrice = couponPrice;
    }

    public Double getOriginPrice() {
        return originPrice;
    }

    public void setOriginPrice(Double originPrice) {
        this.originPrice = originPrice;
    }

    public Integer getDishNum() {
        return dishNum;
    }

    public void setDishNum(Integer dishNum) {
        this.dishNum = dishNum;
    }

    public Double getOriginalTotalPrice() {
        return originalTotalPrice;
    }

    public void setOriginalTotalPrice(Double originalTotalPrice) {
        this.originalTotalPrice = originalTotalPrice;
    }

    public Double getPayTotalPrice() {
        return payTotalPrice;
    }

    public void setPayTotalPrice(Double payTotalPrice) {
        this.payTotalPrice = payTotalPrice;
    }

    public Double getCouponTotalPrice() {
        return couponTotalPrice;
    }

    public void setCouponTotalPrice(Double couponTotalPrice) {
        this.couponTotalPrice = couponTotalPrice;
    }

    public Long getDateKey() {
        return dateKey;
    }

    public void setDateKey(Long dateKey) {
        this.dateKey = dateKey;
    }
}
