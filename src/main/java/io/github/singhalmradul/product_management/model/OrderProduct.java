package io.github.singhalmradul.product_management.model;

import org.springframework.util.Assert;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class OrderProduct {

    @Id
    private String id;

    @ManyToOne(targetEntity = Product.class, optional = false)
    @JoinColumn(name = "product_id")
    private Product product;

    @Embedded
    private Quantity quantity;

    public OrderProduct(Product product, Quantity quantity) {
        Assert.notNull(product, "Product must not be null");
        Assert.notNull(quantity, "Quantity must not be null");
        this.product = product;
        this.quantity = quantity;
    }
}
