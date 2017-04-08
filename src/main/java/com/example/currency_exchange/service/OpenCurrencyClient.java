package com.example.currency_exchange.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.websocket.ClientEndpoint;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by maciej on 16.03.17.
 */
@Service
public class OpenCurrencyClient {

    String url = "http://api.fixer.io/latest?symbol=USD,GBP&base=PLN";

    @Autowired
    RestTemplate rest;

    public String getExchange(){
       Map<String,Object> response = rest.getForObject(url, HashMap.class);
        return response.toString();
    }

}
