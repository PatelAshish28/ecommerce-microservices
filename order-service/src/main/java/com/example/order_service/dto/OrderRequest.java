package com.example.order_service.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record OrderRequest(

        @NotNull(message = "Product Id is Required")
        Long productId,

        @NotNull(message = "Quantity is Required")
        @Min(value = 1,message = "Quantity Must Be At Least 1")
        Integer quantity
        ) {

}
