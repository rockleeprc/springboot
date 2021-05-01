package cn.kd.entity.view;

import cn.kd.entity.WorkloadAdviser;

import static cn.kd.util.ViewUtils.calculateAverageValue;


public class WorkloadAdviserView extends WorkloadAdviser {
    // 平均跟进次数：跟进次数/跟进客户数 指标格式：4.33
    private String avgTrackingQuantity;
    // 平均回复数：回复记录数/回复客户数 指标格式：7.22
    private String avgReplyQuantity;

    public String getAvgTrackingQuantity() {
        return calculateAverageValue(getTrackingQuantity(), getTrackingCustomer());
    }

    public void setAvgTrackingQuantity(String avgTrackingQuantity) {
        this.avgTrackingQuantity = avgTrackingQuantity;
    }

    public String getAvgReplyQuantity() {
        return calculateAverageValue(getReplyQuantity(), getReplyCustomer());
    }

    public void setAvgReplyQuantity(String avgReplyQuantity) {
        this.avgReplyQuantity = avgReplyQuantity;
    }


}
