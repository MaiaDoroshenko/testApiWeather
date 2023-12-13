package com.meteosolutions.weatherapi.repository;

import com.meteosolutions.weatherapi.entity.DailyForecastEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DailyForecastRepository extends JpaRepository<DailyForecastEntity,Long> {
    Optional<DailyForecastEntity> findByWeatherCity_CityName(String cityName);


}
