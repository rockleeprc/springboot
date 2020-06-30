package com.example5.controller;

import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.core.MethodParameter;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;

@Validated
@RestController
@RequestMapping("/param")
public class ParamController {
    @RequestMapping("/p1")
    public String param1(@RequestParam String str1, @RequestParam String str2) {
        return str1 + " | " + str2;
    }

    @RequestMapping("/p2")
    public String param2(@Valid @NotBlank(message="str1不能为空") @RequestParam("str1") String str1, @NotNull @NotEmpty @RequestParam("str2") String str2) {
        return str1 + " | " + str2;
    }


}
