package com.zrimgaila.payments_backend.repository;

import com.zrimgaila.payments_backend.DTO.IdCancellationFeeDTO;
import com.zrimgaila.payments_backend.general.PaymentStatus;
import com.zrimgaila.payments_backend.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer> {

    @Query("SELECT p FROM Payment p WHERE p.status = ?1 ORDER BY p.amount DESC")
    List<Payment> findByStatusOrderByAmount(PaymentStatus status);

    @Query("SELECT p FROM Payment p WHERE p.status = ?1 ORDER BY p.creationDate DESC")
    List<Payment> findByStatusOrderByCreationDate(PaymentStatus status);

    @Query("SELECT p.id, p.cancellationFee FROM Payment p WHERE p.id = ?1 and p.status= :status")
    IdCancellationFeeDTO findIdAndCancellationFeeById(int id, @Param("status") PaymentStatus status);
}
