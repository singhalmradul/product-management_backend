package io.github.singhalmradul.product_management.model;

public interface OrderedProduct extends ProductProjection {

    Integer getAmount();
    Quantity.Unit getUnit();

    default Quantity getQuantity() {
        return new Quantity(getAmount(), getUnit());
    }
}