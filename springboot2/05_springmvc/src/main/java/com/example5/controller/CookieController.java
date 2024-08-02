package com.example5.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;

@RestController
public class CookieController {

    @RequestMapping("/cookie")
    public String cookie(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("getParameter JSESSIONID:"+request.getParameter("JSESSIONID"));
        System.out.println("session:"+request.getSession());
        System.out.println("sessionId:"+request.getSession().getId());
        Cookie c = new Cookie("version", "1.0");
        c.setMaxAge(2);
        response.addCookie(c);
        return LocalDate.now().toString();
    }
}
