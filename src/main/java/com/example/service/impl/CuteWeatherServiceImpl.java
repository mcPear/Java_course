package com.example.service.impl;

import com.example.service.WeatherService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * Created by maciej on 14.03.17.
 */
@Service("cute")
public class CuteWeatherServiceImpl implements WeatherService {


    @Override
    public String getWeather() {
        return "It's sunny";
    }
}
