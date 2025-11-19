/*
 * Copyright 2025 the original author or authors...
 */
package com.company.gateway;

/* This is a comment. */

import org.springframework.cloud.gateway.filter.factory.AddResponseHeaderGatewayFilterFactory;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    private final AddResponseHeaderGatewayFilterFactory addResponseHeaderFilterFactory;

    public GatewayConfig(AddResponseHeaderGatewayFilterFactory addResponseHeaderFilterFactory) {
        this.addResponseHeaderFilterFactory = addResponseHeaderFilterFactory;
    }

    // Helper method to create an AddResponseHeader filter
    private AddResponseHeaderGatewayFilterFactory.NameValueConfig createHeader(String name, String value) {
        AddResponseHeaderGatewayFilterFactory.NameValueConfig config = new AddResponseHeaderGatewayFilterFactory.NameValueConfig();
        config.setName(name);
        config.setValue(value);
        return config;
    }

    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                // Service-A route
                .route("service-a", r -> r.path("/service-a/**")
                        .filters(f -> f.stripPrefix(1)
                                .filters(
                                addResponseHeaderFilterFactory.apply(createHeader("Y-Gateway", "YGateway")),
                                addResponseHeaderFilterFactory.apply(createHeader("Y-API-Version", "v2.0.0")),
                                addResponseHeaderFilterFactory.apply(createHeader("Y-Environment", "Test"))
                        ))
                        .uri("lb://service-a"))

                // Mock route for testing headers
                .route("service-b", r -> r.path("/service-b/**")
                        .filters(f -> f.stripPrefix(1)
                                .filters(
                                        addResponseHeaderFilterFactory.apply(createHeader("X-Gateway", "X-Gateway")),
                                        addResponseHeaderFilterFactory.apply(createHeader("X-API-Version", "v1.0.0")),
                                        addResponseHeaderFilterFactory.apply(createHeader("X-Environment", "Production"))
                                ))
                        .uri("lb://service-b"))

                .build();
    }
}
