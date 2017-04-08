package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication(scanBasePackages = "com.example.*")
@EnableJpaRepositories(basePackages = "com.example.*")
@ComponentScan({"com.example.*"})
@EntityScan(basePackages = {"com.example.*"})
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

    @Bean
	RestTemplate getRestTemplate(){
    	return  new RestTemplateBuilder().build();
	}

}
