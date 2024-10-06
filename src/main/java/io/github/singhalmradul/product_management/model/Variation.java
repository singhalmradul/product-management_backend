package io.github.singhalmradul.product_management.model;

import java.util.HashSet;
import java.util.Set;

import io.github.singhalmradul.product_management.utilities.identifier_generators.AlphaNumericSequence;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Data
@Entity
public class Variation {

    @Id
    @GeneratedValue
    @AlphaNumericSequence
    private String id;

    @OneToMany
    @JoinTable(
        name = "product_variation",
        inverseJoinColumns = @JoinColumn(name = "product_id"),
        joinColumns = @JoinColumn(name = "variation_id")
    )
    private Set<Product> products = new HashSet<>();
}
