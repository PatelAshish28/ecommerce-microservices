package com.example.inventory_service.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record ReserveInventoryRequest(

        @NotNull(message = "Quantity Is Required")
        @Min(value = 1,message = "Quantity Must Be At least 1")
        Integer quantity
) {
}
