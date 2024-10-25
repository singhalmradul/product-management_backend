package io.github.singhalmradul.product_management.model.entities;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.FetchType.EAGER;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@Entity
public class OrderRequest extends IdentifiableEntity<OrderRequest> {

    @ManyToOne(optional = false)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    private LocalDate date;

    @Builder.Default
    @OneToMany(
        mappedBy = "order",
        fetch = EAGER,
        cascade = ALL,
        orphanRemoval = true
    )
    private List<OrderProduct> products = new ArrayList<>();

    public OrderRequest addProduct(final OrderProduct product) {
        this.products.add(product);
        product.setOrder(this);
        return this;
    }

    public OrderRequest removeProduct(final OrderProduct product) {
        this.products.remove(product);
        product.setOrder(null);
        return this;
    }

    public boolean isCompleted() {
        return products.stream().allMatch(OrderProduct::isCompleted);
    }
}
