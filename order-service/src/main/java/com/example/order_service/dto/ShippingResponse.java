package com.example.order_service.dto;

public record ShippingResponse(

        Long id,
        Long orderId,
        ShippingStatus status
) {
}
