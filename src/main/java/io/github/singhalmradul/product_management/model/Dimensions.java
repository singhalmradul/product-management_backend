package io.github.singhalmradul.product_management.model;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class Dimensions {

    private Float length;
    private Float width;
    private Float height;
}
