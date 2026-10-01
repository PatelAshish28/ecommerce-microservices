package com.example.order_service.dto;

public record InventoryResponse(

        Long id,
        Long productId,
        Integer quantity
) {
}
