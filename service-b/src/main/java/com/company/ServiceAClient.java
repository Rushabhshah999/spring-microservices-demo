/*
 * Copyright 2025 the original author or authors...
 */
package com.company;

/* This is a comment. */

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;


//name = "service-a" must match  spring.application.name of the provider.
//No url is needed.
//How Feign finds the service Feign → Spring Cloud LoadBalancer → Eureka → Service Instance
//So this: @FeignClient(name = "post-service")  is logically equivalent to:
//http://<any-running-instance-of-post-service>

@FeignClient(name = "service-a" ,  configuration = ServiceAFeignConfig.class) // Notice: no URL!
public interface ServiceAClient {

    @GetMapping(value ="/servicea" , produces = { "application/json" })
    Map<String,String> getServiceA();
}