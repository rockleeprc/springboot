package com.foo.processor;

import com.foo.bean.EngineFactoryBean;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;

public class SpecialBeanForEngineProcessor implements BeanFactoryPostProcessor, BeanNameAware {

    private String name;

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        BeanDefinitionRegistry bdr = (BeanDefinitionRegistry) beanFactory;
        GenericBeanDefinition gbd = new GenericBeanDefinition();
        gbd.setBeanClass(EngineFactoryBean.class);
        gbd.setScope(BeanDefinition.SCOPE_SINGLETON);
        gbd.setAutowireCandidate(true);
        bdr.registerBeanDefinition("engine-factory-bean", gbd);
    }

    @Override
    public void setBeanName(String name) {
        System.out.println("SpecialBeanForEngineProcessor beanName=" + name);
        this.name = name;
    }

}
