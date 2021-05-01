package cn.kd.entity;

import java.io.Serializable;

/**
 * 顾问/组 工作量和产出表实体对象
 */
public class WorkloadAdviser implements Serializable {
    private Long adviserId; // 顾问id
    private String adviserName; // 顾问名称
    private Long adviserGroupId; // 顾问组id
    private String adviserGroupName;// 顾问组名称
    private Long newCustomer; // 新客户数
    private Long totalCustomer; // 客户总数
    private Long imSubmitCustomer;// IM提交客户数
    private Long trackingCustomer;// 跟进客户数
    private Long trackingQuantity;// 跟进次数
    private Long talkTimeSecond;// 通话时长
    private Long imReceiveCustomer;//  im接待客户
    private Long replyCustomer;// 回复客户数
    private Long replyQuantity;// 回复记录数
    private Long matchSubmitQuantity;// 匹配提交次数
    private Long matchBrandQuantity;// 匹配品牌个数
    private Long matchAgentBrandQuantity;// 匹配品牌个数（经纪品牌)
    private Long matchAdBrandQuantity;// 匹配品牌个数（广告品牌）

    public Long getAdviserGroupId() {
        return adviserGroupId;
    }

    public void setAdviserGroupId(Long adviserGroupId) {
        this.adviserGroupId = adviserGroupId;
    }

    public String getAdviserGroupName() {
        return adviserGroupName;
    }

    public void setAdviserGroupName(String adviserGroupName) {
        this.adviserGroupName = adviserGroupName;
    }

    public Long getAdviserId() {
        return adviserId;
    }

    public void setAdviserId(Long adviserId) {
        this.adviserId = adviserId;
    }

    public String getAdviserName() {
        return adviserName;
    }

    public void setAdviserName(String adviserName) {
        this.adviserName = adviserName;
    }

    public Long getNewCustomer() {
        return newCustomer;
    }

    public void setNewCustomer(Long newCustomer) {
        this.newCustomer = newCustomer;
    }

    public Long getTotalCustomer() {
        return totalCustomer;
    }

    public void setTotalCustomer(Long totalCustomer) {
        this.totalCustomer = totalCustomer;
    }

    public Long getImSubmitCustomer() {
        return imSubmitCustomer;
    }

    public void setImSubmitCustomer(Long imSubmitCustomer) {
        this.imSubmitCustomer = imSubmitCustomer;
    }

    public Long getTrackingCustomer() {
        return trackingCustomer;
    }

    public void setTrackingCustomer(Long trackingCustomer) {
        this.trackingCustomer = trackingCustomer;
    }

    public Long getTrackingQuantity() {
        return trackingQuantity;
    }

    public void setTrackingQuantity(Long trackingQuantity) {
        this.trackingQuantity = trackingQuantity;
    }

    public Long getTalkTimeSecond() {
        return talkTimeSecond;
    }

    public void setTalkTimeSecond(Long talkTimeSecond) {
        this.talkTimeSecond = talkTimeSecond;
    }

    public Long getImReceiveCustomer() {
        return imReceiveCustomer;
    }

    public void setImReceiveCustomer(Long imReceiveCustomer) {
        this.imReceiveCustomer = imReceiveCustomer;
    }

    public Long getReplyCustomer() {
        return replyCustomer;
    }

    public void setReplyCustomer(Long replyCustomer) {
        this.replyCustomer = replyCustomer;
    }

    public Long getReplyQuantity() {
        return replyQuantity;
    }

    public void setReplyQuantity(Long replyQuantity) {
        this.replyQuantity = replyQuantity;
    }

    public Long getMatchSubmitQuantity() {
        return matchSubmitQuantity;
    }

    public void setMatchSubmitQuantity(Long matchSubmitQuantity) {
        this.matchSubmitQuantity = matchSubmitQuantity;
    }

    public Long getMatchBrandQuantity() {
        return matchBrandQuantity;
    }

    public void setMatchBrandQuantity(Long matchBrandQuantity) {
        this.matchBrandQuantity = matchBrandQuantity;
    }

    public Long getMatchAgentBrandQuantity() {
        return matchAgentBrandQuantity;
    }

    public void setMatchAgentBrandQuantity(Long matchAgentBrandQuantity) {
        this.matchAgentBrandQuantity = matchAgentBrandQuantity;
    }

    public Long getMatchAdBrandQuantity() {
        return matchAdBrandQuantity;
    }

    public void setMatchAdBrandQuantity(Long matchAdBrandQuantity) {
        this.matchAdBrandQuantity = matchAdBrandQuantity;
    }
}
