package com.example.ecommerce.shipping_service.service;

import com.example.ecommerce.shipping_service.dto.ShippingResponse;
import com.example.ecommerce.shipping_service.entity.Shipping;
import com.example.ecommerce.shipping_service.entity.ShippingStatus;
import com.example.ecommerce.shipping_service.repository.ShippingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ShippingService {

    private final ShippingRepository shippingRepository;

    public ResponseEntity<ShippingResponse> shippingOrder(Long orderId) {

        Shipping shipping = new Shipping();

        shipping.setOrderId(orderId);
        shipping.setStatus(ShippingStatus.PICKED_UP);

        Shipping response=shippingRepository.save(shipping);

        ShippingResponse response1=new ShippingResponse(
                response.getId(),
                response.getOrderId(),
                response.getStatus()
        );

        return ResponseEntity.ok(response1);
    }

}
