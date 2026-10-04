package com.example.order_service.dto;

import com.example.order_service.entity.OrderStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;

public record OrderResponse(

        Long id,
        Long productId,
        Integer quantity,
        BigDecimal totalPrice,
        OrderStatus status,
        ShippingResponse response
) {
}
