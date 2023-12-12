package com.meteosolutions.weatherapi.repository;

import com.meteosolutions.weatherapi.entity.WeatherInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WeatherInfoRepository extends JpaRepository<WeatherInfoEntity,Long> {
}
