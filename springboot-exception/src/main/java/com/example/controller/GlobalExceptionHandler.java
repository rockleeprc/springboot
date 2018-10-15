package com.example.controller;

import com.example.common.Result;
import com.example.common.ErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
//@RestControllerAdvice
public class GlobalExceptionHandler {


    /**
     * 问题：浏览器和客户端都会返回json数据
     * @param exception
     * @return
     */
//    @ExceptionHandler(Exception.class)
//    @RequestBody
//    public Result handleException(Exception exception) {
//        return Result.no(ErrorCode.ERR, e.getMessage());
//    }

    /**
     * 自适应，浏览器返回5xx.html，客户端返回json
     *
     * @param exception
     * @param request
     * @return
     */
    @ExceptionHandler(Exception.class)
    public String handleException(Exception exception, HttpServletRequest request) {
        request.setAttribute("javax.servlet.error.status_code", HttpStatus.INTERNAL_SERVER_ERROR.value());
        return "forward:/error";
    }
}
