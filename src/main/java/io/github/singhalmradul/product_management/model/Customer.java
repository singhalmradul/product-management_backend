package io.github.singhalmradul.product_management.model;

import io.github.singhalmradul.product_management.utilities.identifier_generators.AlphaNumericSequence;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Customer {

    @Id
    @GeneratedValue
    @AlphaNumericSequence
    private String id;

    private String name;

    private String phoneNumber;

    private String email;

    private String gstin;

    private String description;
}
