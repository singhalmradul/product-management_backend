package io.github.singhalmradul.product_management.model;

import static jakarta.persistence.GenerationType.UUID;

import java.time.LocalDate;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class OrderRequest {

    @Id
    @GeneratedValue(strategy = UUID)
    private UUID id;

    @ManyToOne(targetEntity = Customer.class, optional = false)
    private Customer customer;

    private LocalDate date;

    private String pdf;
}
