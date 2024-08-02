package cn.kd;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@MapperScan("cn.kd.mapper")
public class MybatisApplication {
    private final static Logger logger = LoggerFactory.getLogger(MybatisApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(MybatisApplication.class, args);
        logger.info("==========provider bar start");
        logger.debug("==========provider bar start");
    }
}
