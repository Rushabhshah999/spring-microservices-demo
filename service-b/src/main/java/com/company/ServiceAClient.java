/*
 * Copyright 2025 the original author or authors...
 */
package com.company;

/* This is a comment. */

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@FeignClient(name = "service-a") // Notice: no URL!
public interface ServiceAClient {

    @GetMapping(value ="/servicea" , produces = { "application/json" })
    Map<String,String> getServiceA();
}