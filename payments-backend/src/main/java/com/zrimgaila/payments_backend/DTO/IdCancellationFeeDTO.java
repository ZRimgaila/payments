package com.zrimgaila.payments_backend.DTO;

import lombok.*;

import java.math.BigDecimal;

@NoArgsConstructor @AllArgsConstructor @Setter @Getter @ToString
public class IdCancellationFeeDTO {

    private Integer id;
    private BigDecimal cancellation_fee;

}
