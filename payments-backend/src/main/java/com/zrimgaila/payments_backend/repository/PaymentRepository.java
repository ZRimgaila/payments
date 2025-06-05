package com.zrimgaila.payments_backend.repository;

import com.zrimgaila.payments_backend.DTO.IdCancellationFeeDTO;
import com.zrimgaila.payments_backend.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer> {

    @Query("SELECT p FROM Payment p WHERE p.status = ?1 ORDER BY p.amount DESC")
    List<Payment> findByStatusOrderByAmountDesc(String status);

    @Query("SELECT p.id, p.cancellationFee FROM Payment p WHERE p.id = ?1 and p.status='cancelled'")
    Optional<IdCancellationFeeDTO> findIdAndCancellationFeeById(int id);
}
