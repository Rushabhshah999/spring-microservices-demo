package com.company;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class WorldController {

    @Autowired
    private  ServiceAClient serviceAClient;

    @GetMapping(value ="/world" , produces = { "application/json" })
    public String world() throws InterruptedException{
        Thread.sleep(1000);
        return "World from Service B!";
        //http://localhost:8082/world
    }

    @GetMapping(value ="/feign" , produces = { "application/json" })
    public Map<String,String> feign() throws InterruptedException{

       //String str =  serviceAClient.getServiceA();
        return Map.of("Service A",serviceAClient.getServiceA().toString());
       // return str;
        //http://localhost:8082/world
    }
}
