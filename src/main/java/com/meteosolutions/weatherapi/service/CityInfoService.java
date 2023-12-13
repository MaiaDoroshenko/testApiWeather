package com.meteosolutions.weatherapi.service;

import com.meteosolutions.weatherapi.entity.CityInfo;
import com.meteosolutions.weatherapi.helper.ApiWeatherHelper;
import com.meteosolutions.weatherapi.repository.CityInfoRepository;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Service
@Transactional
@Builder
@Slf4j
@AllArgsConstructor
public class CityInfoService {
    private final ApiWeatherHelper apiWeatherHelper;
    private final CityInfoRepository cityInfoRepository;

    public CityInfo saveCityInfo (String cityName){
        String cityKey = apiWeatherHelper.getLocationKey(cityName).block();

        CityInfo cityInfo = new CityInfo();
        cityInfo.setKey(cityKey);
        cityInfo.setLocalizedName(cityName);

        return cityInfoRepository.save(cityInfo);
    }
    public CityInfo getCityInfoByName(String cityName) {
        return cityInfoRepository.findByLocalizedName(cityName);
    }



}
