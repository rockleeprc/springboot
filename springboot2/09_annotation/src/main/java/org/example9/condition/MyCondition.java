package org.example9.condition;

import org.example9.domain.Person;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.context.annotation.Conditional;
import org.springframework.core.type.AnnotatedTypeMetadata;

import java.util.Map;

public class MyCondition implements Condition {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        // 获取注解上的信息
        Map<String, Object> map = metadata.getAnnotationAttributes(Conditional.class.getName());
        ConfigurableListableBeanFactory beanFactory = context.getBeanFactory();
        Person person = beanFactory.getBean(Person.class);
        if (person != null) {
            return true;
        }
        return false;
    }
}
