package com.example.weather.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by maciej on 11.03.17.
 */
@Controller
public class MyController {

    @RequestMapping("/controller")
    public Boolean conrollerTest(){
        return true;
    }

}

