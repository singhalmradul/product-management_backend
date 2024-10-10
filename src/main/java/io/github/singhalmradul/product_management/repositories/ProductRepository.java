package io.github.singhalmradul.product_management.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import io.github.singhalmradul.product_management.model.Product;

public interface ProductRepository extends JpaRepository<Product, String> {

    @Query(value = """
        SELECT p.*, pv.* -- , v.*
        FROM product p
        LEFT JOIN product_variation pv ON p.id = pv.product_id
        -- LEFT JOIN variation v ON pv.variation_id = v.id
        WHERE p.name % :query
        ORDER BY similarity(p.name, :query) DESC
        """,
        nativeQuery = true
    )
    List<Product> findByNameSimilar(@Param("query") String name);

}
