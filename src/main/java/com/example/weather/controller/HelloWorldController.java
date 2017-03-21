package com.example.weather.controller;

import org.springframework.web.bind.annotation.*;

/**
 * Created by maciej on 07.03.17.
 */
@RestController //mówi, że to komponent, to kontroler, jest restowy
@RequestMapping("/hello") //wszystkie metody tego kontrolera oczekują na zapytanie po tym adresie http://localhost:8080/hello
public class HelloWorldController {

    @RequestMapping("/world") // wejdź do metody /hello/world
    public String helloWorld(){
        return "HelloWorld";
    }

}
