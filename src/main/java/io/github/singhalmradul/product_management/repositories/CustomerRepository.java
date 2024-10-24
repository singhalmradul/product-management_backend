package io.github.singhalmradul.product_management.repositories;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import io.github.singhalmradul.product_management.model.entities.Customer;

public interface CustomerRepository extends JpaRepository<Customer, String> {

    @Query(value = """
        SELECT *
        FROM customer
        WHERE name % :query
        ORDER BY similarity(name, :query) DESC
        """,
        nativeQuery = true
    )
    List<Customer> findByNameSimilar(@Param("query") String name);
}