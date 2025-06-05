package com.zrimgaila.payments_backend.controller;

import com.zrimgaila.payments_backend.DTO.CancelPaymentRequest;
import com.zrimgaila.payments_backend.DTO.IdCancellationFeeDTO;
import com.zrimgaila.payments_backend.model.Payment;
import com.zrimgaila.payments_backend.service.PaymentService;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Valid;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@RestController
@CrossOrigin
public class PaymentController {

    @Autowired
    private PaymentService service;

    @Autowired
    private Validator validator;

    @GetMapping("/payments")
    public ResponseEntity<List<Payment>> getAllPayments(){
        return ResponseEntity.ok(service.getAllPayments());
    }

    @GetMapping("/payments/{id}")
    public ResponseEntity<Payment> getPaymentById(@PathVariable int id){
        return ResponseEntity.ok(service.getPaymentById(id));
    }

    @PostMapping("/payments")
    public ResponseEntity<Payment> createPayment(@Valid @RequestBody Payment payment){

        Payment newPayment = service.addPayment(payment);
        return new ResponseEntity<>(newPayment, HttpStatus.CREATED);
    }

    @PutMapping("/payments/{id}/cancel")
    public ResponseEntity<?> cancelPayment(@PathVariable int id){
        Payment existingPayment = service.getPaymentById(id);
        if(existingPayment != null){
            // Validating cancellation
            CancelPaymentRequest request = new CancelPaymentRequest(existingPayment);
            Set<ConstraintViolation<CancelPaymentRequest>> violations = validator.validate(request);
            if (!violations.isEmpty()) {
                String errorMsg = violations.stream()
                        .map(ConstraintViolation::getMessage)
                        .findFirst()
                        .orElse("Cancellation validation failed");
                return new ResponseEntity<>(errorMsg, HttpStatus.BAD_REQUEST);
            }
            // Setting payment status, cancellation_date and cancellation_fee
            String cancelledPayment = service.cancelPayment(existingPayment);
            return new ResponseEntity<>(cancelledPayment, HttpStatus.OK);
        } else{
            return new ResponseEntity<>("There is no payment with such id", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/payments/active")
    public ResponseEntity<List<Payment>> getAllActivePayments(){
        return ResponseEntity.ok(service.getAllActivePayments());
    }

    @GetMapping("/payments/{id}/cancellationFee")
    public ResponseEntity<?> getPaymentCancellationFeeById(@PathVariable int id){
        Optional<IdCancellationFeeDTO> pair = service.getPaymentCancellationFeeById(id);
        if(pair.isPresent()){
            return new ResponseEntity<>(pair, HttpStatus.OK);
        } else{
            return new ResponseEntity<>("Unable to find " + id + " payment", HttpStatus.NOT_FOUND);
        }
    }
}