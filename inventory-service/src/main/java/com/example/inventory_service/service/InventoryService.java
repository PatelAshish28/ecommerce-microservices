package com.example.inventory_service.service;

import com.example.inventory_service.client.ProductClient;
import com.example.inventory_service.dto.InventoryRequest;
import com.example.inventory_service.dto.InventoryResponse;
import com.example.inventory_service.dto.ProductResponse;
import com.example.inventory_service.entity.Inventory;
import com.example.inventory_service.exception.InsufficientStockException;
import com.example.inventory_service.exception.InventoryNotFound;
import com.example.inventory_service.repository.InventoryRepository;
import jakarta.ws.rs.NotFoundException;
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

    public InventoryResponse reserveStock(
            Long productId,Integer quantity
    ){

        Inventory inventory= inventoryRepository
                .findByProductId(productId)
                .orElseThrow(()->
                    new InventoryNotFound(productId));
        if(inventory.getQuantity()<quantity){
            throw new InsufficientStockException(productId);
        }

        inventory.setQuantity(inventory.getQuantity()-quantity);

        Inventory savedInventory=inventoryRepository.save(inventory);

        return new InventoryResponse(
                savedInventory.getId(),
                savedInventory.getProductId(),
                savedInventory.getQuantity()
        );
    }
}