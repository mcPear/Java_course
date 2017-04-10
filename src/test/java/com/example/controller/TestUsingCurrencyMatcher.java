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
    ExchangeRateRepository mockExchangeRateRepository;
    //TODO Mockito can't create an instance of this repository because it's an interface. I don't know how to fix it without creating a class implementing repository.

    @InjectMocks
    CurrencyExchangeService currencyExchangeService;

    @Test
    public void shouldContainCurrencyFromPLN(){
        ArrayList<ExchangeRate> exchangeRates = currencyExchangeService.getAllFromDB();
        Assert.assertThat(exchangeRates, org.hamcrest.CoreMatchers.hasItems(CurrencyMatcher.exchangesFrom(Currency.getInstance("PLN"))));
    }

}
