package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("classpath:beans.xml")
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
		User user = new User("Maciek", "Gru");
	}

	@Bean
	String weatherType(){ //nazwa metody to nazwa beana
	    return "weather_type";
    }

    @Bean
    String otherWeatherType(){ //nazwa metody to nazwa beana
        return "other_weather_type";
    }

}
