package com.example.inventory_service.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.client.RestClient;

@Configuration
public class RestClientConfig {

    // Normal RestClient Builder
    // Used for normal HTTP communication such as Eureka
    @Bean
    @Primary
    public RestClient.Builder restClientBuilder() {
        return RestClient.builder();
    }

    // Load-balanced RestClient Builder
    // Used when calling another microservice by service name
    @Bean
    @LoadBalanced
    public RestClient.Builder loadBalancedRestClientBuilder() {
        return RestClient.builder();
    }

    // RestClient used by our application
    @Bean
    public RestClient restClient() {
        return loadBalancedRestClientBuilder().build();
    }
}