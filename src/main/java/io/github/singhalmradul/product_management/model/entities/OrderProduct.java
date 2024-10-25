package io.github.singhalmradul.product_management.model.entities;

import static jakarta.persistence.FetchType.LAZY;
import static java.util.Objects.hash;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.github.singhalmradul.product_management.model.embeddables.Quantity;
import io.github.singhalmradul.product_management.model.keys.OrderProductKey;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.ToString;

@Data
@IdClass(OrderProductKey.class)
@Entity
public class OrderProduct extends AuditableEntity {

    @Embedded
    private Quantity quantity;

    private boolean completed;

    @Id
    @ManyToOne(optional = false)
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;

    @Id
    @ToString.Exclude
    @JsonIgnore
    @ManyToOne(optional = false, fetch = LAZY)
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private OrderRequest order;

    @Override
    public boolean equals(Object o) {
        if (o instanceof OrderProduct that) {
            return Objects.equals(this.order, that.order)
                && Objects.equals(this.product, that.product)
            ;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return hash(product, order);
    }

    public boolean isPending() {
        return !completed;
    }
}
