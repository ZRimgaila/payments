package com.zrimgaila.payments_backend.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = CancellationValidator.class)
public @interface ValidateCancellation {

    String message() default "The payment cannot be cancelled because cancellations" +
            " are only allowed on the same day the payment was created.";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
