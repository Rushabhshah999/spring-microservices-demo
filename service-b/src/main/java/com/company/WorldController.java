package com.company;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class WorldController {

    @Autowired
    private ServiceAClient serviceAClient;

    @Autowired
    private CircuitBreakerFactory circuitBreakerFactory;

    @GetMapping(value = "/world", produces = "application/json")
    public Map<String, String> world() {
        return Map.of("message", "World from Service B!");
    }

    @GetMapping(value = "/feign", produces = "application/json")
    public Map<String, String> feign() {

        // Wrap Feign call in a Resilience4j CircuitBreaker
        return circuitBreakerFactory.create("service-a").run(
                () -> serviceAClient.getServiceA(),
                throwable -> {
                    Map<String, String> map = new HashMap<>();
                    map.put("error", "Service A is unavailable");
                    return map;
                }
        );
    }
}
