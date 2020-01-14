package org.example9.annotation;

import org.example9.config.BeanAutoConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(BeanAutoConfiguration.class)
public @interface EnableBean {
}
