package com.example.controller;

import com.example.currency_exchange.entity.ExchangeRate;
import com.example.currency_exchange.model.ExchangeModel;
import com.example.currency_exchange.repository.ExchangeRateRepository;
import com.example.currency_exchange.service.CurrencyExchangeService;
import matchers.CurrencyMatcher;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Currency;
import java.util.Optional;

import static matchers.OptionalMatcher.isPresent;

/**
 * Created by maciej on 09.04.17.
 */
@RunWith(MockitoJUnitRunner.class)
public class TestUsingCurrencyMatcher {

    @Mock
    ExchangeRateRepository exchangeRateRepository;

    @InjectMocks
    CurrencyExchangeService currencyExchangeService;

    @Test
    public void shouldContainCurrencyFromPLN(){
        //exchangeRateRepository exists (!null), but doesn't contain anything (findAll()==null)
        // what repo is it ? is it connected with my DB ? if not, it's not connected with any DB ...
        //TODO try spring-data-mock (github.com/mmnaseri/spring-data-mock)
        //Iterable<ExchangeRate> exchangeRates = currencyExchangeService.getAllFromDB();
        //Assert.assertThat(exchangeRates, org.hamcrest.CoreMatchers.hasItems(CurrencyMatcher.exchangesFrom(Currency.getInstance("PLN"))));
    }

}
