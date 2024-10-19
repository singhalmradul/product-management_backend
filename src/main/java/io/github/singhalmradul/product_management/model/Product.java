package io.github.singhalmradul.product_management.model;

import static jakarta.persistence.CascadeType.MERGE;
import static jakarta.persistence.CascadeType.PERSIST;
import static jakarta.persistence.EnumType.STRING;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@Entity
@Table(name = "product")
public class Product extends BaseEntity<Product> implements ProductProjection {

    @Column(name = "code", nullable = false, length = 15)
    private String code;

    @Column(name = "name", nullable = false, length = 63)
    private String name;

    @Column(name = "weight", nullable = false)
    private Integer weight;

    @Column(name = "length", precision = 5, scale = 2)
    private Float length;

    @Column(name = "width", precision = 5, scale = 2)
    private Float width;

    @Column(name = "height", precision = 5, scale = 2)
    private Float height;

    @Column(name = "images", columnDefinition = "VARCHAR(255)[]")
    private Set<String> images = new HashSet<>();

    @Column(name = "description", length = 255)
    private String description;

    @Enumerated(STRING)
    @Column(name = "unit_preference", length = 10)
    private Quantity.Unit unitPreference;

    @Column(name = "variant_group_id", columnDefinition = "VARCHAR(36)")
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
