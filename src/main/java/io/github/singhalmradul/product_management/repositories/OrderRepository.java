package io.github.singhalmradul.product_management.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.singhalmradul.product_management.model.entities.OrderRequest;

public interface OrderRepository extends JpaRepository<OrderRequest, String> {}
