package com.company;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class HelloController {
    @GetMapping(value = "/hello", produces = { "application/json" })
    public Map<String,String> hello() throws InterruptedException{
        //Thread.sleep(3000);
        //return "Hello from Service A!";
        return Map.of("Service A","Hello from Service A!");
        ////http://localhost:8081/hello
    }

    @GetMapping(value ="/servicea" , produces = { "application/json" })
    public Map<String,String>  servicea() {

        return Map.of("Service A","service A called! using fin client!");
     //   return "service A called! using fin client";
        ////http://localhost:8081/hello
    }

}
