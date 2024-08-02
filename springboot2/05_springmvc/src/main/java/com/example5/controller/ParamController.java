package com.example5.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Arrays;

@Validated
@RestController
@RequestMapping("/param")
public class ParamController {
    @RequestMapping("/p1")
    public String param1(@RequestParam String str1, @RequestParam String str2) {
        return str1 + " | " + str2;
    }

    @RequestMapping("/p2")
    public String param2(@Valid @NotBlank(message = "str1不能为空") @RequestParam("str1") String str1, @NotNull @RequestParam("str2") String str2) {
        return str1 + " | " + str2;
    }

    @PostMapping(value = "p3")
    public void param3(@RequestParam(value = "age", required = false) Integer age, @RequestParam(value = "name", required = false) String name, @RequestParam(value = "array", required = false) Integer[] array) {
        System.out.println(age);
        System.out.println(name);
        System.out.println(Arrays.toString(array));
    }

    @PostMapping(value = "p4")
    public void param4(@RequestBody Parma param) {
        System.out.println(param);
    }

    static class Parma {
        private Integer age;
        private String name;
        Integer[] array;

        @Override
        public String toString() {
            return "Parma{" +
                    "age=" + age +
                    ", name='" + name + '\'' +
                    ", array=" + Arrays.toString(array) +
                    '}';
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

        public Integer[] getArray() {
            return array;
        }

        public void setArray(Integer[] array) {
            this.array = array;
        }
    }

}
