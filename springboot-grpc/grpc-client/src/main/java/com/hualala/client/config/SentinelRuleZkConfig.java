package com.hualala.client.config;

import com.alibaba.csp.sentinel.datasource.ReadableDataSource;
import com.alibaba.csp.sentinel.datasource.zookeeper.ZookeeperDataSource;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import javax.annotation.PostConstruct;
import java.util.List;

@Configuration
public class SentinelRuleZkConfig {


    @Value("${spring.application.name}")
    private String appName;

    @Value("${zookeeper.address}")
    private String remoteAddress;

    @Value("${spring.cloud.sentinel.zookeeper.path}")
    private String path;


    /**
     * 流控规则数据源
     */
    @PostConstruct
    public void loadRules() {
        ReadableDataSource<String, List<FlowRule>> flowRuleDataSource = new ZookeeperDataSource<>(
                remoteAddress,
                path + "/" + appName,
                source -> JSON.parseObject(source, new TypeReference<List<FlowRule>>() {
                }));
        FlowRuleManager.register2Property(flowRuleDataSource.getProperty());

    }
}
