package com.hualala.client.controller;


import com.hualala.client.annotation.Caching;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class CachingController {

    @Caching(expire = 10)
    @RequestMapping("/cache")
    public String cache(Integer age, String name, Long weight, String address, Double salar) {
        System.out.println("com.hualala.client.controller.CachingController.cache()");
        return LocalDateTime.now().toString();
    }
}
