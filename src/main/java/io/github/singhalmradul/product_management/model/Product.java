package io.github.singhalmradul.product_management.model;

import static jakarta.persistence.EnumType.STRING;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.github.singhalmradul.product_management.utilities.converters.DimensionsConverter;
import io.github.singhalmradul.product_management.utilities.identifier_generators.AlphaNumericSequence;
import jakarta.persistence.Convert;
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

    @ManyToMany
    @JoinTable(
            name = "product_category",
            inverseJoinColumns = @JoinColumn(name = "category_id"),
            joinColumns = @JoinColumn(name = "product_id")
    )
    private List<Category> category;

    private Integer weight;

    @Convert(converter = DimensionsConverter.class)
    private Dimensions dimensions;

    private List<String> images;

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

    @JsonIgnore
    public List<Product> getVariants() {
        if (variation == null) {
            return List.of(this);
        }
        return variation.getProducts();
    }

    public String getWeightString() {
        StringBuilder weightString = new StringBuilder();
        if (weight != null) {
            if (weight >= 1000) {
                weightString.append(weight / 1000.0);
                weightString.append(" kg");
            } else {
                weightString.append(weight);
                weightString.append(" g");
            }
        } else {
            weightString.append("-");
        }
        return weightString.toString();
    }
}
