package io.github.singhalmradul.product_management.model.entities;

import jakarta.persistence.MappedSuperclass;
import lombok.Data;

@Data
@MappedSuperclass
public abstract class AuditableEntity {

    // @JsonIgnore
    // private LocalDateTime createdAt;

    // @JsonIgnore
    // private LocalDateTime updatedAt;

    // @PrePersist
    // protected void onCreate() {
    //     createdAt = now();
    //     updatedAt = now();
    // }

    // @PreUpdate
    // protected void onUpdate() {
    //     updatedAt = now();
    // }
}
