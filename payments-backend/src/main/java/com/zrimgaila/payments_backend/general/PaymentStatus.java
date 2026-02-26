package com.zrimgaila.payments_backend.general;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public enum PaymentStatus {

    ACTIVE("active"),
    CANCELLED("cancelled");

    private final String description;

    PaymentStatus (String description) {
        this.description = description;
    }
}
