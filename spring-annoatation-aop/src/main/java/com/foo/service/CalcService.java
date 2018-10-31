package com.foo.service;

import org.springframework.transaction.annotation.Transactional;

public class CalcService {

    public Integer div(Integer i, Integer j) {
        System.out.println("**********div***********");
        return i / j;
    }
}
