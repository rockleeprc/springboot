package com.hualala.server.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "redis")
public class RedisProperties2 {
    private String host;
    private Integer port;
    private Integer db;
    private Integer poolMaxActive;
    private Integer poolMaxWait;
    private Integer poolMaxIdle;
    private Integer poolMinIdle;

    public Integer getPoolMaxActive() {
        return poolMaxActive;
    }

    public void setPoolMaxActive(Integer poolMaxActive) {
        this.poolMaxActive = poolMaxActive;
    }

    public Integer getPoolMaxWait() {
        return poolMaxWait;
    }

    public void setPoolMaxWait(Integer poolMaxWait) {
        this.poolMaxWait = poolMaxWait;
    }

    public Integer getPoolMaxIdle() {
        return poolMaxIdle;
    }

    public void setPoolMaxIdle(Integer poolMaxIdle) {
        this.poolMaxIdle = poolMaxIdle;
    }

    public Integer getPoolMinIdle() {
        return poolMinIdle;
    }

    public void setPoolMinIdle(Integer poolMinIdle) {
        this.poolMinIdle = poolMinIdle;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public Integer getDb() {
        return db;
    }

    public void setDb(Integer db) {
        this.db = db;
    }

    @Override
    public String toString() {
        return "RedisProperties2{" +
                "host='" + host + '\'' +
                ", port=" + port +
                ", db=" + db +
                ", poolMaxActive=" + poolMaxActive +
                ", poolMaxWait=" + poolMaxWait +
                ", poolMaxIdle=" + poolMaxIdle +
                ", poolMinIdle=" + poolMinIdle +
                '}';
    }
}
