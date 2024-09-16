package io.github.singhalmradul.product_management.model;

public record Quantity(int value, Unit unit) {

    public Quantity {
        if (value <= 0) {
            throw new IllegalArgumentException(String.format("Invalid quantity: %d %s", value, unit));
        }
    }

    public enum Unit {
        KG, PCS, BOXES
    }

    @Override
    public String toString() {
        return new StringBuilder()
            .append(value)
            .append(' ')
            .append(unit)
            .toString()
        ;
    }
}
