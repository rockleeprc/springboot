package com.foo.kd;

import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.math.BigDecimal;

public class Order implements WritableComparable<Order> {

    private Long key;
    private String deviceId;
    private String clientIp;
    private Double totalPrice;
    private Double payPrice;
    private Double couponPrice;
    private Double refundPrice;
    private Double receivedPrice;
    private Integer source;
    private Long shopKey;
    private Long brandKey;
    private Long companyKey;
    private Long groupKey;
    private Long dateKey;
    private Long memberKey;


    @Override

    public int compareTo(Order shop) {
        return this.key.compareTo(shop.getKey());
    }

    @Override
    public void write(DataOutput out) throws IOException {
        out.writeLong(key);
        out.writeUTF(deviceId);
        out.writeUTF(clientIp);
        out.writeDouble(totalPrice);
        out.writeDouble(payPrice);
        out.writeDouble(couponPrice);
        out.writeDouble(refundPrice);
        out.writeDouble(receivedPrice);
        out.writeInt(source);
        out.writeLong(shopKey);
        out.writeLong(brandKey);
        out.writeLong(companyKey);
        out.writeLong(groupKey);
        out.writeLong(dateKey);
        out.writeLong(memberKey);
    }

    @Override
    public void readFields(DataInput in) throws IOException {
        key = in.readLong();
        deviceId = in.readUTF();
        clientIp = in.readUTF();
        totalPrice = in.readDouble();
        payPrice = in.readDouble();
        couponPrice = in.readDouble();
        refundPrice = in.readDouble();
        receivedPrice = in.readDouble();
        source = in.readInt();
        shopKey = in.readLong();
        brandKey = in.readLong();
        companyKey = in.readLong();
        groupKey = in.readLong();
        dateKey = in.readLong();
        memberKey = in.readLong();
    }

    @Override
    public String toString() {
        return key +
                Constant.TOSTRING_SEPERATOR + deviceId +
                Constant.TOSTRING_SEPERATOR + clientIp +
                Constant.TOSTRING_SEPERATOR + totalPrice +
                Constant.TOSTRING_SEPERATOR + payPrice +
                Constant.TOSTRING_SEPERATOR + couponPrice +
                Constant.TOSTRING_SEPERATOR + refundPrice +
                Constant.TOSTRING_SEPERATOR + receivedPrice +
                Constant.TOSTRING_SEPERATOR + source +
                Constant.TOSTRING_SEPERATOR + shopKey +
                Constant.TOSTRING_SEPERATOR + brandKey +
                Constant.TOSTRING_SEPERATOR + companyKey +
                Constant.TOSTRING_SEPERATOR + groupKey +
                Constant.TOSTRING_SEPERATOR + dateKey +
                Constant.TOSTRING_SEPERATOR + memberKey;
    }

    public Long getKey() {
        return key;
    }

    public void setKey(Long key) {
        this.key = key;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getClientIp() {
        return clientIp;
    }

    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
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

    public Double getRefundPrice() {
        return refundPrice;
    }

    public void setRefundPrice(Double refundPrice) {
        this.refundPrice = refundPrice;
    }

    public Double getReceivedPrice() {
        return receivedPrice;
    }

    public void setReceivedPrice(Double receivedPrice) {
        this.receivedPrice = receivedPrice;
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

    public Long getBrandKey() {
        return brandKey;
    }

    public void setBrandKey(Long brandKey) {
        this.brandKey = brandKey;
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

    public Long getDateKey() {
        return dateKey;
    }

    public void setDateKey(Long dateKey) {
        this.dateKey = dateKey;
    }

    public Long getMemberKey() {
        return memberKey;
    }

    public void setMemberKey(Long memberKey) {
        this.memberKey = memberKey;
    }
}
