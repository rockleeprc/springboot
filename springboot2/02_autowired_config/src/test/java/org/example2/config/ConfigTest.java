package org.example2.config;

import org.example2.config.domain.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest//(classes = {AutowiredConfigApplication.class})// 包名不一致
public class ConfigTest {

    @Autowired
    @Qualifier(value ="p1")
    private Person person;

    @Test
    public void person() {
        System.out.println(person);
    }
}
