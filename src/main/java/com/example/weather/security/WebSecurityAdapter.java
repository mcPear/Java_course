package com.example.weather.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Created by maciej on 04.04.17.
 */
@EnableWebSecurity
@Configuration
public class WebSecurityAdapter extends WebSecurityConfigurerAdapter {


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable(); //csrf - rodzaj ataku na użytkownika, wykorzystuje to, że jest on zalogowany i odpala chyba skrypt kradnący np dane
        http
                .authorizeRequests()
                .antMatchers("/api/**").authenticated()
                .anyRequest().permitAll(); //wszystkie zapytania są dla wszystkich
        http
                .formLogin() //nawet gdyby było wymagane logowanie, to nie będzie tego okienka, bo zmieniamy na formLogin
                .loginPage("/login");
    }


}
