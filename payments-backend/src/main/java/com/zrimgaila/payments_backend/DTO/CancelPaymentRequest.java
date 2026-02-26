package com.zrimgaila.payments_backend.DTO;

import com.zrimgaila.payments_backend.model.Payment;
import com.zrimgaila.payments_backend.validation.CancellationValidator;
import com.zrimgaila.payments_backend.validation.ValidateCancellation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@ValidateCancellation
@NoArgsConstructor
public class CancelPaymentRequest {

    private Payment payment;

    public CancelPaymentRequest(Payment payment){
        this.payment = payment;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }
}
