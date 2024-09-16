package io.github.singhalmradul.product_management.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.singhalmradul.product_management.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, UUID> {}