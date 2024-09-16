package io.github.singhalmradul.product_management.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.singhalmradul.product_management.model.Category;

public interface CategoryRepository extends JpaRepository<Category, String> {

    Optional<Category> findByName(String name);

    void deleteByName(String name);
}
