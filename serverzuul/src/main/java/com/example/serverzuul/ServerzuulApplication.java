package com.example.serverzuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.stereotype.Controller;

@SpringBootApplication
@Controller
@EnableZuulProxy
public class ServerzuulApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServerzuulApplication.class, args);
	}
}
