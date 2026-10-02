package com.ecommerce.product_service.controller;

import com.ecommerce.product_service.client.InventoryClient;
import com.ecommerce.product_service.dto.InventoryResponse;
import com.ecommerce.product_service.dto.ProductRequest;
import com.ecommerce.product_service.dto.ProductResponse;
import com.ecommerce.product_service.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    private final InventoryClient inventoryClient;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductResponse createProduct(
            @Valid @RequestBody ProductRequest request) {

        return productService.createProduct(request);
    }

    @GetMapping("/{id}")
    public ProductResponse getProductById(
            @PathVariable Long id) {

        return productService.getProductById(id);
    }

    @GetMapping
    public List<ProductResponse> getAllProducts() {

        return productService.getAllProducts();
    }

    @GetMapping("/inventory/{productId}")
    public InventoryResponse getInventoryById(
            @PathVariable Long productId
    ){
      return inventoryClient.getByProductId(productId);
    }
}
