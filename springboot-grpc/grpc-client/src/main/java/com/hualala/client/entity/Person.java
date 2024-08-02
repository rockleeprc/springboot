package com.hualala.client.entity;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Person {

    private Integer age;
    private String name;
    private String address;
    private List<String> nums;
    private Long[] array;
    private Map map;

    public Person() {
    }

    public Person(Integer age, String name, String address) {
        this.age = age;
        this.name = name;
        this.address = address;
    }

    public List<String> getNums() {
        return nums;
    }

    public void setNums(List<String> nums) {
        this.nums = nums;
    }

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void getA() {
        System.out.println("");
    }

    public Object[] getB() {
        return Arrays.asList(1, 2, 3).toArray();
    }

    public Long[] getArray() {
        return array;
    }

    public void setArray(Long[] array) {
        this.array = array;
    }

    @Override
    public String toString() {
        return "Person{" +
                "age=" + age +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", nums=" + nums +
                '}';
    }
}
