package com.example.currency_exchange.service;

import com.example.currency_exchange.dto.CurrencyDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.Currency;

/**
 * Created by maciej on 15.03.17.
 */
@Service
public class CurrencyExchangeService {

    @Autowired
    private CurrencyDTO currencyDTO;

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

}
