package com.example.currency_exchange.controller;

import com.example.currency_exchange.dto.CurrencyDTO;
import com.example.currency_exchange.entity.ExchangeRate;
import com.example.currency_exchange.model.ExchangeModel;
import com.example.currency_exchange.repository.ExchangeRateRepository;
import com.example.currency_exchange.service.CurrencyExchangeService;
import com.example.currency_exchange.service.ExchangeService;
import com.example.currency_exchange.service.OpenCurrencyClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by maciej on 07.03.17.
 */

@RestController
public class CurrencyExchangeController {

    @Autowired
    ExchangeRateRepository exchangeRateRepository;

    @Autowired
    private CurrencyExchangeService currencyExchangeService;

    @Autowired
    private ExchangeService exchangeService;

    @Autowired
    private OpenCurrencyClient openCurrencyClient;

    @RequestMapping("/{value}")
    public Long multiplyByTwo(@PathVariable Long value) { //jak dam 2 to Spring sobie zrzutuje na Longa sam
        return 2 * value;
    }

    @RequestMapping("/currency/{value}/")
    public String addCurrencySignature(@PathVariable Long value,
                                       @RequestParam("currency") String currency) { //wymaga ? w adresie
        return value.toString() + currency;
    }//http://localhost:8080/currency/10/?currency=pln

    @RequestMapping("/multiplier1/{value}/{multiplier}/")
    public String currencyMultiplier1(@PathVariable Long value,
                                      @PathVariable Double multiplier,
                                      @RequestParam("from") String from,
                                      @RequestParam("to") String to) {

        try {
            Currency currencyFrom = Currency.getInstance(from);
            Currency currencyTo = Currency.getInstance(to);
            return value + from + " equals " + value * multiplier + to;
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Something went wrong");
        }
    }//http://localhost:8080/multiplier/10/4.3/?from=PLN&to=USD returns status 200 if everything went ok, 400 if wrong value or multiplier type or 500 if wrong currency ex. uSD

    @RequestMapping("/multiplier2/{value}/{multiplier}/")
    public String currencyMultiplier2(@PathVariable Long value,
                                      @PathVariable Double multiplier,
                                      @RequestParam("from") String from,
                                      @RequestParam("to") String to,
                                      HttpServletRequest request,
                                      HttpServletResponse response) {

        try {
            Currency currencyFrom = Currency.getInstance(from);
            Currency currencyTo = Currency.getInstance(to);
            response.setStatus(202); //exemplary status
            return value + from + " equals " + value * multiplier + to;
        } catch (IllegalArgumentException e) {
            response.setStatus(401); //exemplary status
            return null;
        }
    }//http://localhost:8080/multiplier2/10/4.3/?from=PLN&to=USD returns status 202 if everything went ok, 400 if bad request, 401 if wrong currency ex. uSD

    //HOMEWORK by Adam - Chapter3
    @RequestMapping("/multiplier3/{value}/{multiplier}/")
    public ResponseEntity<CurrencyDTO> currencyMultiplier3(@PathVariable Long value,
                                                           @PathVariable Double multiplier,
                                                           @RequestParam("from") String from,
                                                           @RequestParam("to") String to) {
        return currencyExchangeService.currencyMultiplier(value, multiplier, from, to);
    }//http://localhost:8080/multiplier3/125/4.3/?from=PLN&to=USD returns status 200 if everything went ok, 400 if bad request, 400 if wrong currency ex. uSD

    @RequestMapping("/currency")
    public String getExchange() {
        return openCurrencyClient.getExchange();
    }

    //HOMEWORK - Chapter4
    @RequestMapping(value = "/rateBaseTarget", method = RequestMethod.GET)
    public Double getRate(@RequestParam("base") String base,
                          @RequestParam("target") String target) {
        try {
            Currency currencyBase = Currency.getInstance(base);
            Currency currencyTarget = Currency.getInstance(target);
            return exchangeService.getRateBaseTarget(base, target);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }//http://localhost:8080/rateBaseTarget?base=EUR&target=PLN

    //HOMEWORK - Chapter4
    @RequestMapping(value = "/ratesBaseTargets", method = RequestMethod.GET)
    public Map<String, Double> getMultipleRates(@RequestParam("base") String base,
                                                @RequestParam("targets") List<String> targets) {
        try {
            Currency.getInstance(base);
            for (String t : targets)
                Currency.getInstance(t);

            return exchangeService.getRatesBaseTargets(base, targets);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }//http://localhost:8080/ratesBaseTargets?base=EUR&targets=PLN,GBP,USD

    //HOMEWORK - Chapter4
    @RequestMapping(value = "/rateBaseTargetPOST", method = RequestMethod.POST)
    public Double getRate_POST(@RequestBody String base,
                               @RequestBody String target) {
        try {
            Currency.getInstance(base);
            Currency.getInstance(target);
            return exchangeService.getRateBaseTarget(base, target);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }//http://localhost:8080/rateBaseTargetPOST
    /*
    * I don't know how to write body and test it :( but have tried
    * */

    //HOMEWORK - Chapter4
    @RequestMapping(value = "/performConversion", method = RequestMethod.GET)
    public Double performConversion(@RequestParam("value") Integer value,
                                    @RequestParam("base") String base,
                                    @RequestParam("target") String target) {
        try {
            Currency.getInstance(base);
            Currency.getInstance(target);
            return exchangeService.performConversion(value, base, target);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }//http://localhost:8080/performConversion?value=344&base=EUR&target=PLN

    //HOMEWORK - Chapter4
    @RequestMapping(value = "/performConversionValue", method = RequestMethod.GET)
    public ResponseEntity<Double> performConversionValue(@Validated ExchangeModel exchangeModel) {
        try {
            Currency.getInstance(exchangeModel.getBase());
            Currency.getInstance(exchangeModel.getTarget());
            new SimpleDateFormat("yyyy-mm-dd").parse(exchangeModel.getDate());
            Double convertedValue = exchangeService.performConversionValue(exchangeModel);
            return new ResponseEntity<>(convertedValue, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>((Double) null, HttpStatus.BAD_REQUEST);
        } catch (ParseException e) {
            return new ResponseEntity<>((Double) null, HttpStatus.BAD_REQUEST);
        } catch (HttpClientErrorException e) {
            return new ResponseEntity<>((Double) null, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            return new ResponseEntity<>((Double) null, HttpStatus.I_AM_A_TEAPOT);
        }
    }//http://localhost:8080/performConversionValue?value=344&base=EUR&target=PLN&date=2016-05-21

    //HOMEWORK - Chapter5
    @RequestMapping("/saveRatesBaseTargets")
    public void saveMultipleRates(@RequestParam("base") String base,
                                  @RequestParam("targets") List<String> targets,
                                  @RequestParam("date") java.sql.Date date) {
        try {
            Currency.getInstance(base);
            for (String t : targets)
                Currency.getInstance(t);

            exchangeService.saveRatesBaseTargets(base, targets, date);
        } catch (IllegalArgumentException e) {
            return;
        }

    }//http://localhost:8080/saveRatesBaseTargets?base=EUR&targets=PLN,GBP,USD&2016-05-10

    //HOMEWORK - Chapter5
    @RequestMapping(value = "/getLastEntry", method = RequestMethod.GET)
    public ExchangeRate getLastExchangeRate() {

        return exchangeRateRepository.findFirstByOrderByIdDesc();
    }//http://localhost:8080/getLastEntry    result sample: {"id":12,"currencyFrom":"EUR","currencyTo":"USD","value":1.13,"date":"2016-05-13"}

    //HOMEWORK - Chapter5
    @RequestMapping(value = "/saveExchangeRate", method = RequestMethod.PUT)
    public Long saveExchangeRateBody(@RequestBody ExchangeRate exchangeRate) {
        exchangeRateRepository.save(exchangeRate);
        return exchangeRateRepository.count();
    }//http://localhost:8080/saveExchangeRate    body sample: {"id":null,"currencyFrom":"EUR","currencyTo":"USD","value":1.13,"date":"2016-05-13"}

    //HOMEWORK - Chapter5
    @RequestMapping(value = "/deleteById", method = RequestMethod.DELETE)
    public void deleteExchangeRateById(@RequestParam("id") Long id) {

        exchangeRateRepository.delete(id);
    }//http://localhost:8080/deleteById?2

    //for me
    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public Iterable<ExchangeRate> getAllExchangeRatesFromDB() {

        return currencyExchangeService.getAllFromDB();
    }//http://localhost:8080/getAll


}
