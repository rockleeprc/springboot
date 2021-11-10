package com.example5.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@Slf4j
@RequestMapping("/foo")
public class FooController {

    @Value("${server.port}")
    private Integer port;

    @GetMapping("/foo/info")
    public String info() {
        LocalDateTime now = LocalDateTime.now();
        log.info("request {}, port {}", now, port);
        return "now----------:" + now + " port:" + port;
    }
}
