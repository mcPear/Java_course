package com.example.weather.controller;

import com.example.weather.model.WeatherParams;
import com.example.currency_exchange.service.OpenCurrencyClient;
import com.example.weather.repository.WeatherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * Created by maciej on 16.03.17.
 */
@RestController
@RequestMapping("/newapi")
public class NewWeatherController {



    @Autowired
    private OpenCurrencyClient client;

    @Autowired
    private WeatherRepository repo;

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    String hello(@RequestParam(name="imie", required = false, defaultValue = "stranger") String name){
        return "hello "+name;
    }

    @RequestMapping("/weather1")
    public WeatherParams getWeather(@Validated WeatherParams params){
        params.setCurrent(false);
        return params;
    }//http://localhost:8080/api/weather1?city=London&country=England&current=true

    @RequestMapping(value = "/weather2", method = RequestMethod.POST )
    WeatherParams getWeatherPost(@RequestBody WeatherParams params){ //RequestBody wysyłamy w ciele requestu
        params.setCurrent(false);
        return params;
    }//http://localhost:8080/api/weatherP?city=London&country=England&current=true

    @RequestMapping("/weather3")
    ResponseEntity<WeatherParams> getWeatherPost2(@RequestParam WeatherParams params){
        params.setCurrent(false);
        //repo.deleteInBatch();
        return new ResponseEntity<WeatherParams>(params, HttpStatus.ACCEPTED);
    }//http://localhost:8080/api/weatherP?city=London&country=England&current=true


}
