package com.example.controller;

import com.example.dto.CurrencyDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

