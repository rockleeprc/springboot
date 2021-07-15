package com.example5;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.LinkedHashSet;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		Runnable runnable = LinkedHashSet::new;

//		SpringApplication.run(Application.class, args);
	}

}
