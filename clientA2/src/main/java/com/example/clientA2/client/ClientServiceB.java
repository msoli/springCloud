package com.example.clientA2.client;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.command.AsyncResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.concurrent.Future;

@Service
@Slf4j
public class ClientServiceB {

    /**
     * URL uses the logical name of user-service - upper or lower case,
     * doesn't matter.
     */
    public static final String SERVICE_B_URL = "http://DEMO.MICROSERVICE";


    @Autowired
    @LoadBalanced
    private RestTemplate restTemplate;


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
    @HystrixCommand(fallbackMethod = "saludoDefault")
    public String saludo(String name) {

        log.info("SALUDO () invoked: for " + name);

        String respuesta = restTemplate.getForObject(SERVICE_B_URL + "/api/demo/{name}", String.class, name);
        return respuesta;

    }

    //Asynchronous Model
    @HystrixCommand(fallbackMethod = "saludoFutureDefault")
    public Future<String> getSaludoFuture(final String name) {
        return new AsyncResult<String>() {
            public String invoke() {
                return restTemplate.getForObject(SERVICE_B_URL + "/api/demo/{name}", String.class, name);
            }
        };
    }

    public String saludoDefault(String name) {
        return new String("Hello World thanks to Circuit Breaker (Hystrix) - SYNCHRONOUS");
    }

    public String saludoFutureDefault(String name) {
        return new String("Hello World thanks to Circuit Breaker (Hystrix) - FUTURE (ASYNCHRONOUS");
    }


}
