package io.github.singhalmradul.product_management.model;

public record Weight(float value, Unit unit) {

    public Weight {
        if (value <= 0) {
            throw new IllegalArgumentException(String.format("Invalid weight: %f %s", value, unit));
        }
    }

    public enum Unit {
        KG, G
    }
}
