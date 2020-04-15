package com.foo.dao.impl;

import com.foo.dao.IPerson;
import org.springframework.stereotype.Repository;

@Repository
public class TomImpl implements IPerson {
    @Override
    public String findBy() {
        return "Tom";
    }
}
