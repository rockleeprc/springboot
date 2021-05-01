package com.example5.controller;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Date;

@RestController
@RequestMapping("date")
public class DateController {


    /**
     * 就近原则
     *
     * @param date
     */
    @RequestMapping("date1")
    public Date date1(@DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {
        return date;
    }

    @RequestMapping("date2")
    public Date date2(Date date) {
        return date;
    }

    @RequestMapping("date3")
    public Date date3() {
        return new Date();
    }

    @RequestMapping("date4")
    public LocalDate date4(LocalDate date) {
        return date;
    }


}
