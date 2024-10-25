package io.github.singhalmradul.product_management.model.keys;

import static java.util.Objects.hash;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

public record OrderProductKey(UUID order, UUID product) implements Serializable {

    @Override
    public boolean equals(Object o) {
        if (o instanceof OrderProductKey that) {
            return Objects.equals(this.order, that.order)
                && Objects.equals(this.product, that.product)
            ;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return hash(order, product);
    }
}
