package com.example.serverzuul;

import com.example.serverzuul.configuration.RibbonConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClients;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.stereotype.Controller;


@SpringBootApplication
@EnableZuulProxy
@RibbonClients(defaultConfiguration = RibbonConfiguration.class)
@EnableEurekaClient
public class ServerzuulApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServerzuulApplication.class, args);
	}
}
