package io.github.singhalmradul.product_management.model;

import java.time.LocalDate;
import java.util.List;

import io.github.singhalmradul.product_management.utilities.identifier_generators.AlphaNumericSequence;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class OrderRequest {

    @Id
    @GeneratedValue
    @AlphaNumericSequence
    private String id;

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
