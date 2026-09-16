package com.example.inventory_service.service;

import com.example.inventory_service.client.ProductClient;
import com.example.inventory_service.dto.InventoryRequest;
import com.example.inventory_service.dto.InventoryResponse;
import com.example.inventory_service.dto.ProductResponse;
import com.example.inventory_service.entity.Inventory;
import com.example.inventory_service.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    private final ProductClient productClient;

    public InventoryService(InventoryRepository inventoryRepository, ProductClient productClient) {
        this.inventoryRepository = inventoryRepository;
        this.productClient = productClient;
    }

    public InventoryResponse getByProductId(Long productId) {

        Inventory inventory = inventoryRepository
                .findByProductId(productId)
                .orElseThrow(() ->
                        new RuntimeException("Inventory not found"));

        return mapToResponse(inventory);
    }

    private InventoryResponse mapToResponse(Inventory inventory) {

        return new InventoryResponse(
                inventory.getId(),
                inventory.getProductId(),
                inventory.getQuantity()
        );
    }

    public InventoryResponse createInventory(InventoryRequest request) {

        ProductResponse product =
                productClient.getProductById(request.productId());

        Inventory inventory = Inventory.builder()
                .productId(product.id())
                .quantity(request.quantity())
                .build();

        Inventory saved = inventoryRepository.save(inventory);

        return mapToResponse(saved);
    }


}
