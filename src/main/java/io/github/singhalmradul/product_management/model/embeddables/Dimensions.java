package io.github.singhalmradul.product_management.model.embeddables;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class Dimensions {

    private Float length;
    private Float width;
    private Float height;
}
