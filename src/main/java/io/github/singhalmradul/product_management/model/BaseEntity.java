package io.github.singhalmradul.product_management.model;

import static java.time.LocalDateTime.now;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.github.singhalmradul.product_management.utilities.identifier_generators.AlphaNumericSequence;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Data;

@Data
@MappedSuperclass
public abstract class BaseEntity<T> {

    @Id
    @GeneratedValue
    @AlphaNumericSequence
    private String id;

    @JsonIgnore
    private LocalDateTime createdAt;

    @JsonIgnore
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = now();
        updatedAt = now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = now();
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof BaseEntity<?> other) {
            return getId().equals(other.getId());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return getId().hashCode();
    }

}
