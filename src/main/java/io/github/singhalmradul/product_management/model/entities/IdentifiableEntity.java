package io.github.singhalmradul.product_management.model.entities;

import static jakarta.persistence.GenerationType.UUID;

import java.util.Objects;
import java.util.UUID;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;

@Data
@MappedSuperclass
public abstract class IdentifiableEntity<T> extends AuditableEntity{

    @Id
    @GeneratedValue(strategy = UUID)
    private UUID id;

    @Override
    public boolean equals(Object o) {
        if (o instanceof IdentifiableEntity<?> that) {
            return Objects.equals(this.getId(), that.getId());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return getId().hashCode();
    }

}
