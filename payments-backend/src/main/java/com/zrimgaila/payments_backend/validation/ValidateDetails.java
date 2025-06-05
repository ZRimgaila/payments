package com.zrimgaila.payments_backend.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = DetailsValidator.class)
public @interface ValidateDetails {

    String message() default "Invalid details based on payment_type: " +
            "if payment_type = 'EUR' -> details mandatory, " +
            "if payment_type = 'USD' -> details optional, " +
            "if payment_type = 'EUR & USD' -> no details";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
