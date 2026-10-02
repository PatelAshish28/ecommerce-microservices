package com.ecommerce.product_service.client;

import com.ecommerce.product_service.dto.InventoryResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "inventory-service",path = "/api/inventory")
public interface InventoryClient {

    @GetMapping("/{productId}")
    public InventoryResponse getByProductId(@PathVariable Long productId);

}
