package com.zrimgaila.payments_backend.validation;

import com.zrimgaila.payments_backend.model.Payment;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
public class DetailsValidator implements ConstraintValidator<ValidateDetails, Payment> {
    @Override
    public boolean isValid(Payment payment, ConstraintValidatorContext context) {
        boolean isValid = true;
        if(payment != null){
            if("EUR".equalsIgnoreCase(payment.getPaymentType()) && payment.getDetails() != null && !payment.getDetails().isEmpty()){
                isValid = true;
            } else if("USD".equalsIgnoreCase(payment.getPaymentType())){
                isValid = true;
            } else if("EUR & USD".equalsIgnoreCase(payment.getPaymentType()) && (payment.getDetails() == null || payment.getDetails().isEmpty())){
                isValid = true;
            } else {
                isValid = false;
            }
        } else {
            isValid = false;
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
