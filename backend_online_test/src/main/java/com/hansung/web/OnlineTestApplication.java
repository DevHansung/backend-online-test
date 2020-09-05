package com.hansung.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class OnlineTestApplication {
	public static void main(String[] args) {
		SpringApplication.run(OnlineTestApplication.class, args);
	}
}
