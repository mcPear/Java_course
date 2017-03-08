package com.example;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.util.Currency;

/**
 * Created by maciej on 07.03.17.
 */

@RestController
public class CurrencyExchangeController {

    @RequestMapping("/{value}")
    public Long multiplyByTwo(@PathVariable Long value){ //jak dam 2 to Spring sobie zrzutuje na Longa sam
       return value;
    }

    @RequestMapping("/currency/{value}/")
    public String addCurrencySignature(@PathVariable Long value,
                                       @RequestParam("currency") String currency){ //wymaga ? w adresie
        return value.toString()+currency;
    }//http://localhost:8080/currency/10/?currency=pln

    @RequestMapping("/multiplier/{value}/{multiplier}/")
    public String currencyMultpilier(@PathVariable Long value,
                                     @PathVariable Double multiplier,
                                     @RequestParam("from") String from,
                                     @RequestParam("to") String to){

        try {
            Currency currencyFrom = Currency.getInstance(from);
            Currency currencyTo = Currency.getInstance(to);
            return value + from + " equals " + value * multiplier + to;
        }
        catch(IllegalArgumentException e){
            throw new RuntimeException("Something went wrong");
        }
    }//http://localhost:8080/multiplier/10/4/?from=PLN&to=USD 



}
