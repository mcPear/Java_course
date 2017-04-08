package com.example.currency_exchange.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * Created by maciej on 21.03.17.
 */

@Data
@Entity
@Table(name="user")
public class User {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String email;

    @Column
    @OneToMany
    private List<Currency> currencies;
}
