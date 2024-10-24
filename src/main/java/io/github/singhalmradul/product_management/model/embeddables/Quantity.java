package io.github.singhalmradul.product_management.model.embeddables;

import static jakarta.persistence.EnumType.STRING;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Enumerated;

@Embeddable
public record Quantity(
        int amount,
        @Enumerated(STRING) Unit unit
    ) {

    public Quantity  {
        if (amount <= 0) {
            throw new IllegalArgumentException(
                String.format("Invalid quantity: %d %s", amount, unit)
            );
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
                .toString();
    }
}
