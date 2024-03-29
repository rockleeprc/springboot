package com.hualala.server.controller;

import com.hualala.server.entity.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class GrpcController {


    @Autowired
    private Person person;

    static {
        log.info("-----static GrpcController");
    }

    @RequestMapping("/person")
    public Person person() {
        log.info("-----" + person);
        return person;
    }

}
