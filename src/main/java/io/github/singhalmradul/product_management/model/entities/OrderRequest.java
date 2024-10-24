package io.github.singhalmradul.product_management.model.entities;

import static jakarta.persistence.CascadeType.PERSIST;
import static jakarta.persistence.CascadeType.REMOVE;
import static jakarta.persistence.FetchType.EAGER;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@Entity
public class OrderRequest extends IdentifiableEntity<OrderRequest> {

    @ManyToOne(targetEntity = Customer.class, optional = false)
    private Customer customer;

    private LocalDate date;

    private String pdf;

    @OneToMany(
        targetEntity = OrderProduct.class,
        mappedBy = "order",
        fetch = EAGER,
        cascade = {PERSIST, REMOVE},
        orphanRemoval = true
    )
    private List<OrderProduct> products;

    private boolean fulfilled;
}
