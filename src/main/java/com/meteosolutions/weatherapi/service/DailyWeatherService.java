package com.meteosolutions.weatherapi.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.meteosolutions.weatherapi.dto.DailyForecastDTO;
import com.meteosolutions.weatherapi.dto.DailyWeatherDTO;
import com.meteosolutions.weatherapi.dto.WeatherCityDTO;
import com.meteosolutions.weatherapi.entity.CityInfo;
import com.meteosolutions.weatherapi.entity.DailyForecastEntity;
import com.meteosolutions.weatherapi.entity.DailyWeatherEntity;
import com.meteosolutions.weatherapi.entity.WeatherCityEntity;
import com.meteosolutions.weatherapi.exception.CityNotFoundException;
import com.meteosolutions.weatherapi.helper.ApiWeatherHelper;
import com.meteosolutions.weatherapi.repository.CityInfoRepository;
import com.meteosolutions.weatherapi.repository.DailyWeatherRepository;
import com.meteosolutions.weatherapi.repository.WeatherCityRepository;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@Builder
@Slf4j
@AllArgsConstructor
public class DailyWeatherService {
    private final ApiWeatherHelper apiWeatherHelper;
    private final ObjectMapper objectMapper;
    private final CityInfoRepository cityInfoRepository;
    private final DailyWeatherRepository dailyWeatherRepository;
    private final WeatherCityRepository weatherCityRepository;

    public void saveDailyWeather(DailyWeatherDTO dailyWeatherDTO, String cityName) {
        WeatherCityDTO weatherCityDTO = apiWeatherHelper.getWeatherInfoByCity(cityName).block();

        if (weatherCityDTO != null) {
            CityInfo cityInfo = cityInfoRepository.findByLocalizedName(cityName);

            if (cityInfo == null) {
                // Create and save CityInfo if not found
                cityInfo = new CityInfo();
                cityInfo.setKey(weatherCityDTO.getCityKey());
                cityInfo.setLocalizedName(weatherCityDTO.getCityName());
                cityInfo = cityInfoRepository.save(cityInfo);
            }

            DailyWeatherEntity dailyWeatherEntity = toDailyWeatherEntity(dailyWeatherDTO, cityInfo);
            dailyWeatherRepository.save(dailyWeatherEntity);
        } else {
            throw new CityNotFoundException("City not found: " + cityName);
        }

}
    private DailyWeatherEntity toDailyWeatherEntity(DailyWeatherDTO dailyWeatherDTO, CityInfo cityInfo) {
        DailyWeatherEntity dailyWeatherEntity = new DailyWeatherEntity();
        dailyWeatherEntity.setDate(dailyWeatherDTO.getDate());
        dailyWeatherEntity.setMinTemperature(dailyWeatherDTO.getMinTemperature());
        dailyWeatherEntity.setMaxTemperature(dailyWeatherDTO.getMaxTemperature());
        dailyWeatherEntity.setDayIcon(dailyWeatherDTO.getDayIcon());
        dailyWeatherEntity.setDayIconPhrase(dailyWeatherDTO.getDayIconPhrase());
        dailyWeatherEntity.setDayHasPrecipitation(dailyWeatherDTO.getDayHasPrecipitation());
        dailyWeatherEntity.setNightIcon(dailyWeatherDTO.getNightIcon());
        dailyWeatherEntity.setNightIconPhrase(dailyWeatherDTO.getNightIconPhrase());
        dailyWeatherEntity.setNightHasPrecipitation(dailyWeatherDTO.getNightHasPrecipitation());
        dailyWeatherEntity.setPrecipitationType(dailyWeatherDTO.getPrecipitationType());
        dailyWeatherEntity.setPrecipitationIntensity(dailyWeatherDTO.getPrecipitationIntensity());
        dailyWeatherEntity.setCityInfo(cityInfo);

        return dailyWeatherEntity;
    }
}
