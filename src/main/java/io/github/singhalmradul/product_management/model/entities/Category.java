package io.github.singhalmradul.product_management.model.entities;

import static jakarta.persistence.EnumType.STRING;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.github.singhalmradul.product_management.model.embeddables.Quantity;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@Entity
public class Category extends IdentifiableEntity<Category> {

    private String name;

    @Enumerated(STRING)
    private Quantity.Unit unitPreference;

    private List<String> images = new ArrayList<>();

    @ToString.Exclude
    @JsonIgnore
    @ManyToMany(mappedBy="categories")
    private Set<Product> products = new HashSet<>();

    private String description;

    public Category addProduct(Product product) {
        products.add(product);
        return this;
    }

    public Category removeProduct(Product product) {
        products.remove(product);
            return this;
    }
}
