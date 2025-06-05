package com.zrimgaila.payments_backend.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import com.zrimgaila.payments_backend.validation.ValidateBICCode;
import com.zrimgaila.payments_backend.validation.ValidateDetails;
import com.zrimgaila.payments_backend.validation.ValidatePaymentType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static com.zrimgaila.payments_backend.general.AppConstants.STATUS_ACTIVE;

@ValidateBICCode
@ValidateDetails
@Builder
@NoArgsConstructor @AllArgsConstructor @Setter @Getter
@Entity @ToString
@Table(name = "payment")
public class Payment {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(precision = 19, scale = 2)
    private BigDecimal amount;

    @JsonProperty("currency")
    @NotBlank
    private String currency;

    @JsonProperty("debtor_iban")
    @NotBlank
    private String debtorIban;

    @JsonProperty("creditor_iban")
    @NotBlank
    private String creditorIban;

    @JsonProperty("payment_type")
    @ValidatePaymentType // custom annotation
    private String paymentType;

    // Used for EUR or USD payments
    private String details;

    // Used for EUR & USD payments
    @JsonProperty("BIC_code")
    @Column(name = "BIC_code")
    private String BICCode;

    // Additional fields
    @JsonProperty("creation_date")
    @CreationTimestamp
    private LocalDateTime creationDate;

    @Column(nullable = false)
    private String status = STATUS_ACTIVE;

    // prideti payment cancellation service
    @JsonProperty("cancellation_date")
    private LocalDateTime cancellationDate;

    @JsonProperty("cancellation_fee")
    @Column(precision = 19, scale = 2)
    private BigDecimal cancellationFee;
}
