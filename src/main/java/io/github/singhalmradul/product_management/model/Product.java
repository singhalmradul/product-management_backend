package io.github.singhalmradul.product_management.model;

import static jakarta.persistence.CascadeType.MERGE;
import static jakarta.persistence.CascadeType.PERSIST;
import static jakarta.persistence.EnumType.STRING;
import static java.lang.String.format;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.github.singhalmradul.product_management.utilities.identifier_generators.AlphaNumericSequence;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class Product {

    @Id
    @GeneratedValue
    @AlphaNumericSequence
    private String id;

    private String code;

    private String name;

    @ManyToMany(cascade={PERSIST, MERGE})
    @JoinTable(
        name = "product_category",
        inverseJoinColumns = @JoinColumn(name = "category_id"),
        joinColumns = @JoinColumn(name = "product_id")
    )
    private Set<Category> categories = new HashSet<>();

    private Integer weight;

    @Embedded
    private Dimensions dimensions;

    private Set<String> images = new HashSet<>();

    private String description;

    @ManyToOne
    @JoinTable(
        name = "product_variation",
        inverseJoinColumns = @JoinColumn(name = "variation_id"),
        joinColumns = @JoinColumn(name = "product_id")
    )
    private Variation variation;

    @Enumerated(STRING)
    private Quantity.Unit unitPreference;

    public Quantity.Unit getUnitPreference() {
        if (unitPreference == null && !categories.isEmpty()) {
            return categories
                .stream()
                .findFirst()
                .get()
                .getUnitPreference()
            ;
        }
        return unitPreference;
    }

    @JsonIgnore
    public Set<Product> getVariants() {
        if (variation == null) {
            return Set.of(this);
        }
        return variation.getProducts();
    }

    public String getWeightString() {
        if (weight == null) {
            return "-";
        }
        if (weight >= 1000) {
            return format("%.3f kg", weight / 1000.0);
        } else {
            return format("%d g", weight);
        }
    }

    public Product addCategory(Category category) {
        categories.add(category);
        return this;
    }

    public Product removeCategory(Category category) {
        categories.remove(category);
        return this;
    }

    @Override
    public int hashCode() {
        if (id == null) {
            return super.hashCode();
        }
        return id.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (id == null) {
            return super.equals(obj);
        }
        if (obj instanceof Product product) {
            return id.equals(product.id);
        }
        return false;
    }
}
