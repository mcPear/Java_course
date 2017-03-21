package com.example.currency_exchange.service;

/**
 * Created by maciej on 20.03.17.
 */

import com.example.currency_exchange.model.ExchangeModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.*;

@Service
public class ExchangeClient {

    private String url = "http://api.fixer.io/latest?symbols=USD,GBP&base=PLN";
    private static final String BASE_TARGET_URL = "http://api.fixer.io/latest?base=BBB&symbols=TTT";
    private static final String BASE_TARGETS_LIST_URL = "http://api.fixer.io/latest?base=BBB&symbols=";
    private static final String BASE_TARGET_DATE_URL = "http://api.fixer.io/YYYY-MM-DD?base=BBB&symbols=TTT";

    @Autowired
    private RestTemplate rest;

    public ExchangeModel getExchange() {
        return rest.getForObject(url, ExchangeModel.class);
    }

    public Double getRateBaseTarget(String base, String target){
        String url = BASE_TARGET_URL.replace("BBB",base).replace("TTT",target);
        System.out.println(url);
        Map<String,Object> response = rest.getForObject(url, HashMap.class);
        return (Double)((HashMap)response.get("rates")).get(target);
    }

    public Map<String,Double> getRatesBaseTargets(String base, List<String> targets){
        String url = BASE_TARGETS_LIST_URL.replace("BBB",base);
        for(String t: targets){
            url+=t+',';
        }
        url=url.substring(0,url.length()-1);
        System.out.println(url);
        Map<String,Object> response = rest.getForObject(url, HashMap.class);
        return (Map<String, Double>) response.get("rates");
    }

    public Double performConversion(Integer value, String base, String target){
        String url = BASE_TARGET_URL.replace("BBB",base).replace("TTT",target);
        System.out.println(url);
        Map<String,Object> response = rest.getForObject(url, HashMap.class);
        Double rate = (Double)((HashMap)response.get("rates")).get(target);
        return value*rate;
    }

    public Double performConversionValue(ExchangeModel exchangeModel){
        String url = BASE_TARGET_DATE_URL.replace("BBB",exchangeModel.getBase()).replace("TTT",exchangeModel.getTarget()).replace("YYYY-MM-DD",exchangeModel.getDate());
        System.out.println(url);
        Map<String,Object> response = rest.getForObject(url, HashMap.class);
        Double rate = (Double)((HashMap)response.get("rates")).get(exchangeModel.getTarget());
        return rate*(exchangeModel.getValue());
    }
}
