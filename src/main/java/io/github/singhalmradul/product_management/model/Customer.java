package io.github.singhalmradul.product_management.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@Entity
public class Customer extends BaseEntity<Customer> {

    private String name;

    private String phoneNumber;

    private String email;

    private String gstin;

    private String description;

    @ToString.Exclude
    @JsonIgnore
    @OneToMany(targetEntity = OrderRequest.class, mappedBy = "customer")
    private List<OrderRequest> orders;
}
