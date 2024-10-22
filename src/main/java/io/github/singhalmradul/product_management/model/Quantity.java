package io.github.singhalmradul.product_management.model;

import jakarta.persistence.Embeddable;

@Embeddable
public record Quantity(int amount, Unit unit) {

    public Quantity {
        if (amount <= 0) {
            throw new IllegalArgumentException(String.format("Invalid quantity: %d %s", amount, unit));
        }
    }

    public enum Unit {
        KG, PCS, BOXES
    }

    @Override
    public String toString() {
        return new StringBuilder()
            .append(amount)
            .append(' ')
            .append(unit)
            .toString()
        ;
    }
}
