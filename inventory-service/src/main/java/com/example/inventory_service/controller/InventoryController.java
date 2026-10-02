package com.example.inventory_service.controller;

import com.example.inventory_service.client.OrderClient;
import com.example.inventory_service.dto.InventoryRequest;
import com.example.inventory_service.dto.InventoryResponse;
import com.example.inventory_service.dto.ReserveInventoryRequest;
import com.example.inventory_service.service.InventoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    private final OrderClient orderClient;

    @GetMapping("/fetchOrders")
    public String fetchOrders() {

        return orderClient.fetchOrders();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public InventoryResponse createInventory(
            @Valid @RequestBody InventoryRequest request) {

        return inventoryService.createInventory(request);
    }

    @GetMapping("/{productId}")
    public InventoryResponse getInventory(
            @PathVariable Long productId) {

        return inventoryService.getByProductId(productId);
    }

    @PostMapping("/{productId}/reserve")
    public ResponseEntity<InventoryResponse> reserveStock(
            @PathVariable Long productId,
            @Valid @RequestBody ReserveInventoryRequest request) {

        return ResponseEntity.ok(
                inventoryService.reserveStock(
                        productId,
                        request.quantity()
                )
        );
    }

}