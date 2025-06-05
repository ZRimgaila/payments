package com.zrimgaila.payments_backend.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;
import java.util.List;

import static com.zrimgaila.payments_backend.general.AppConstants.*;

public class PaymentTypeValidator implements ConstraintValidator<ValidatePaymentType, String> {
    @Override
    public boolean isValid(String paymentType, ConstraintValidatorContext context) {
        List<String> paymentTypes = Arrays.asList(PAYMENT_TYPE_EUR, PAYMENT_TYPE_USD, PAYMENT_TYPE_EUR_AND_USD);
        return paymentTypes.contains(paymentType);
    }
}
