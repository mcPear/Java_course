package com.example.controller;

import com.example.currency_exchange.model.ExchangeModel;
import com.example.currency_exchange.service.ExchangeService;
import com.example.weather.controller.NewWeatherController;
import com.example.weather.model.WeatherParams;
import com.example.weather.service.impl.OpenWeatherMapClient;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Answers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Optional;

import static matchers.OptionalMatcher.isEmpty;
import static matchers.OptionalMatcher.isPresent;

/**
 * Created by maciej on 28.03.17.
 */
@RunWith(MockitoJUnitRunner.class)
public class NewWeatherControllerTest {

    @Mock(answer = Answers.RETURNS_MOCKS)
    private ExchangeService exchangeService;

    @Mock
    private OpenWeatherMapClient weatherClient;


    @InjectMocks
    NewWeatherController sut;


    @Before
    public void setUP() {
    }


    @Test
    public void shouldReturnWeather() {
        WeatherParams weatherParams = sut.getWeather(new WeatherParams());
        Assert.assertNotNull(weatherParams);
    }


}