package com.foo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class HelloController {
    @RequestMapping("/hello")
    public String forwoad() {
        return "hello";
    }

    @RequestMapping("/info")
    public String info() {
        return "info";
    }
}


