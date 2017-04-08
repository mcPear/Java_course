package com.example.currency_exchange.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;

/**
 * Created by maciej on 08.04.17.
 */
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="exchange_rate")
public class ExchangeRate {
    @Id
    @GeneratedValue
    private Long id;

    @Column
    String currencyFrom;

    @Column
    String currencyTo;

    @Column
    BigDecimal value;

    @Column
    Date date;
}
