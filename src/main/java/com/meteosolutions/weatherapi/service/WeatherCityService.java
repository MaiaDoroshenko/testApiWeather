package com.meteosolutions.weatherapi.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.meteosolutions.weatherapi.dto.DailyForecastDTO;
import com.meteosolutions.weatherapi.dto.WeatherCityDTO;
import com.meteosolutions.weatherapi.entity.DailyForecastEntity;
import com.meteosolutions.weatherapi.entity.HeadlineEntity;
import com.meteosolutions.weatherapi.entity.WeatherCityEntity;
import com.meteosolutions.weatherapi.helper.ApiWeatherHelper;
import com.meteosolutions.weatherapi.repository.DailyWeatherRepository;
import com.meteosolutions.weatherapi.repository.WeatherCityRepository;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@Builder
@Slf4j
@AllArgsConstructor
public class WeatherCityService {
    private final ApiWeatherHelper apiWeatherHelper;
    private final ObjectMapper objectMapper;
    private final WeatherCityRepository weatherCityRepository;
    private final DailyWeatherRepository dailyWeatherRepository;

    public String saveAndReturnWeatherCityJson(String cityName) {
        WeatherCityDTO weatherCityDTO = apiWeatherHelper.getWeatherInfoByCity(cityName).block();
        if (weatherCityDTO != null) {
            WeatherCityEntity weatherCityEntity = toWeatherCityEntity(weatherCityDTO);
            weatherCityRepository.save(weatherCityEntity);

            try {
                return objectMapper.writeValueAsString(weatherCityDTO);
            } catch (JsonProcessingException ex) {
                log.error("Error while converting WeatherCityDTO to JSON: {}", ex.getMessage());
                throw new RuntimeException("Error processing data for city: " + cityName);
            }
        } else {
            throw new RuntimeException("City not found: " + cityName);
        }
    }

    private WeatherCityEntity toWeatherCityEntity(WeatherCityDTO weatherCityDTO) {

        WeatherCityEntity weatherCity = new WeatherCityEntity();
        weatherCity.setCityName(weatherCityDTO.getCityName());
        weatherCity.setCityKey(weatherCityDTO.getCityKey());
        HeadlineEntity headline = new HeadlineEntity();
        headline.setEffectiveDate(LocalDateTime.parse(weatherCityDTO.getHeadline().getEffectiveDate()));
        headline.setEffectiveEpochDate(weatherCityDTO.getHeadline().getEffectiveEpochDate());
        headline.setSeverity(weatherCityDTO.getHeadline().getSeverity());
        headline.setText(weatherCityDTO.getHeadline().getText());
        weatherCity.setHeadline(headline);
        List<DailyForecastDTO> dailyForecastDTOList = weatherCityDTO.getDailyForecasts();
        List<DailyForecastEntity> dailyForecastEntityList = new ArrayList<>();

        if (dailyForecastDTOList != null) {
            for (DailyForecastDTO dailyForecastDTO : dailyForecastDTOList) {
                DailyForecastEntity dailyForecastEntity = new DailyForecastEntity();
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

                dailyForecastEntity.setWeatherCity(weatherCity);
                dailyForecastEntityList.add(dailyForecastEntity);
            }
        }

        weatherCity.setDailyForecasts(dailyForecastEntityList);

        return weatherCity;

    }

}
