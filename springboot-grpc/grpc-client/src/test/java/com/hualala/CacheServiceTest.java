package com.hualala;

import com.hualala.client.GrpcClientApplication;
import com.hualala.client.entity.Person;
import com.hualala.client.service.CacheService;
import com.hualala.client.util.HashUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = GrpcClientApplication.class)
public class CacheServiceTest {

    @Autowired
    private CacheService cacheService;

    @Autowired
    RedisTemplate redisTemplate;

    String key = "hash_key1";
    String field = "hash_field";
    String params = "3liyan3中文";

    @Test
    public void testPutNoCrash() {
        Person person3 = new Person(3, "liyan3", "中文");
        cacheService.hashPutDefaultExpire(key, params, person3);

        String mur = HashUtils.murmur128(params);
        String sha = HashUtils.sha256(params);
        System.out.println(mur);
        System.out.println(sha);
    }

    @Test
    public void testPutCrash() {
        Person person3 = new Person(4, "liyan4", "日文");
        cacheService.hashPutDefaultExpire(key, params, person3);

        String mur = HashUtils.murmur128(params);
        String sha = HashUtils.sha256(params);
        System.out.println(mur);
        System.out.println(sha);
    }

    @Test
    public void testGet() {
        Person person = cacheService.hashGet(key, "abc", Person.class);
        System.out.println(person);

//        HashOperations hashOperations = redisTemplate.opsForHash();
//        Map<String,Person> values = (Map<String, Person>) hashOperations.get(key, params);
//        System.out.println(values);
    }


    @Test
    public void testSha() throws InterruptedException {
        System.out.println(HashUtils.murmur128(params));
        TimeUnit.SECONDS.sleep(2);
        System.out.println(HashUtils.murmur128(params));
    }

}
