package com.company;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Map;

@FeignClient(name = "service-a") // Notice: no URL!
public interface ServiceAClient {

    @GetMapping(value ="/servicea" , produces = { "application/json" })
    Map<String,String> getServiceA();
}