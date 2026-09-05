package com.example.inventory_service.dto;

public record InventoryResponse(
        Long id,
        Long productId,
        Integer quantity
) {
}