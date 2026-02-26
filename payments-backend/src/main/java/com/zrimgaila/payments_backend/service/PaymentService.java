package com.zrimgaila.payments_backend.service;

import com.zrimgaila.payments_backend.DTO.IdCancellationFeeDTO;
import com.zrimgaila.payments_backend.general.PaymentStatus;
import com.zrimgaila.payments_backend.model.Payment;
import com.zrimgaila.payments_backend.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

import static com.zrimgaila.payments_backend.general.AppConstants.*;

@Service
@Transactional(rollbackFor = Exception.class)
public class PaymentService implements PaymentServiceIfc{

    @Autowired
    private PaymentRepository repo;

    @Transactional(readOnly = true)
    public Payment getPaymentById(int id) {
        return repo.findById(id).orElse(null);
    }

    @Transactional(readOnly = true)
    public List<Payment> getAllPayments() {
        return repo.findAll();
    }

    public Payment addPayment(Payment payment){
        return repo.save(payment);
    }

    public String cancelPayment(Payment payment) {
        LocalDateTime currentDate = LocalDateTime.now();
        BigDecimal hours = BigDecimal.valueOf(Duration.between(payment.getCreationDate(), currentDate).toHours());

        BigDecimal k = BigDecimal.valueOf(0);
        switch(payment.getPaymentType()) {
            case EUR:
                k = CANCELLATION_FEE_FOR_EUR;
                break;
            case USD:
                k = CANCELLATION_FEE_FOR_USD;
                break;
            case EUR_AND_USD:
                k = CANCELLATION_FEE_FOR_EUR_AND_USD;
                break;
        }

        payment.setCancellationDate(currentDate);
        payment.setCancellationFee(hours.multiply(k));
        payment.setStatus(PaymentStatus.CANCELLED);
        repo.save(payment);

        return "Payment " + payment.getId() + " successfully cancelled!";
    }

    @Transactional(readOnly = true)
    public List<Payment> getAllActivePayments() {
        return repo.findByStatusOrderByAmountDesc(String.valueOf(PaymentStatus.ACTIVE));
    }

    @Transactional(readOnly = true)
    public IdCancellationFeeDTO getPaymentCancellationFeeById(int id) {
        return repo.findIdAndCancellationFeeById(id, PaymentStatus.CANCELLED);
    }
}
