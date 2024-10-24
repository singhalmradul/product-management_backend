package io.github.singhalmradul.product_management.model.entities;

import static java.time.LocalDateTime.now;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Data;

@Data
@MappedSuperclass
public abstract class AuditableEntity {

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
}
