package com.foo.kd;

import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class Shop implements WritableComparable<Shop> {
    private Long key;
    private Long companyKey;
    private Long groupKey;
    private Long brandKey;
    private Integer source;
    private Long provinceKey;
    private Long cityKey;
    private Long districtKey;
    private Long dateKey;

    @Override
    public int compareTo(Shop shop) {
        return this.key.compareTo(shop.getKey());
    }

    @Override
    public void write(DataOutput out) throws IOException {
        out.writeLong(key);
        out.writeLong(companyKey);
        out.writeLong(groupKey);
        out.writeLong(brandKey);
        out.writeInt(source);
        out.writeLong(provinceKey);
        out.writeLong(cityKey);
        out.writeLong(districtKey);
        out.writeLong(dateKey);
    }

    @Override
    public void readFields(DataInput in) throws IOException {
        key = in.readLong();
        companyKey = in.readLong();
        groupKey = in.readLong();
        brandKey = in.readLong();
        source = in.readInt();
        provinceKey = in.readLong();
        cityKey = in.readLong();
        districtKey = in.readLong();
        dateKey = in.readLong();
    }

    @Override
    public String toString() {
        return key +
                Constant.TOSTRING_SEPERATOR + groupKey +
                Constant.TOSTRING_SEPERATOR + companyKey +
                Constant.TOSTRING_SEPERATOR + brandKey +
                Constant.TOSTRING_SEPERATOR + source +
                Constant.TOSTRING_SEPERATOR + provinceKey +
                Constant.TOSTRING_SEPERATOR + cityKey +
                Constant.TOSTRING_SEPERATOR + districtKey +
                Constant.TOSTRING_SEPERATOR + dateKey;
    }

    public Long getKey() {
        return key;
    }

    public void setKey(Long key) {
        this.key = key;
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

    public Integer getSource() {
        return source;
    }

    public void setSource(Integer source) {
        this.source = source;
    }

    public Long getProvinceKey() {
        return provinceKey;
    }

    public void setProvinceKey(Long provinceKey) {
        this.provinceKey = provinceKey;
    }

    public Long getCityKey() {
        return cityKey;
    }

    public void setCityKey(Long cityKey) {
        this.cityKey = cityKey;
    }

    public Long getDistrictKey() {
        return districtKey;
    }

    public void setDistrictKey(Long districtKey) {
        this.districtKey = districtKey;
    }

    public Long getDateKey() {
        return dateKey;
    }

    public void setDateKey(Long dateKey) {
        this.dateKey = dateKey;
    }
}
