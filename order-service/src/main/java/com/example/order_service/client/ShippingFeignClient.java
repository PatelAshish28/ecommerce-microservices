package com.example.order_service.client;

import com.example.order_service.dto.ShippingResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "shipping-service",path = "/api/shipping")
public interface ShippingFeignClient {

    @PostMapping("/{orderId}")
    ResponseEntity<ShippingResponse> shippingOrder(@PathVariable Long orderId);

}
