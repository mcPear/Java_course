package com.example.weather.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by maciej on 04.04.17.
 */
@Data
@Entity
public class UserDetails {

    @Id
    @GeneratedValue
    private  Long id;
    private  String name;
    private  String surname;
    private  String mail;
    private String password;


}
