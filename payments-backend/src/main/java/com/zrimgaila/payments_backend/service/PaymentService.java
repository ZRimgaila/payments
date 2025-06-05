package com.zrimgaila.payments_backend.service;

import com.zrimgaila.payments_backend.DTO.IdCancellationFeeDTO;
import com.zrimgaila.payments_backend.model.Payment;
import com.zrimgaila.payments_backend.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.zrimgaila.payments_backend.general.AppConstants.*;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository repo;

    public Payment getPaymentById(int id) {
        return repo.findById(id).orElse(null);
    }

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
            case PAYMENT_TYPE_EUR:
                k = CANCELLATION_FEE_FOR_EUR;
                break;
            case PAYMENT_TYPE_USD:
                k = CANCELLATION_FEE_FOR_USD;
                break;
            case PAYMENT_TYPE_EUR_AND_USD:
                k = CANCELLATION_FEE_FOR_EUR_AND_USD;
                break;
        }

        payment.setCancellationDate(currentDate);
        payment.setCancellationFee(hours.multiply(k));
        payment.setStatus(STATUS_CANCELLED);
        repo.save(payment);

        return "Payment " + payment.getId() + " successfully cancelled!";
    }

    public List<Payment> getAllActivePayments() {
        return repo.findByStatusOrderByAmountDesc(STATUS_ACTIVE);
    }

    public Optional<IdCancellationFeeDTO> getPaymentCancellationFeeById(int id) {
        return repo.findIdAndCancellationFeeById(id);
    }
}
