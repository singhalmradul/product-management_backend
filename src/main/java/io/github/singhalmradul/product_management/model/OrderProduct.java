package io.github.singhalmradul.product_management.model;

import org.springframework.util.Assert;

public record OrderProduct(Product product, Quantity quantity) {

    public OrderProduct {
        Assert.notNull(product, "Product cannot be null");
        Assert.notNull(quantity, "Quantity cannot be null");
    }
}
