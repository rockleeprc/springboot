package cn.kd.entity.view;

import cn.kd.entity.WorkloadAdviser;


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


    private static Double DEFAULT_VIEW_VALUE = 0D;

    public static String calculateAverageValue(Long divisor, Long dividend) {
        if (divisor == null || divisor == 0.00D || dividend == null || dividend == 0.00D) {
            return String.format("%.2f", DEFAULT_VIEW_VALUE);
        }
        Double result = (double) divisor / (double) dividend;
        return String.format("%.2f", result);
    }
}
