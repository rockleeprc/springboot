package com.hualala;

import com.hualala.client.GrpcClientApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
//import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = GrpcClientApplication.class)
public class RedissonTest {


    @Autowired
//    private RedissonClient redissonClient;

    @Test
    public void testRedissonClient() {
//        System.out.println(redissonClient);
    }
}
