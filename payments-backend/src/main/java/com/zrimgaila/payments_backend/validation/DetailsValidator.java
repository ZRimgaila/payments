package com.zrimgaila.payments_backend.validation;

import com.zrimgaila.payments_backend.general.PaymentType;
import com.zrimgaila.payments_backend.model.Payment;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class DetailsValidator implements ConstraintValidator<ValidateDetails, Payment> {
    @Override
    public boolean isValid(Payment payment, ConstraintValidatorContext context) {
        boolean isValid;

        if(payment == null){
            isValid = false;
        } else{
            boolean isEur = PaymentType.EUR.equals(payment.getPaymentType());
            boolean isUsd = PaymentType.USD.equals(payment.getPaymentType());
            boolean isEurAndUsd = PaymentType.EUR_AND_USD.equals(payment.getPaymentType());
            boolean hasDetails = payment.getDetails() != null && !payment.getDetails().isEmpty();

            isValid = (isEur && hasDetails) || isUsd || (isEurAndUsd && !hasDetails);
        }

        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(context.getDefaultConstraintMessageTemplate())
                    .addPropertyNode("details")
                    .addConstraintViolation();
        }

        return isValid;
    }
}
