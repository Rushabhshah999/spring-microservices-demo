package com.company;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @GetMapping("/hello")
    public String hello() throws InterruptedException{
        Thread.sleep(3000);
        return "Hello from Service A!";
        ////http://localhost:8081/hello
    }

    @GetMapping("/servicea")
    public String servicea() {


        return "service A called!";
        ////http://localhost:8081/hello
    }

}
