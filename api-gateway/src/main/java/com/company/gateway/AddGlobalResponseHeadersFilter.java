package com.company.gateway;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import org.springframework.http.HttpHeaders;

@Component
public class AddGlobalResponseHeadersFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // Continue the chain, then add headers after response is ready
        return chain.filter(exchange).then(Mono.fromRunnable(() -> {
            if (!exchange.getResponse().isCommitted()) {
                HttpHeaders headers = exchange.getResponse().getHeaders();
                headers.add("X-Gateway", "RushabhSpringCloudGateway");
                headers.add("X-API-Version", "v1.0.0");
                headers.add("X-Environment", "Production");
            }
        }));
    }

    @Override
    public int getOrder() {
        // Run after routing, before response is sent
        return -1;
    }
}