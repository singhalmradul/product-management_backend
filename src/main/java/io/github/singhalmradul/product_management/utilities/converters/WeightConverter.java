package io.github.singhalmradul.product_management.utilities.converters;

import io.github.singhalmradul.product_management.model.Weight;
import jakarta.persistence.AttributeConverter;

public class WeightConverter implements AttributeConverter<Weight, String> {

    @Override
    public String convertToDatabaseColumn(Weight weight) {
        return weight.value() + " " + weight.unit();
    }

    @Override
    public Weight convertToEntityAttribute(String dbData) {
        String[] parts = dbData.split(" ");
        return new Weight(Float.parseFloat(parts[0]), Weight.Unit.valueOf(parts[1]));
    }
}
