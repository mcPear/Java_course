package com.example.weather.entity;

import lombok.Data;

import javax.persistence.*;
import java.security.Timestamp;

/**
 * Created by maciej on 27.03.17.
 */
@Data
@Entity
@Table(name="weather")
public class Weather {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String city;

    @Column
    private Double temp;

    @Column
    private Double pressure;

    @Column
    private Timestamp time;

}