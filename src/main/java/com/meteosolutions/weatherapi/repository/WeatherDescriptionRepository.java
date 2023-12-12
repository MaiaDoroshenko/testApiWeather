package com.meteosolutions.weatherapi.repository;

import com.meteosolutions.weatherapi.entity.WeatherDescriptionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WeatherDescriptionRepository extends JpaRepository<WeatherDescriptionEntity,Long> {
}
