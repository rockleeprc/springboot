package org.es;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.net.URL;
import java.util.Arrays;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTests {


    @Test
    public void t(){
        URL resource = this.getClass().getResource("/org/es");
        System.out.println(resource.getPath());
        File file = new File(resource.getPath());
        Arrays.stream(file.listFiles()).forEach(System.out::println);
    }

    @Test
    public void redlock() {
        // 集群模式
        Config config = new Config();
        config.useClusterServers().addNodeAddress(
                        "redis://172.29.3.245:6375", "redis://172.29.3.245:6376", "redis://172.29.3.245:6377",
                        "redis://172.29.3.245:6378", "redis://172.29.3.245:6379", "redis://172.29.3.245:6380")
                .setPassword("a123456").setScanInterval(5000);
        RedissonClient redissonClient = Redisson.create(config);
        //通过redisson获取redlock
        RLock lock = redissonClient.getLock("redlock");

        lock.lock();
        try {

        } finally {
            lock.unlock();
        }
    }

}
