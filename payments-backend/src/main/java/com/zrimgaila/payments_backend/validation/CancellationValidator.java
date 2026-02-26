package com.zrimgaila.payments_backend.validation;

import com.zrimgaila.payments_backend.DTO.CancelPaymentRequest;
import com.zrimgaila.payments_backend.general.PaymentStatus;
import com.zrimgaila.payments_backend.model.Payment;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDateTime;

import static com.zrimgaila.payments_backend.general.PaymentStatus.*;

public class CancellationValidator implements ConstraintValidator<ValidateCancellation, CancelPaymentRequest> {
    @Override
    public boolean isValid(CancelPaymentRequest request, ConstraintValidatorContext context) {
        boolean isValid;

        if(request == null){
            isValid = false;
        } else{
            Payment payment = request.getPayment();
            boolean isActive = ACTIVE.equals(payment.getStatus());
            boolean isCreatedToday = payment.getCreationDate().getDayOfMonth() == LocalDateTime.now().getDayOfMonth();
            if(!isActive){
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate("The payment is already cancelled")
                        .addConstraintViolation();
            }
            isValid = (isActive && isCreatedToday);
        }

        return isValid;
    }
}
