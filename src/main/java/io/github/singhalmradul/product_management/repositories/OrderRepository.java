package io.github.singhalmradul.product_management.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import io.github.singhalmradul.product_management.model.OrderRequest;
import io.github.singhalmradul.product_management.model.ProductWithQuantityRecord;

public interface OrderRepository extends JpaRepository<OrderRequest, String> {

    @Query(value = """
        SELECT p.name, p.code, p.weight, p.category_id, p.images, op.amount, op.unit
        FROM order_product op
        JOIN product p ON op.product_id = p.id
        WHERE op.order_id = :orderId
    """, nativeQuery = true)
    List<ProductWithQuantityRecord> findByOrderId(@Param("orderId") String orderId);
}
