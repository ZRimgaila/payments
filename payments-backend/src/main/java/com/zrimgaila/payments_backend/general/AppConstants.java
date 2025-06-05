package com.zrimgaila.payments_backend.general;

import java.math.BigDecimal;

public final class AppConstants {

    public static final String STATUS_ACTIVE = "active";
    public static final String STATUS_CANCELLED = "cancelled";
    public static final String PAYMENT_TYPE_EUR = "EUR";
    public static final String PAYMENT_TYPE_USD = "USD";
    public static final String PAYMENT_TYPE_EUR_AND_USD = "EUR & USD";
    public static final BigDecimal CANCELLATION_FEE_FOR_EUR = BigDecimal.valueOf(0.05);
    public static final BigDecimal CANCELLATION_FEE_FOR_USD = BigDecimal.valueOf(0.1);
    public static final BigDecimal CANCELLATION_FEE_FOR_EUR_AND_USD = BigDecimal.valueOf(0.15);
}
