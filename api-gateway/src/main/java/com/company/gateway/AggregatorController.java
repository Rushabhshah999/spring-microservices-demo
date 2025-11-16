package com.company.gateway;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;

@RestController
public class AggregatorController {

    private static final Logger log = LoggerFactory.getLogger(AggregatorController.class);

    @Autowired
    private DiscoveryClient discoveryClient;

    private final RestTemplate restTemplate = new RestTemplate();

    @GetMapping("/aggregate")
    public String aggregate() throws Exception {
        String serviceAUrl = discoveryClient.getInstances("service-a").get(0).getUri() + "/hello";
        String serviceBUrl = discoveryClient.getInstances("service-b").get(0).getUri() + "/world";

        log.debug("weldone Rushabh Inside");

        CompletableFuture<String> aFuture = CompletableFuture.supplyAsync(() ->
                restTemplate.getForObject(serviceAUrl, String.class))
                .exceptionally(ex -> { return "Service A failed: " + ex.getMessage();
                 });

        CompletableFuture<String> bFuture = CompletableFuture.supplyAsync(() ->
                restTemplate.getForObject(serviceBUrl, String.class))
                .exceptionally(ex -> { return "Service B failed: " + ex.getMessage();});

        CompletableFuture.allOf(aFuture, bFuture).join();

        return "Results merged: [" + aFuture.get() + " | " + bFuture.get() + "]";
    }
    //http://localhost:8080/aggregate
}
