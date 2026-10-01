package com.example.inventory_service.exception;

public class InsufficientStockException extends RuntimeException {

    public InsufficientStockException(Long productId) {
        super("Insufficient Stock For Product : "+productId);
    }


}
