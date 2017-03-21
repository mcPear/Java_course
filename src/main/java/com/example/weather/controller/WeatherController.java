package com.example.weather.controller;

import com.example.weather.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by maciej on 14.03.17.
 */
@RestController
@RequestMapping("/api")
public class WeatherController {

    @Qualifier(value = "cute")// drugi, lepszy sposób na wskazanie co wstrzyknąć
    @Autowired //jak ci się uda znaleźć implementację WeatherService to gra, jak nie to nawet nie startuj aplikacji
    private WeatherService weatherService; //nie wskazuję, z której implementacji będę korzystał

    //@Qualifier("otherWeatherType")// wykorzystanie nazwy metody z @Bean do sprecyzowania
    @Qualifier("xmlBean")
    @Autowired
    private String myBean;

    @Autowired
    Collection<WeatherService> weatherServices;


    @RequestMapping("/weathers")
    public List<String> allWeathers(){
        List<String> result = new ArrayList<>();
        weatherServices.forEach(x->result.add(x.getWeather()));
        result.add(myBean); //dodatek pokazuje, że inne beany też można wstrzykiwać
        return  result;
    }


    @RequestMapping("/weather")
    public String getWeather(){
        return weatherService.getWeather();
    }

    @RequestMapping("/weathers/{status}")
    public List<String> allWeathersStatus(@PathVariable Long status,
                                                          HttpServletRequest request,
                                                          HttpServletResponse response){
        List<String> result = new ArrayList<>();
        weatherServices.forEach(x->result.add(x.getWeather()));
        result.add(myBean); //dodatek pokazuje, że inne beany też można wstrzykiwać
        response.setStatus(401); //warunkowa zmiana zwracanego statusu
        return result;

    }

}
