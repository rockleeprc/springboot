package com.foo.config;

import com.foo.bean.Blue;
import com.foo.bean.Red;
import com.foo.bean.Yellow;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LifecycleConfiguration {

    //    @Scope("prototype")
//    @Bean(initMethod = "init", destroyMethod = "destroy")
    public Red red() {
        return new Red();
    }

//    @Scope("prototype")
//    @Bean
    public Blue blue() {
        return new Blue();
    }

        @Bean
    public Yellow yellow() {
        return new Yellow();
    }

}
