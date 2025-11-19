/*
 * Copyright 2025 the original author or authors...
 */
package com.company;


/* This is a comment. */


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class HelloController {

    @GetMapping(value = "/hello", produces = { "application/json" })
    public Map<String,String> hello() {
        return Map.of("Service A","Hello from Service A!");
    }

    @GetMapping(value ="/servicea" , produces = { "application/json" })
    public Map<String,String>  servicea() {

        return Map.of("Service A","service A called! using fin client!");
        ////http://localhost:8081/hello
    }

}
