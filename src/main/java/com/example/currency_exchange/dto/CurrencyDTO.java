package com.example.currency_exchange.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by maciej on 09.03.17.
 */
@Data
@NoArgsConstructor
@Component
public class CurrencyDTO implements Serializable{
    private BigDecimal value;
    private String currency;

}
