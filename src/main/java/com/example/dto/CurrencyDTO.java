package com.example.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * Created by maciej on 09.03.17.
 */
@Data
@NoArgsConstructor
public class CurrencyDTO {
    private BigDecimal value;
    private String currency;

}
