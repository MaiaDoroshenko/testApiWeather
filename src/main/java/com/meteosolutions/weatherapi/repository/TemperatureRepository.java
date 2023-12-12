package com.meteosolutions.weatherapi.repository;

import com.meteosolutions.weatherapi.entity.TemperatureEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TemperatureRepository extends JpaRepository<TemperatureEntity,Long> {
}
