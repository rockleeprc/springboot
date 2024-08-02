package com.foo.config;

import com.foo.beanfactoryorder.FirstBeanFactoryPostProcessor;
import com.foo.beanfactoryorder.SecondBeanFactoryPostProcessor;
import com.foo.beanfactoryorder.ThirdBeanFactoryPostProcessor;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({FirstBeanFactoryPostProcessor.class, SecondBeanFactoryPostProcessor.class, ThirdBeanFactoryPostProcessor.class})
public class OrderedConfiguration {
}
