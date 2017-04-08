package com.example.weather.repository;

import com.example.weather.entity.Weather;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by maciej on 21.03.17.
 */
@Repository
public interface WeatherRepository extends CrudRepository<Weather,Long> {
}
