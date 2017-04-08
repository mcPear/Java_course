package com.example.currency_exchange.repository;

import com.example.currency_exchange.entity.Currency;
import com.example.currency_exchange.entity.ExchangeRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by maciej on 28.03.17.
 */
@Repository
public interface ExchangeRateRepository extends CrudRepository<ExchangeRate,Long> {
    public ExchangeRate findFirstByOrderByIdDesc();
    //List<ExchangeRate> findByCurrencyFromIgnoreCaseContaining(String currencyFrom);
}
