package com.zrimgaila.payments_backend.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = BICcodeValidator.class)
public @interface ValidateBICCode {

    String message() default "Invalid BIC_code based on paymentType: " +
            "if paymentType = 'EUR & USD' -> BIC_code is mandatory. In other cases BIC_code is not used.";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
