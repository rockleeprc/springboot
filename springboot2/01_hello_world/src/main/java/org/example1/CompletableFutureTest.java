package org.example1;

import java.util.Optional;

public class CompletableFutureTest {

    public static void main(String[] args) {
        Object o = Optional.ofNullable(new Object())
                .map(e -> {
                    System.out.println("hello");
                    return e;
                }).orElseThrow(() -> new RuntimeException("error"));

        Optional.ofNullable(null)
                .ifPresentOrElse(e -> System.out.println(e),
                        () -> System.out.println("不存在"));

        String str = null;
        String s = Optional.ofNullable(str)
                .orElse(print()); // 是否非空都会执行
        System.out.println(s);

    }

    public static String print(){
        System.out.println("----");
        return "CC";
    }
}
