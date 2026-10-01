package com.example.order_service.client;

import com.example.order_service.dto.InventoryResponse;
import com.example.order_service.dto.ReserveInventoryRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
@RequiredArgsConstructor
public class InventoryClient {

    private final RestClient restClient;

    public InventoryResponse reserveStock(
            Long productId,
            Integer quantity
    ){

        return restClient
                .post()
                .uri(
                        "http://inventory-service/api/inventory/{productId}/reserve",
                        productId
                )
                .body(
                        new ReserveInventoryRequest(quantity)
                )
                .retrieve()
                .body(InventoryResponse.class);
    }
}
