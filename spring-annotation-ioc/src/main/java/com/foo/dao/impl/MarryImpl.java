package com.foo.dao.impl;

import com.foo.dao.IPerson;
import org.springframework.stereotype.Service;


@Service
public class MarryImpl implements IPerson {
    @Override
    public String findBy() {
        return "marry";
    }
}
