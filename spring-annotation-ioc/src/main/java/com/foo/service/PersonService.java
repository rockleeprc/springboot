package com.foo.service;

import com.foo.dao.IPerson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class PersonService {
    //    @Resource(name="tomImpl")
    @Qualifier("marryImpl")
    @Autowired
    private IPerson per;

    public void print() {
        System.out.println("invoke ----- " + per.findBy());
    }
}
