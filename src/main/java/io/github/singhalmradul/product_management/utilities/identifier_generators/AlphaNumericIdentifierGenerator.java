package io.github.singhalmradul.product_management.utilities.identifier_generators;

import static org.hibernate.generator.EventType.INSERT;

import java.util.EnumSet;
import java.util.Random;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.generator.BeforeExecutionGenerator;
import org.hibernate.generator.EventType;

public class AlphaNumericIdentifierGenerator implements BeforeExecutionGenerator {

    private static final String ALPHA_NUMERIC_STRING = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final Random RANDOM = new Random();

    @Override
    public EnumSet<EventType> getEventTypes() {
        return EnumSet.of(INSERT);
    }

    @Override
    public Object generate(
        SharedSessionContractImplementor session,
        Object owner,
        Object currentValue,
        EventType eventType
    ) {
        if (currentValue != null) {
            return currentValue;
        }
        return generateRandomAlphaNumericString(8);
    }

    public static String generateRandomAlphaNumericString(int length) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int index = RANDOM.nextInt(ALPHA_NUMERIC_STRING.length());
            builder.append(ALPHA_NUMERIC_STRING.charAt(index));
        }
        return builder.toString();
    }
}