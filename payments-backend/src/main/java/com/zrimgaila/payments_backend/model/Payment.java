package com.zrimgaila.payments_backend.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.zrimgaila.payments_backend.general.PaymentStatus;
import com.zrimgaila.payments_backend.general.PaymentType;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import com.zrimgaila.payments_backend.validation.ValidateBICCode;
import com.zrimgaila.payments_backend.validation.ValidateDetails;
import com.zrimgaila.payments_backend.validation.ValidatePaymentType;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@ValidateBICCode
@ValidateDetails
@Builder
@NoArgsConstructor @AllArgsConstructor
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
    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;

    // Used for EUR or USD payments
    private String details;

    // Used for EUR & USD payments
    @JsonProperty("BIC_code")
    @Column(name = "BIC_code")
    private String BICCode;

    // Additional fields
    @JsonProperty("creation_date")
    @CreationTimestamp
    private OffsetDateTime creationDate;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private PaymentStatus status = PaymentStatus.ACTIVE;

    // prideti payment cancellation service
    @JsonProperty("cancellation_date")
    private OffsetDateTime cancellationDate;

    @JsonProperty("cancellation_fee")
    @Column(precision = 19, scale = 2)
    private BigDecimal cancellationFee;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getDebtorIban() {
        return debtorIban;
    }

    public void setDebtorIban(String debtorIban) {
        this.debtorIban = debtorIban;
    }

    public String getCreditorIban() {
        return creditorIban;
    }

    public void setCreditorIban(String creditorIban) {
        this.creditorIban = creditorIban;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getBICCode() {
        return BICCode;
    }

    public void setBICCode(String BICCode) {
        this.BICCode = BICCode;
    }

    public OffsetDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(OffsetDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public void setStatus(PaymentStatus status) {
        this.status = status;
    }

    public OffsetDateTime getCancellationDate() {
        return cancellationDate;
    }

    public void setCancellationDate(OffsetDateTime cancellationDate) {
        this.cancellationDate = cancellationDate;
    }

    public BigDecimal getCancellationFee() {
        return cancellationFee;
    }

    public void setCancellationFee(BigDecimal cancellationFee) {
        this.cancellationFee = cancellationFee;
    }
}
