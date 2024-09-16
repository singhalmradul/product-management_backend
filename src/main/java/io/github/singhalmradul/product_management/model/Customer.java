package io.github.singhalmradul.product_management.model;

import static jakarta.persistence.GenerationType.UUID;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = UUID)
    UUID id;

    String name;

    String phoneNumber;

    String email;

    String gstin;

    String description;
}
