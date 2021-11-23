package com.hualala.client.controller;


import com.hualala.client.annotation.Caching;
import com.hualala.client.entity.Person;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class CachingController {

    @Caching(expire = 10)
    @RequestMapping("/cache")
    public String cache(@RequestBody Person person) {
        System.out.println("com.hualala.client.controller.CachingController.cache()");
        System.out.println(person);
        return LocalDateTime.now().toString();
    }
}
