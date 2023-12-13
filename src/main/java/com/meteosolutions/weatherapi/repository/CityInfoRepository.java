package com.meteosolutions.weatherapi.repository;

import com.meteosolutions.weatherapi.entity.CityInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import reactor.core.publisher.Mono;

public interface CityInfoRepository extends JpaRepository<CityInfo,Long> {
    CityInfo findByLocalizedName(String localizedName);
}
