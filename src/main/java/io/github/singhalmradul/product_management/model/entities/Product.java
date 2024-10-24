package io.github.singhalmradul.product_management.model.entities;

import static jakarta.persistence.CascadeType.MERGE;
import static jakarta.persistence.CascadeType.PERSIST;
import static jakarta.persistence.EnumType.STRING;

import java.util.HashSet;
import java.util.Set;

import io.github.singhalmradul.product_management.model.embeddables.Quantity;
import io.github.singhalmradul.product_management.model.projections.ProductProjection;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@Entity
public class Product extends IdentifiableEntity<Product> implements ProductProjection {

    private String code;

    private String name;

    private Integer weight;

    private Float length;

    private Float width;

    private Float height;

    private Set<String> images = new HashSet<>();

    private String description;

    @Enumerated(STRING)
    private Quantity.Unit unitPreference;

    private String variantGroupId;

    @ManyToMany(cascade = { PERSIST, MERGE })
    @JoinTable(
        name = "product_category",
        inverseJoinColumns = @JoinColumn(name = "category_id"),
        joinColumns = @JoinColumn(name = "product_id")
    )
    private Set<Category> categories = new HashSet<>();

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

    public Product addCategory(Category category) {
        categories.add(category);
        return this;
    }

    public Product removeCategory(Category category) {
        categories.remove(category);
        return this;
    }
}
