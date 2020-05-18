package org.example8.spi.impl;

import org.example8.spi.Fruit;

public class Apple implements Fruit {
    @Override
    public String getName() {
        return "an apple";
    }
}
