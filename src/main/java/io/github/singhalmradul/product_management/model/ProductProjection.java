package io.github.singhalmradul.product_management.model;

import static java.lang.String.format;

import java.util.Collection;

public interface ProductProjection {

    String getName();

    String getCode();

    Integer getWeight();

    Collection<Category> getCategories();

    Collection<String> getImages();

    default String getWeightString() {
        if (getWeight() == null) {
            return "-";
        }
        if (getWeight() >= 1000) {
            return format("%.3f kg", getWeight() / 1000.0);
        } else {
            return format("%d g", getWeight());
        }
    }
}