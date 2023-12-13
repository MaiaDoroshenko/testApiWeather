package com.meteosolutions.weatherapi.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.meteosolutions.weatherapi.dto.DailyForecastDTO;
import com.meteosolutions.weatherapi.entity.DailyForecastEntity;
import com.meteosolutions.weatherapi.entity.WeatherCityEntity;
import com.meteosolutions.weatherapi.exception.CityNotFoundException;
import com.meteosolutions.weatherapi.helper.ApiWeatherHelper;
import com.meteosolutions.weatherapi.repository.DailyForecastRepository;
import com.meteosolutions.weatherapi.repository.WeatherCityRepository;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Transactional
@Builder
@Slf4j
@AllArgsConstructor
public class DailyForecastService {
    private final ApiWeatherHelper apiWeatherHelper;
    private final DailyForecastRepository dailyForecastRepository;
    private final WeatherCityRepository weatherCityRepository;
    private final ObjectMapper objectMapper;

    public void saveDailyForecast(String cityName) {
        try {
            WeatherCityEntity weatherCityEntity = getOrCreateWeatherCity(cityName);

            if (weatherCityEntity != null) {
                String cityKey = apiWeatherHelper.getLocationKey(cityName).block();

                if (cityKey != null) {
                    DailyForecastDTO dailyForecastDTO = apiWeatherHelper.getDailyForecast(cityKey).block();

                    if (dailyForecastDTO != null) {
                        DailyForecastEntity dailyForecastEntity = toDailyForecastEntity(dailyForecastDTO, String.valueOf(weatherCityEntity));
                        dailyForecastRepository.save(dailyForecastEntity);
                    } else {
                        log.error("Failed to fetch daily weather information for city: {}", cityName);
                    }
                } else {
                    log.error("Failed to fetch weather information for city: {}", cityName);
                }
            } else {
                log.error("WeatherCityEntity not found for city: {}", cityName);
            }
        } catch (Exception e) {
            log.error("Error while saving daily forecast for '{}': {}", cityName, e.getMessage());
        }
    }

    private WeatherCityEntity getOrCreateWeatherCity(String cityName) {
        WeatherCityEntity weatherCityEntity = weatherCityRepository.findByCityName(cityName);

        if (weatherCityEntity == null) {
            // If WeatherCityEntity doesn't exist, create and save a new one
            weatherCityEntity = new WeatherCityEntity();
            weatherCityEntity.setCityName(cityName);
            weatherCityRepository.save(weatherCityEntity);
        }

        return weatherCityEntity;
    }


    private WeatherCityEntity getWeatherCityEntity(String cityName) {
        return weatherCityRepository.findByCityName(cityName);


    }

    private DailyForecastEntity toDailyForecastEntity(DailyForecastDTO dailyForecastDTO, String cityName) {
        DailyForecastEntity dailyForecastEntity = new DailyForecastEntity();
        WeatherCityEntity weatherCityEntity = weatherCityRepository.findByCityName(cityName);
        dailyForecastEntity.setDate(LocalDateTime.parse(dailyForecastDTO.getDate()));
        dailyForecastEntity.setEpochDate(dailyForecastDTO.getEpochDate());
        dailyForecastEntity.setDayIcon(dailyForecastDTO.getDayIcon());
        dailyForecastEntity.setDayIconPhrase(dailyForecastDTO.getDayIconPhrase());
        dailyForecastEntity.setDayHasPrecipitation(dailyForecastDTO.getDayHasPrecipitation());
        dailyForecastEntity.setNightIcon(dailyForecastDTO.getNightIcon());
        dailyForecastEntity.setNightIconPhrase(dailyForecastDTO.getNightIconPhrase());
        dailyForecastEntity.setNightHasPrecipitation(dailyForecastDTO.getNightHasPrecipitation());
        dailyForecastEntity.setPrecipitationType(dailyForecastDTO.getPrecipitationType());
        dailyForecastEntity.setPrecipitationIntensity(dailyForecastDTO.getPrecipitationIntensity());
        dailyForecastEntity.setWeatherCity(weatherCityEntity);

        return dailyForecastEntity;
    }
}
