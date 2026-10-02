package com.ecommerce.product_service.dto;

public record InventoryResponse(
        Long id,
        Long productId,
        Integer quantity
) {
}
