package com.example.clientA.service.client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import javax.annotation.PostConstruct;

@Service
@Slf4j
public class ClientServiceB {

    /**
     * URL uses the logical name of user-service - upper or lower case,
     * doesn't matter.
     */
    public static final String SERVICE_B_URL = "http://desktop-n99t78j:8766/public/";


    @Autowired
    @LoadBalanced
    protected RestTemplate restTemplate;


    /**
     * The RestTemplate works because it uses a custom request-factory that uses
     * Ribbon to look-up the service to use. This method simply exists to show
     * this.
     */
    @PostConstruct
    public void demoOnly() {
        // Can't do this in the constructor because the RestTemplate injection
        // happens afterwards.
        log.warn("The RestTemplate request factory is " + restTemplate.getRequestFactory());
    }

    //Synchronous Model
    @HystrixCommand(fallbackMethod = "greetingDefault")
    public String getSaludo(String name) {

        log.info("greeting() invoked: for " + name);

        return restTemplate.getForObject(serviceUrl + "api/saludo/{name}", String.class, name);

    }


}
