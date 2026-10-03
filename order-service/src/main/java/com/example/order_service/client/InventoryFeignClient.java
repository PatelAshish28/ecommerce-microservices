package com.example.order_service.client;

import feign.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient(name = "inventory-service",path = "/api/inventory")
public interface InventoryFeignClient {

    @PutMapping("/{productId}/{quantity}")
    public ResponseEntity<String> updateStock(@PathVariable Long productId,
                                              @PathVariable Integer quantity);

}
