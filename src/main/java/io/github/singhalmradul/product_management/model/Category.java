package io.github.singhalmradul.product_management.model;

import static jakarta.persistence.EnumType.STRING;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.github.singhalmradul.product_management.utilities.identifier_generators.AlphaNumericSequence;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Data;
import lombok.ToString;

@Data
@Entity
public class Category {

    @Id
    @GeneratedValue
    @AlphaNumericSequence
    private String id;

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
