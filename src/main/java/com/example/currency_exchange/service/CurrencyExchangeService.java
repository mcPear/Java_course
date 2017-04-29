package com.example.currency_exchange.service;

import com.example.currency_exchange.dto.CurrencyDTO;
import com.example.currency_exchange.entity.ExchangeRate;
import com.example.currency_exchange.model.ExchangeModel;
import com.example.currency_exchange.repository.ExchangeRateRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Currency;

/**
 * Created by maciej on 15.03.17.
 */
@Service
public class CurrencyExchangeService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CurrencyDTO currencyDTO;

    @Autowired
    private ExchangeRateRepository exchangeRateRepository;

    public ResponseEntity<CurrencyDTO> currencyMultiplier(Long value,
                                             Double multiplier,
                                             String from,
                                             String to){

        try {
            Currency currencyFrom = Currency.getInstance(from);
            Currency currencyTo = Currency.getInstance(to);
            currencyDTO.setValue(new BigDecimal(value*multiplier));
            currencyDTO.setCurrency(to);
            return new ResponseEntity<>(currencyDTO, HttpStatus.OK);
        }
        catch(IllegalArgumentException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    public Iterable<ExchangeRate> getAllFromDB(){

        //I'm not using logger.debug, because it's not working... I suppose I should
        //It's late, I can't figure out how to enable it
        Iterable<ExchangeRate> exchangeRates = exchangeRateRepository.findAll();
        logger.info(exchangeRateRepository.findAll().toString());

        return exchangeRates;
    }

}
