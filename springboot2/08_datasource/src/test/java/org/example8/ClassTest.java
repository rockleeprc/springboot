package org.example8;


import org.example8.spi.impl.Apple;
import org.junit.Test;

public class ClassTest {

    @Test
    public void testIsAssignableFrom(){
        System.out.println(Object.class.isAssignableFrom(Apple.class));
    }
}
