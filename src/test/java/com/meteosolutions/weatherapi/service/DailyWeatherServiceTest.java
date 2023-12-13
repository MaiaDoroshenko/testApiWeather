package com.meteosolutions.weatherapi.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.meteosolutions.weatherapi.dto.DailyWeatherDTO;
import com.meteosolutions.weatherapi.dto.WeatherCityDTO;
import com.meteosolutions.weatherapi.entity.CityInfo;
import com.meteosolutions.weatherapi.entity.DailyWeatherEntity;
import com.meteosolutions.weatherapi.helper.ApiWeatherHelper;
import com.meteosolutions.weatherapi.repository.CityInfoRepository;
import com.meteosolutions.weatherapi.repository.DailyWeatherRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class DailyWeatherServiceTest {
    @Mock
    private ApiWeatherHelper apiWeatherHelper;

    @Mock
    private CityInfoRepository cityInfoRepository;

    @Mock
    private DailyWeatherRepository dailyWeatherRepository;

    @InjectMocks
    private DailyWeatherService dailyWeatherService;

    @Test
    void test_Save_Daily_Weather() {

        DailyWeatherDTO dailyWeatherDTO = new DailyWeatherDTO();
        dailyWeatherDTO.setMinTemperature(10.5);
        dailyWeatherDTO.setMaxTemperature(25.3);

        WeatherCityDTO weatherCityDTO = new WeatherCityDTO();

        when(apiWeatherHelper.getWeatherInfoByCity(anyString()))
                .thenReturn(Mono.just(weatherCityDTO));


        dailyWeatherService.saveDailyWeather(dailyWeatherDTO, "CityName");


        verify(apiWeatherHelper, times(1)).getWeatherInfoByCity(anyString());
        verify(cityInfoRepository, times(1)).findByLocalizedName(anyString());
        verify(dailyWeatherRepository, times(1)).save(any(DailyWeatherEntity.class));

        ArgumentCaptor<DailyWeatherEntity> captor = ArgumentCaptor.forClass(DailyWeatherEntity.class);
        verify(dailyWeatherRepository).save(captor.capture());

        DailyWeatherEntity capturedEntity = captor.getValue();

        assertEquals(dailyWeatherDTO.getDate(), capturedEntity.getDate());
        assertEquals(dailyWeatherDTO.getMinTemperature(), capturedEntity.getMinTemperature());

    }


    }

