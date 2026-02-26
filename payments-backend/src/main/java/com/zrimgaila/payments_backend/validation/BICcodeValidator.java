package com.zrimgaila.payments_backend.validation;

import com.zrimgaila.payments_backend.model.Payment;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import static com.zrimgaila.payments_backend.general.PaymentType.EUR_AND_USD;

public class BICcodeValidator implements ConstraintValidator<ValidateBICCode, Payment> {

    @Override
    public boolean isValid(Payment payment, ConstraintValidatorContext context) {
        boolean isValid;

        if(payment == null){
            isValid = false;
        } else{
            boolean isEurAndUsd = EUR_AND_USD.equals(payment.getPaymentType());
            boolean hasBicCode = payment.getBICCode() != null && !payment.getBICCode().isEmpty();

            isValid = (isEurAndUsd && hasBicCode) || (!isEurAndUsd && !hasBicCode);
        }

        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(context.getDefaultConstraintMessageTemplate())
                    .addPropertyNode("BICcode")
                    .addConstraintViolation();
        }

        return isValid;
    }
}
