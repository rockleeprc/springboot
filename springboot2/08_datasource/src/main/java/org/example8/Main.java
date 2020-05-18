package org.example8;

import java.io.IOException;

public class Main {

    private static int i, j;
    private static int k, h;

    public static void main(String[] args) throws InterruptedException, IOException {
        int count = 0;
        while (true) {
            i = 0;
            j = 0;
            k = 0;
            h = 0;
            Thread t1 = new Thread(() -> {
                i = 1;
                j = h;
            }, "t1");
            Thread t2 = new Thread(() -> {
                k = 1;
                h = i;
            }, "t1");
            t1.start();
            t2.start();
            t1.join();
            t2.join();
            if(j==0&& h==0){
                System.out.println("j==0 && h==0,count="+count);
                break;
            }
            System.out.println(count);
            count++;
        }

    }

    public static void m1(Person person) {
//        person.name = "B";
        Person p = new Person();
        p.name = "BB";
        person = p;
        System.out.println(person.hashCode());
    }

    private static class Person {
        String name;


    }

}
