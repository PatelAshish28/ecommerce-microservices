package com.example.inventory_service.client;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
@RequiredArgsConstructor
public class OrderClient {

    private final RestClient restClient;

    public String fetchOrders(){

        return restClient.get()
                .uri("http://order-service"+"/api/orders/helloOrders")
                .retrieve()
                .body(String.class);
    }
}
