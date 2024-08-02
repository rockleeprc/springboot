package org.example9.annotation;

import org.example9.condition.MyCondition;
import org.example9.config.BeanAutoConfiguration;
import org.example9.domain.Person;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({Person.class, BeanAutoConfiguration.class})
@Conditional(MyCondition.class)
public @interface EnableBean {
}
