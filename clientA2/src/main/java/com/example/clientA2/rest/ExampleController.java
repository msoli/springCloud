package com.example.clientA2.rest;

import com.example.clientA2.client.ClientServiceB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@RestController
@RequestMapping("api")
public class ExampleController {


    @Autowired
    private ClientServiceB clientServiceB;

    @GetMapping(path = "saludo/{name}")
    public String hello(@PathVariable("name") String name) {

        return "Hola desde microservicio A " + name;

    }

    @GetMapping(path = "saludoDefault/{name}")
    public String saludoDefault(@PathVariable("name") String name) {

        String response = clientServiceB.getSaludo(name);
        return response;

    }

    @GetMapping(path = "saludoFuture/{name}")
    public String saludoFuture(@PathVariable("name") String name) throws ExecutionException, InterruptedException {

        Future<String> response = clientServiceB.getSaludoFuture(name);
        return response.get();

    }
}
