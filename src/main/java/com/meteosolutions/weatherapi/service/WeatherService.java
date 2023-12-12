package com.meteosolutions.weatherapi.service;

import com.meteosolutions.weatherapi.dto.WeatherInfoDTO;
import com.meteosolutions.weatherapi.helper.ApiWeatherHelper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
@Slf4j
@Transactional
public class WeatherService {
    private final ApiWeatherHelper apiWeatherHelper;
    public Mono<WeatherInfoDTO>getWeatherForLocation(String cityName){
        return apiWeatherHelper.getLocationKey(cityName)
                .switchIfEmpty(Mono.error(new DataRetrievalFailureException("Location key not found for city: " + cityName)))
                .flatMap(apiWeatherHelper::getWeatherInfo)
                .onErrorResume(error -> {
                    log.error("Error retrieving weather information for city: {}. Error: {}", cityName, error.getMessage());
                    return Mono.error(new DataRetrievalFailureException("Weather info not found for city: " + cityName));
                });
        }

}
