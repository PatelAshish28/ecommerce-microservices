package com.example.inventory_service.client;

import com.example.inventory_service.dto.ProductResponse;
import com.example.inventory_service.exception.ProductServiceUnavailableException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
@Component
@RequiredArgsConstructor
public class ProductClient {

    private final RestClient restClient;

    @CircuitBreaker(
            name = "productService",
            fallbackMethod = "productServiceFallback"
    )
    public ProductResponse getProductById(Long productId) {

        return restClient
                .get()
                .uri("http://product-service/api/products/{id}", productId)
                .retrieve()
                .body(ProductResponse.class);
    }

    public ProductResponse productServiceFallback(
            Long productId,
            Throwable throwable) {

        throw new ProductServiceUnavailableException(
                "Product service is currently unavailable"
        );
    }
}