package com.example5.controller;

import com.example5.domain.Person;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;


@RestController
public class PersonController {
    @RequestMapping("person")
    public Person person() {
        return new Person("AAA", 18, new Date());
    }
}
