package com.foo.servervice;

import com.foo.dao.IPerson;
import com.foo.dao.impl.MarryImpl;
import com.foo.dao.impl.TomImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

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
