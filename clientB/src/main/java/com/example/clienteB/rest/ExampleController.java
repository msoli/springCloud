package com.example.clienteB.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class ExampleController {

    @GetMapping(path = "demo/{name}")
    public String hello(@PathVariable("name") String name) {
        System.out.println("k");

        return "Hola desde microservicio B " + name;

    }
}
