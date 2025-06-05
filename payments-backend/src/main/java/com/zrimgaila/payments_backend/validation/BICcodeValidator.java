package com.zrimgaila.payments_backend.validation;

import com.zrimgaila.payments_backend.model.Payment;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class BICcodeValidator implements ConstraintValidator<ValidateBICCode, Payment> {

    @Override
    public boolean isValid(Payment payment, ConstraintValidatorContext context) {
        boolean isValid = true;

        if(payment != null){
            if("EUR & USD".equalsIgnoreCase(payment.getPaymentType()) &&
                    (payment.getBICCode() != null && !payment.getBICCode().isEmpty())){
                isValid = true;
            } else if(!"EUR & USD".equalsIgnoreCase(payment.getPaymentType())
                    && (payment.getBICCode() == null || payment.getBICCode().isEmpty())){
                isValid = true;
            } else{
                isValid = false;
            }
        } else{
            isValid = false;
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
