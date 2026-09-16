package com.example.inventory_service.client;

import com.example.inventory_service.dto.ProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
@RequiredArgsConstructor
public class ProductClient {

    private final RestClient restClient;

    @Value("${services.product.url}")
    private String productServiceUrl;

    public ProductResponse getProductById(Long productId) {

        return restClient
                .get()
                .uri(productServiceUrl + "/api/products/{id}", productId)
                .retrieve()
                .body(ProductResponse.class);
    }


}
