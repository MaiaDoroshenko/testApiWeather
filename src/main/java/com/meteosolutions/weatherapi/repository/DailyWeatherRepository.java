package com.meteosolutions.weatherapi.repository;

import com.meteosolutions.weatherapi.entity.DailyWeatherEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DailyWeatherRepository extends JpaRepository<DailyWeatherEntity,Long> {
    Optional<DailyWeatherEntity> findByCity(String cityName);

}
