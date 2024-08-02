package com.hualala.client.controller.sentinel;

import com.alibaba.csp.sentinel.slots.block.BlockException;

public class CachingSentinel {

    public static String flowBlockHandler(BlockException e) {
        e.printStackTrace();
        return "流控降级";
    }
}
