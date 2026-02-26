package com.zrimgaila.payments_backend.validation;

import com.zrimgaila.payments_backend.general.PaymentType;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;
import java.util.List;

import static com.zrimgaila.payments_backend.general.PaymentType.*;

public class PaymentTypeValidator implements ConstraintValidator<ValidatePaymentType, PaymentType> {
    @Override
    public boolean isValid(PaymentType paymentType, ConstraintValidatorContext context) {
        List<PaymentType> paymentTypes = Arrays.asList(EUR, USD, EUR_AND_USD);
        return paymentTypes.contains(paymentType);
    }
}
