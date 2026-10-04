package com.example.ecommerce.shipping_service.repository;

import com.example.ecommerce.shipping_service.entity.Shipping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShippingRepository extends JpaRepository<Shipping,Long> {
}
