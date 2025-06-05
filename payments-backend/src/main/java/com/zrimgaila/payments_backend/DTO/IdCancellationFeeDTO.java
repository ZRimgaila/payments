package com.zrimgaila.payments_backend.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@NoArgsConstructor @AllArgsConstructor @Setter @Getter
public class IdCancellationFeeDTO {

    private Integer id;
    private BigDecimal cancellation_fee;

}
