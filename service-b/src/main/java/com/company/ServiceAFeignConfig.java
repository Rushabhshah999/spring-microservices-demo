package com.company;

import feign.Logger;
import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceAFeignConfig {

    @Bean
    public RequestInterceptor customRequestInterceptor() {
        return template -> {
            template.header("X-Custom-Header-Rushabh", "hello-feign-rushabh");
        };
    }

    @Bean
    public Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }
}
