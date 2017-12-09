package com.example.servereureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class ServereurekaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServereurekaApplication.class, args);
	}
}
