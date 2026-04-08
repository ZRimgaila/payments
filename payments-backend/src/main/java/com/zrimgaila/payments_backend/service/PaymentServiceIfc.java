package com.zrimgaila.payments_backend.service;

import com.zrimgaila.payments_backend.DTO.IdCancellationFeeDTO;
import com.zrimgaila.payments_backend.general.PaymentStatus;
import com.zrimgaila.payments_backend.model.Payment;

import java.util.List;

public interface PaymentServiceIfc {

    Payment getPaymentById(int id);
    List<Payment> getAllPayments();
    Payment addPayment(Payment payment);
    String cancelPayment(Payment payment);
    List<Payment> getPaymentsByStatusOrderByAmount(PaymentStatus paymentStatus);
    List<Payment> getPaymentsByStatusOrderByCreationDate(PaymentStatus paymentStatus);
    IdCancellationFeeDTO getPaymentCancellationFeeById(int id);
}
