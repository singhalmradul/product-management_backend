package io.github.singhalmradul.product_management.model;

import java.util.Collection;

import io.github.singhalmradul.product_management.model.Quantity.Unit;

public record ProductWithQuantityRecord(Product product, Quantity quantity) implements ProductWithQuantity {

    @Override
    public String getName() {
        return product.getName();
    }

    @Override
    public String getCode() {
        return product.getCode();
    }

    @Override
    public Integer getWeight() {
        return product.getWeight();
    }

    @Override
    public Collection<Category> getCategories() {
        return product.getCategories();
    }

    @Override
    public Collection<String> getImages() {
        return product.getImages();
    }

    @Override
    public Integer getAmount() {
        return quantity.amount();
    }

    @Override
    public Unit getUnit() {
        return quantity.unit();
    }

}
