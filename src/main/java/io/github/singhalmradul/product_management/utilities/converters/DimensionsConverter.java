package io.github.singhalmradul.product_management.utilities.converters;

import io.github.singhalmradul.product_management.model.Dimensions;
import jakarta.persistence.AttributeConverter;

public class DimensionsConverter implements AttributeConverter<Dimensions, String> {

    private String getDimensionString(Float dimension) {
        return dimension == null ? "" : dimension.toString();
    }

    private Float getDimension(String dimension) {
        return dimension == null || dimension.isBlank() ? null : Float.parseFloat(dimension);
    }

    @Override
    public String convertToDatabaseColumn(Dimensions dimensions) {
        return String.format(
            "%sx%sx%s",
            getDimensionString(dimensions.length()),
            getDimensionString(dimensions.width()),
            getDimensionString(dimensions.height())
        );
    }

    @Override
    public Dimensions convertToEntityAttribute(String dbData) {
        String[] parts = dbData.split("x");
        return new Dimensions(getDimension(parts[0]), getDimension(parts[1]), getDimension(parts[2]));
    }

}
