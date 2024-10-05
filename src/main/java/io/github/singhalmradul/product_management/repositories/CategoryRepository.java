package io.github.singhalmradul.product_management.repositories;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import io.github.singhalmradul.product_management.model.Category;

public interface CategoryRepository extends JpaRepository<Category, String> {

    Optional<Category> findByName(String name);

    @Query(value = """
        SELECT *
        FROM category
        WHERE name % :query
        ORDER BY similarity(name, :query) DESC
        """,
        nativeQuery = true
    )
    List<Category> findByNameSimilar(@Param("query") String name);
}
