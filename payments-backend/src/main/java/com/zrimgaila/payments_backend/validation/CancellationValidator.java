package com.zrimgaila.payments_backend.validation;

import com.zrimgaila.payments_backend.DTO.CancelPaymentRequest;
import com.zrimgaila.payments_backend.model.Payment;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDateTime;

import static com.zrimgaila.payments_backend.general.AppConstants.STATUS_ACTIVE;

public class CancellationValidator implements ConstraintValidator<ValidateCancellation, CancelPaymentRequest> {
    @Override
    public boolean isValid(CancelPaymentRequest request, ConstraintValidatorContext context) {
        boolean isValid = true;
        if(request != null){
            Payment payment = request.getPayment();
            if(STATUS_ACTIVE.equals(payment.getStatus()) && payment.getCreationDate().getDayOfMonth() < LocalDateTime.now().getDayOfMonth()){
                isValid = false;
            }
        }
        return isValid;
    }
}
