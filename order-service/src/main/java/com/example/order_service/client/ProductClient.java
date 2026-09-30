package com.example.order_service.client;

import com.example.order_service.dto.ProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
@RequiredArgsConstructor
public class ProductClient {

    private final RestClient restClient;

    public ProductResponse getProductById(Long productId){

        return restClient
                .get()
                .uri("http://product-service/api/products/{id}",productId)
                .retrieve()
                .body(ProductResponse.class);
    }
}
