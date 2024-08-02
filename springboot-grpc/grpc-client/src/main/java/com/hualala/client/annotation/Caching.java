package com.hualala.client.annotation;


import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Caching {
    @AliasFor("ttl")
    long expire() default -1;

    @AliasFor("expire")
    long ttl() default -1;

    TimeUnit unit() default TimeUnit.SECONDS;
}
