package com.example.controller;

import com.example.dto.CurrencyDTO;
import com.example.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.server.PathParam;
import java.util.Currency;

/**
 * Created by maciej on 07.03.17.
 */

@RestController
public class CurrencyExchangeController {


    @Autowired
    private CurrencyService currencyService;

    @RequestMapping("/{value}")
    public Long multiplyByTwo(@PathVariable Long value){ //jak dam 2 to Spring sobie zrzutuje na Longa sam
       return value;
    }

    @RequestMapping("/currency/{value}/")
    public String addCurrencySignature(@PathVariable Long value,
                                       @RequestParam("currency") String currency){ //wymaga ? w adresie
        return value.toString()+currency;
    }//http://localhost:8080/currency/10/?currency=pln

    @RequestMapping("/multiplier1/{value}/{multiplier}/")
    public String currencyMultiplier1(@PathVariable Long value,
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
    }//http://localhost:8080/multiplier/10/4.3/?from=PLN&to=USD returns status 200 if everything went ok, 400 if wrong value or multiplier type or 500 if wrong currency ex. uSD

    @RequestMapping("/multiplier2/{value}/{multiplier}/")
    public String currencyMultiplier2(@PathVariable Long value,
                                     @PathVariable Double multiplier,
                                     @RequestParam("from") String from,
                                     @RequestParam("to") String to,
                                     HttpServletRequest request,
                                     HttpServletResponse response){

        try {
            Currency currencyFrom = Currency.getInstance(from);
            Currency currencyTo = Currency.getInstance(to);
            response.setStatus(202); //exemplary status
            return value + from + " equals " + value * multiplier + to;
        }
        catch(IllegalArgumentException e){
            response.setStatus(401); //exemplary status
            return null;
        }
    }//http://localhost:8080/multiplier2/10/4.3/?from=PLN&to=USD returns status 202 if everything went ok, 400 if bad request, 401 if wrong currency ex. uSD

    //HOMEWORK
    @RequestMapping("/multiplier3/{value}/{multiplier}/")
    public ResponseEntity<CurrencyDTO> currencyMultiplier3(@PathVariable Long value,
                                                     @PathVariable Double multiplier,
                                                     @RequestParam("from") String from,
                                                     @RequestParam("to") String to){
        return currencyService.currencyMultiplier(value,multiplier,from,to);
    }//http://localhost:8080/multiplier3/125/4.3/?from=PLN&to=USD returns status 200 if everything went ok, 400 if bad request, 400 if wrong currency ex. uSD

}
