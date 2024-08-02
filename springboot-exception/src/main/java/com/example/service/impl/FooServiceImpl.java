package com.example.service.impl;

import com.example.service.FooService;
import org.springframework.stereotype.Service;

@Service
public class FooServiceImpl implements FooService {
    @Override
    public String exception() {
        int i = 1 / 0;
        return "E";
    }
}
