package com.example.order_service.service;

import com.example.order_service.client.InventoryClient;
import com.example.order_service.client.InventoryFeignClient;
import com.example.order_service.client.ProductClient;
import com.example.order_service.dto.OrderRequest;
import com.example.order_service.dto.OrderResponse;
import com.example.order_service.dto.ProductResponse;
import com.example.order_service.entity.Order;
import com.example.order_service.entity.OrderStatus;
import com.example.order_service.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    private final ProductClient productClient;

    private final InventoryClient inventoryClient;

    private final InventoryFeignClient inventoryFeignClient;

    public OrderResponse createOrder(OrderRequest request) {

        //1.Check Product
        ProductResponse product = productClient.getProductById(request.productId());

        //2.Calculate Total Price
        BigDecimal totalPrice = product.price()
                .multiply(BigDecimal.valueOf(request.quantity()));

        //3.Reserve Inventory
        inventoryClient.reserveStock(request.productId(), request.quantity());

        //4.Create Order
        Order order = Order.builder()
                .productId(request.productId())
                .quantity(request.quantity())
                .totalPrice(totalPrice)
                .status(OrderStatus.CREATED)
                .build();

        //5.Save Order
        Order savedOrder = orderRepository.save(order);

        return new OrderResponse(
                savedOrder.getId(),
                savedOrder.getProductId(),
                savedOrder.getQuantity(),
                savedOrder.getTotalPrice(),
                savedOrder.getStatus()
        );
    }

    public ResponseEntity<String> cancelOrder(Long orderId) {

        log.info("Request Came in Cancel Order");
        Order order=orderRepository.findById(orderId).orElseThrow(
                ()->new RuntimeException("Order Not Found")
        );

        order.setStatus(OrderStatus.CANCELLED);
        log.info("Order Status Got Cancelled");

        Order updatedOrder=orderRepository.save(order);

        inventoryFeignClient.updateStock(order.getProductId(),order.getQuantity());
        log.info("Stock Updated");

        return ResponseEntity.ok("Order Cancelled");
    }
}