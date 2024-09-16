package io.github.singhalmradul.product_management.model;

public record Dimensions(Float length, Float width, Float height) {

    public Dimensions {
        if (length != null && length <= 0 || width != null && width <= 0 || height != null && height <= 0) {
            throw new IllegalArgumentException(String.format("Invalid dimensions: %f x %f x %f", length, width, height));
        }
    }
}
