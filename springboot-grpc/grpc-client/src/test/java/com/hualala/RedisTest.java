package com.hualala;


import com.hualala.client.GrpcClientApplication;
import com.hualala.client.entity.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = GrpcClientApplication.class)
public class RedisTest {

    @Autowired
    private RedisTemplate redisTemplate;

//    @Autowired
//    private HashOperations hashOperations;
//
//    @Autowired
//    private ValueOperations valueOperations;
//
//    @Autowired
//    private ListOperations listOperations;
//
//    @Autowired
//    private SetOperations setOperations;
//
//    @Autowired
//    private ZSetOperations zSetOperations;


    @Test
    public void testHashKey() {
        HashOperations hashOperations = redisTemplate.opsForHash();

        Boolean hasKey = hashOperations.hasKey("hash_key1", "15d070b7eefeb0b669151abeafb29db4ea6078cbe7ed1ac41ccbf64b0634d38f");
        System.out.println(hasKey);
        Boolean hasKey2 = hashOperations.hasKey("key", "field");
        System.out.println(hasKey2);
    }

    @Test
    public void testHashOperations() {
        HashOperations hashOperations = redisTemplate.opsForHash();
        String key = "hash_key1";
        String field = "hash_field";

        Person person3 = new Person(3, "liyan3", "中文");
        Person person4 = new Person(4, "liyan4", "中文");
        Map<String, Person> map = new HashMap<>();
        map.put("liyan3", person3);
        map.put("liyan4", person4);
        hashOperations.put(key, field, map);
        redisTemplate.expire(key, 60, TimeUnit.SECONDS);

        Map<String, Person> redisMap = (Map<String, Person>) hashOperations.get(key, field);
        System.out.println(redisMap);
    }

    @Test
    public void testRedisConnect() {
        System.out.println(redisTemplate);

//        System.out.println(hashOperations);
//        System.out.println(valueOperations);
//        System.out.println(listOperations);
//        System.out.println(setOperations);
//        System.out.println(zSetOperations);
    }
}
