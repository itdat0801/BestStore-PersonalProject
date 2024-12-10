package com.personal.dat.be.best_store_server.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.time.LocalDate;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = { DobValidator.class})
public @interface DobConstraint {
    String message() default "Invalid date of birth";
    int min();
    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
