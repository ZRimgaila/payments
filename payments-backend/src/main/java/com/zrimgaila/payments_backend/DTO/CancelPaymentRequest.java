package com.zrimgaila.payments_backend.DTO;

import com.zrimgaila.payments_backend.model.Payment;
import com.zrimgaila.payments_backend.validation.ValidateCancellation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@ValidateCancellation
@Setter @Getter @AllArgsConstructor @NoArgsConstructor
public class CancelPaymentRequest {

    private Payment payment;

}
