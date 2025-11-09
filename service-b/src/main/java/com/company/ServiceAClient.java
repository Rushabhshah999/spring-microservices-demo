package com.company;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "service-a") // Notice: no URL!
public interface ServiceAClient {

    @GetMapping("/servicea")
    String getServiceA();
}