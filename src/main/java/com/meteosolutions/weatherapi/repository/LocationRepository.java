package com.meteosolutions.weatherapi.repository;

import com.meteosolutions.weatherapi.entity.LocationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<LocationEntity,Long> {
}
