package com.example.weather.model;

/**
 * Created by maciej on 20.03.17.
 */
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.Size;


@Data
public class WeatherParams {

    @Valid
    @Size(min=2,max=5)
    private String city;

    private String country;
    private Boolean current;

}
