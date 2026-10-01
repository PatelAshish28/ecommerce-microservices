package com.example.inventory_service.exception;

public class InventoryNotFound extends RuntimeException{

    public InventoryNotFound(Long productId) {
        super("inventory Not Found : "+productId);
    }
}
