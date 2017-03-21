package com.example.weather.service.impl;

import com.example.weather.service.WeatherService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * Created by maciej on 14.03.17.
 */
//@Primary //sposób na określenie, który jest wsztrzykaiwny za pomocą @Autowired
@Service
@Qualifier("ugly")
public class UglyWeatherServiceImpl implements WeatherService {


    @Override
    public String getWeather() {
        return "It's rainy";
    }
}
