package com.example.currency_exchange.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by maciej on 21.03.17.
 */
@Data
@Entity
@Table(name="currency")
public class Currency {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    String currencyName;

    @Column
    BigDecimal value;
}
