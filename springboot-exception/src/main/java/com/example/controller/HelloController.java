package com.example.controller;

import com.example.common.Result;
import com.example.service.FooService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("/hello")
public class HelloController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private FooService fooService;

    @RequestMapping("/exception")
    public Result exception() {
        fooService.exception();
        return Result.ok();
    }

    @RequestMapping("/info")
    public Result<Date> info(HttpServletRequest request){
        Map<String, String[]> parameterMap = request.getParameterMap();
        StringBuffer requestURL = request.getRequestURL();
        return Result.ok(new Date());
    }


}
