package com.example.weather.service.impl;

import com.example.weather.service.WeatherService;
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
