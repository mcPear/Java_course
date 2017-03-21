package com.example.currency_exchange.controller;

import com.example.currency_exchange.dto.CurrencyDTO;
import com.example.currency_exchange.model.ExchangeModel;
import com.example.currency_exchange.service.CurrencyExchangeService;
import com.example.currency_exchange.service.ExchangeClient;
import com.example.currency_exchange.service.OpenCurrencyClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Currency;
import java.util.List;
import java.util.Map;

/**
 * Created by maciej on 07.03.17.
 */

@RestController
public class CurrencyExchangeController {


    @Autowired
    private CurrencyExchangeService currencyExchangeService;

    @Autowired
    private ExchangeClient exchangeClient;

    @Autowired
    private OpenCurrencyClient openCurrencyClient;

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

    //HOMEWORK by Adam - Chapter3
    @RequestMapping("/multiplier3/{value}/{multiplier}/")
    public ResponseEntity<CurrencyDTO> currencyMultiplier3(@PathVariable Long value,
                                                     @PathVariable Double multiplier,
                                                     @RequestParam("from") String from,
                                                     @RequestParam("to") String to){
        return currencyExchangeService.currencyMultiplier(value,multiplier,from,to);
    }//http://localhost:8080/multiplier3/125/4.3/?from=PLN&to=USD returns status 200 if everything went ok, 400 if bad request, 400 if wrong currency ex. uSD

    @RequestMapping("/currency")
    public String getExchange(){
        return openCurrencyClient.getExchange();
    }

    //HOMEWORK - Chapter4
    @RequestMapping(value = "/rateBaseTarget", method = RequestMethod.GET)
    public Double getRate(@RequestParam("base") String base,
                              @RequestParam("target") String target){
        try {
            Currency currencyBase = Currency.getInstance(base);
            Currency currencyTarget = Currency.getInstance(target);
            return exchangeClient.getRateBaseTarget(base, target);
        }
        catch(IllegalArgumentException e){
            return null;
        }
    }//http://localhost:8080/rateBaseTarget?base=EUR&target=PLN

    //HOMEWORK - Chapter4
    @RequestMapping(value = "/ratesBaseTargets", method = RequestMethod.GET)
    public Map<String, Double> getMultipleRates(@RequestParam("base") String base,
                                @RequestParam("targets")List<String> targets){
        try {
            Currency.getInstance(base);
            for(String t: targets)
                Currency.getInstance(t);

            return exchangeClient.getRatesBaseTargets(base, targets);
        }
        catch(IllegalArgumentException e){
            return null;
        }
    }//http://localhost:8080/ratesBaseTargets?base=EUR&targets=PLN,GBP,USD

    //HOMEWORK - Chapter4
    @RequestMapping(value = "/rateBaseTargetPOST", method = RequestMethod.POST)
    public Double getRate_POST(@RequestBody String base,
                          @RequestBody String target){
        try {
            Currency.getInstance(base);
            Currency.getInstance(target);
            return exchangeClient.getRateBaseTarget(base, target);
        }
        catch(IllegalArgumentException e){
            return null;
        }
    }//http://localhost:8080/rateBaseTargetPOST
    /*
    * I don't know how to write body and test it :( but have tried
    * */

    //HOMEWORK - Chapter4
    @RequestMapping(value = "/performConversion", method = RequestMethod.GET)
    public Double performConversion(@RequestParam("value")  Integer value,
                                    @RequestParam("base") String base,
                                    @RequestParam("target") String target){
        try {
            Currency.getInstance(base);
            Currency.getInstance(target);
            return exchangeClient.performConversion(value, base, target);
        }
        catch(IllegalArgumentException e){
            return null;
        }
    }//http://localhost:8080/performConversion?value=344&base=EUR&target=PLN

    //HOMEWORK - Chapter4
    @RequestMapping(value = "/performConversionValue", method = RequestMethod.GET)
    public ResponseEntity<Double> performConversionValue(@Validated ExchangeModel exchangeModel){
        try {
            Currency.getInstance(exchangeModel.getBase());
            Currency.getInstance(exchangeModel.getTarget());
            new SimpleDateFormat("yyyy-mm-dd").parse(exchangeModel.getDate());
            Double convertedValue = exchangeClient.performConversionValue(exchangeModel);
            return new ResponseEntity<>(convertedValue, HttpStatus.OK);
        }
        catch(IllegalArgumentException e){
            return new ResponseEntity<>((Double)null, HttpStatus.BAD_REQUEST);
        }
        catch(ParseException e){
            return new ResponseEntity<>((Double)null, HttpStatus.BAD_REQUEST);
        }
        catch(HttpClientErrorException e) {
            return new ResponseEntity<>((Double)null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        catch(Exception e){
            return new ResponseEntity<>((Double)null, HttpStatus.I_AM_A_TEAPOT);
        }
    }//http://localhost:8080/performConversionValue?value=344&base=EUR&target=PLN&date=2016-05-21

}
