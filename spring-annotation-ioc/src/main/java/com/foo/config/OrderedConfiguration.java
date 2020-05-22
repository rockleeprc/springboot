package com.foo.config;

import com.foo.order.FirstBeanFactoryPostProcessor;
import com.foo.order.SecondBeanFactoryPostProcessor;
import com.foo.order.ThirdBeanFactoryPostProcessor;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({FirstBeanFactoryPostProcessor.class, SecondBeanFactoryPostProcessor.class, ThirdBeanFactoryPostProcessor.class})
public class OrderedConfiguration {
}
