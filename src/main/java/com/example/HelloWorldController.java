package com.example;

import org.springframework.web.bind.annotation.*;

/**
 * Created by maciej on 07.03.17.
 */
@RestController //mówi, że to komponent, to kontroler, jest restowy
@RequestMapping("/hello") //wszystkie metody tego kontrolera oczekują na zapytanie po tym adresie localhost:8080/hello (jakoś tak)
//8080 znaczy, że webowy
public class HelloWorldController {

    @RequestMapping("/world") // wejdź do metody /hello/world
    public String helloWorld(){
        return "HelloWorld";
    }

}
