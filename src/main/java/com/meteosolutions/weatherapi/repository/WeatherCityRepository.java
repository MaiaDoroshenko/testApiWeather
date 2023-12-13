package com.meteosolutions.weatherapi.repository;

import com.meteosolutions.weatherapi.entity.WeatherCityEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WeatherCityRepository extends JpaRepository<WeatherCityEntity,Long> {
    WeatherCityEntity findByCityName(String cityName);
}
