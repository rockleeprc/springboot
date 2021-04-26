package cn.kd.entity;

import java.io.Serializable;

public class AdviserWorkload implements Serializable {

    private Long newCustomers;

    public Long getNewCustomers() {
        return newCustomers;
    }

    public void setNewCustomers(Long newCustomers) {
        this.newCustomers = newCustomers;
    }
}
