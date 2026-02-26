package com.zrimgaila.payments_backend.general;

import lombok.*;
import lombok.Getter;

@Getter
public enum PaymentType {

    EUR("Euro"),
    USD("United States Dolar"),
    EUR_AND_USD("Eur and USD");

    private final String description;

    PaymentType(String description) {
        this.description = description;
    }
}
