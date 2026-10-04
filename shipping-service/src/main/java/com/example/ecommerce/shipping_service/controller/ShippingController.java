package com.example.ecommerce.shipping_service.controller;

import com.example.ecommerce.shipping_service.dto.ShippingResponse;
import com.example.ecommerce.shipping_service.service.ShippingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/shipping")
@RequiredArgsConstructor
public class ShippingController {

    private final ShippingService shippingService;

    @PostMapping("/{orderId}")
    public ResponseEntity<ShippingResponse> shippingOrder(
            @PathVariable Long orderId
    ){

        return  shippingService.shippingOrder(orderId);
    }

}
