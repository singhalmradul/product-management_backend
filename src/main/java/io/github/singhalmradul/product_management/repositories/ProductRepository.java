package io.github.singhalmradul.product_management.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.singhalmradul.product_management.model.Product;

public interface ProductRepository extends JpaRepository<Product, UUID> {

    Optional<Product> findByCode(String code);

    void deleteByCode(String code);
}
