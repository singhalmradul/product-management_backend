package io.github.singhalmradul.product_management.model;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Transient;
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

    @Transient
    private List<ProductWithQuantityRecord> products;

    private boolean fulfilled;
}
