package com.zrimgaila.payments_backend.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = PaymentTypeValidator.class)
public @interface ValidatePaymentType {

    public String message() default "Invalid paymentType: It should be 'EUR', 'USD' OR 'EUR & USD'";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}
