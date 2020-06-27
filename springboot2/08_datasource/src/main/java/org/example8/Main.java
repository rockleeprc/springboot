package org.example8;


import com.mysql.jdbc.Driver;

import java.io.IOException;
import java.sql.SQLException;

public class Main {

    private static int i, j;
    private static int k, h;

    public static void main(String[] args) throws InterruptedException, IOException, SQLException {
        Driver driver = new Driver();
        System.out.println(driver);

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
