package com.example5.controller;

import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.core.MethodParameter;
import org.springframework.core.ParameterNameDiscoverer;
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

@RestController
@RequestMapping("/param")
public class ParamController {
    @RequestMapping("/p1")
    public String param1(@RequestParam String str1, @RequestParam String str2) {
        return str1 + " | " + str2;
    }

    @RequestMapping("/p2")
    public String param2(@NotNull @NotEmpty @RequestParam("str1") String str1, @NotNull @NotEmpty @RequestParam("str2") String str2) {
        return str1 + " | " + str2;
    }

    public static void main(String[] args) throws NoSuchMethodException {
        Method method = ParamController.class.getMethod("param2", String.class, String.class);
        MethodParameter nameParameter = new MethodParameter(method, 0);
        MethodParameter ageParameter = new MethodParameter(method, 1);

        // 打印输出：
        // 使用Parameter输出
        Parameter nameOriginParameter = nameParameter.getParameter();
        Parameter ageOriginParameter = ageParameter.getParameter();
        System.out.println("===================源生Parameter结果=====================");
        System.out.println(nameOriginParameter.getType() + "----" + nameOriginParameter.getName());
        System.out.println(ageOriginParameter.getType() + "----" + ageOriginParameter.getName());
        System.out.println("===================MethodParameter结果=====================");
        System.out.println(nameParameter.getParameterType() + "----" + nameParameter.getParameterName());
        System.out.println(ageParameter.getParameterType() + "----" + ageParameter.getParameterName());
        System.out.println("==============设置上ParameterNameDiscoverer后MethodParameter结果===============");
        ParameterNameDiscoverer parameterNameDiscoverer = new DefaultParameterNameDiscoverer();
        nameParameter.initParameterNameDiscovery(parameterNameDiscoverer);
        ageParameter.initParameterNameDiscovery(parameterNameDiscoverer);
        System.out.println(nameParameter.getParameterType() + "----" + nameParameter.getParameterName());
        System.out.println(ageParameter.getParameterType() + "----" + ageParameter.getParameterName());
    }

    public static void t1() throws NoSuchMethodException {
        Method method = ParamController.class.getMethod("param2", String.class, String.class);
        int parameterCount = method.getParameterCount();
        Parameter[] parameters = method.getParameters();

        // 打印输出：
        System.out.println("方法参数总数：" + parameterCount);
        Arrays.stream(parameters).forEach(p -> System.out.println(p.getType() + "----" + p.getName()));
    }
}
