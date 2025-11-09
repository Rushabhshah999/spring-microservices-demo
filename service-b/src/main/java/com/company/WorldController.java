package com.company;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WorldController {

    @Autowired
    private  ServiceAClient serviceAClient;

    @GetMapping("/world")
    public String world() throws InterruptedException{
        Thread.sleep(1000);
        return "World from Service B!";
        //http://localhost:8082/world
    }

    @GetMapping("/feign")
    public String feign() throws InterruptedException{

       String str =  serviceAClient.getServiceA();

        return str;
        //http://localhost:8082/world
    }
}
