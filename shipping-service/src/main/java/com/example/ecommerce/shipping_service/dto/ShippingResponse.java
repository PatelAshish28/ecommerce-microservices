package com.example.ecommerce.shipping_service.dto;

import com.example.ecommerce.shipping_service.entity.ShippingStatus;

public record ShippingResponse(

        Long id,
        Long orderId,
        ShippingStatus status
) {
}
