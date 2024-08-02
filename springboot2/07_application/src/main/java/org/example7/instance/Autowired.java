package org.example7.instance;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Inherited
public @interface Autowired {
    String value() default "";

    String name() default "";
}
