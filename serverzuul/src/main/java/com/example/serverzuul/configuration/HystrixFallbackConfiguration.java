package com.example.serverzuul.configuration;

import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

/**
 * @author moises.macero
 */
@Configuration
public class HystrixFallbackConfiguration {


    @Bean
    public FallbackProvider route1FallbackProvider() {
        GenericFallbackProvider route1Fallback = new GenericFallbackProvider();
        route1Fallback.setRoute("saludo.microservice");
        return route1Fallback;
    }

    @Bean
    public FallbackProvider route2FallbackProvider() {
        GenericFallbackProvider route2Fallback = new GenericFallbackProvider();
        route2Fallback.setRoute("demo.microservice");
        route2Fallback.setRawStatusCode(200);
        route2Fallback.setStatusCode(HttpStatus.OK);
        route2Fallback.setResponseBody("{\"message\":\"Service Unavailable. En breve regresamos\"}");
        return route2Fallback;
    }


}
