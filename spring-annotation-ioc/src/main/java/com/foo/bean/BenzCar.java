package com.foo.bean;

import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;

public class BenzCar implements InitializingBean {

    private Engine engine;

    public BenzCar() {
        System.out.println("BenzCar Constructor");
        if (engine == null) {
            System.out.println("BenzCar's engine not setting");
        } else {
            System.out.println("BenzCar's engine installed");
        }
    }

    public void start() {
        System.out.println("BenzCar start");
        engine.fire();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("BenzCar initializingBean after propertieSet");
        if (engine == null) {
            System.out.println("BenzCar's engine not setting, in initializingBean ");
        } else {
            System.out.println("e:"+engine);
            System.out.println("BenzCar's engine installed, in initializingBean");
            engine.fire();
        }
    }

    @PostConstruct
    public void postConstruct() {
        System.out.println("BenzCar postConstruct");
        if (engine == null) {
            System.out.println("BenzCar's engine not setting, in postConstruct");
        } else {
            System.out.println("e:"+engine);
            System.out.println("e:"+engine.getClass());
            System.out.println("BenzCar's engine installed, in postConstruct");
        }
    }

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }
}
