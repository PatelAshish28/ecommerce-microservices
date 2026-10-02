package com.example.order_service.client;

import com.example.order_service.dto.InventoryResponse;
import com.example.order_service.dto.ReserveInventoryRequest;
import com.example.order_service.exception.InsufficientStockException;
import com.example.order_service.exception.InventoryUnavailableException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
@RequiredArgsConstructor
public class InventoryClient {

    private final RestClient restClient;

    @CircuitBreaker(
            name = "inventoryService",
            fallbackMethod = "inventoryServiceFallback"
    )
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
                .onStatus(
                        status -> status.value() == 409,
                        (request, response) -> {
                            throw new InsufficientStockException(
                                    "Insufficient stock for product: " + productId
                            );
                        }
                )
                .body(InventoryResponse.class);
    }

    public InventoryResponse inventoryServiceFallback(
            Long productId,
            Integer quantity,
            Throwable throwable) {

        throw new InventoryUnavailableException(
                "Inventory service is currently unavailable"
        );
    }
}
