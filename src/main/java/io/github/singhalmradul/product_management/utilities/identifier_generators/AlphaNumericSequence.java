package io.github.singhalmradul.product_management.utilities.identifier_generators;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import org.hibernate.annotations.IdGeneratorType;

@IdGeneratorType(io.github.singhalmradul.product_management.utilities.identifier_generators.AlphaNumericIdentifierGenerator.class)
@Retention(RUNTIME)
@Target({ METHOD, FIELD })
public @interface AlphaNumericSequence {

}
