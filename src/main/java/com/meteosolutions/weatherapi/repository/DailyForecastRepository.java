package com.meteosolutions.weatherapi.repository;

import com.meteosolutions.weatherapi.entity.DailyForecastEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DailyForecastRepository extends JpaRepository<DailyForecastEntity,Long> {
}
