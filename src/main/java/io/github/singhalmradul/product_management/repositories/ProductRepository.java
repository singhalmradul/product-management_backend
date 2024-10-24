package io.github.singhalmradul.product_management.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import io.github.singhalmradul.product_management.model.entities.Product;

public interface ProductRepository extends JpaRepository<Product, String> {

    @Query(value = """
        SELECT p.*
        FROM product p
        WHERE p.name % :query
        ORDER BY similarity(p.name, :query) DESC
        """,
            nativeQuery = true
    )
    List<Product> findByNameSimilar(@Param("query") String name);

    Optional<Product> findByCode(String code);

}
