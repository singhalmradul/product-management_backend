package io.github.singhalmradul.product_management.model;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@Entity
public class OrderRequest extends BaseEntity<OrderRequest> {

    @ManyToOne(targetEntity = Customer.class, optional = false)
    private Customer customer;

    private LocalDate date;

    private String pdf;

    @ManyToMany(targetEntity = OrderedProduct.class)
    @JoinTable(
        name = "order_product",
        inverseJoinColumns = @JoinColumn(name = "product_id"),
        joinColumns = @JoinColumn(name = "order_id")
    )
    private List<OrderedProduct> products;
}
