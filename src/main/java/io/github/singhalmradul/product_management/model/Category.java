package io.github.singhalmradul.product_management.model;

import static jakarta.persistence.EnumType.STRING;

import java.util.List;

import io.github.singhalmradul.product_management.utilities.identifier_generators.AlphaNumericSequence;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.Data;

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

    private List<String> images;

    @ManyToMany
    @JoinTable(
        name = "product_category",
        joinColumns = @JoinColumn(name = "category_id"),
        inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<Product> products;

    private String description;
}
