package io.github.singhalmradul.product_management.model;

import static jakarta.persistence.GenerationType.UUID;

import java.util.List;
import java.util.UUID;

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
    @GeneratedValue(strategy = UUID)
    private UUID id;

    @OneToMany
    @JoinTable(
        name = "product_variation",
        inverseJoinColumns = @JoinColumn(name = "product_id"),
        joinColumns = @JoinColumn(name = "variation_id")
    )
    private List<Product> products;
}
