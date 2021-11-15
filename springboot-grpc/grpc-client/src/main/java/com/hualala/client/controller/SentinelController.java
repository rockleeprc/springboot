package com.hualala.client.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

@Slf4j
@RestController
public class SentinelController {

    private static final ConcurrentHashMap<String, String> CACHE = new ConcurrentHashMap();
    private static final String METHOD1_KEY = "method1";

    static {
        CACHE.put(METHOD1_KEY, "初始值");
    }

    @SentinelResource(fallback = "method1Fallback", blockHandler = "method1BlockHandler")
    @RequestMapping("/method1")
    public String method1(Integer param) throws InterruptedException {
        log.info("com.hualala.client.controller.SentinelController.method1......");
        String res = null;
        try {
            // 业务逻辑
            int i = 100 / param; // 模拟异常
            TimeUnit.SECONDS.sleep(3);// 模拟超时
            res = "method1 -->" + LocalDateTime.now();
        } catch (Exception e) {
            log.error(Thread.currentThread().getStackTrace()[1].getMethodName(), e);
            throw e;
        } finally {
            // 缓存数据
            if (res != null) {
                CACHE.put(METHOD1_KEY, res);
            }
        }
        return res;
    }

    /**
     * 熔断
     *
     * @param param
     * @return
     */
    public String method1Fallback(Integer param) {
        log.info("method1Fallback");
        // 缓存读取
        return CACHE.get(METHOD1_KEY);
    }

    /**
     * 流控
     *
     * @param blockException
     * @return
     */
    public String method1BlockHandler(Integer param, BlockException blockException) {
        blockException.printStackTrace();
        log.info("method1BlockHandler");
        return CACHE.get(METHOD1_KEY);
    }

    @SentinelResource(blockHandler = "method2BlockHandler")
    @RequestMapping("/method2")
    public String method2() throws InterruptedException {
        TimeUnit.SECONDS.sleep(2);
        return "method2 -->" + LocalDateTime.now();
    }

    public String method2BlockHandler(BlockException blockException) {
        blockException.printStackTrace();
        return "method2BlockHandler";
    }


}
