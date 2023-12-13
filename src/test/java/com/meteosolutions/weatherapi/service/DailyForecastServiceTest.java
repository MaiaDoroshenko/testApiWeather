package com.meteosolutions.weatherapi.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.meteosolutions.weatherapi.dto.DailyForecastDTO;
import com.meteosolutions.weatherapi.entity.DailyForecastEntity;
import com.meteosolutions.weatherapi.entity.WeatherCityEntity;
import com.meteosolutions.weatherapi.helper.ApiWeatherHelper;
import com.meteosolutions.weatherapi.repository.DailyForecastRepository;
import com.meteosolutions.weatherapi.repository.WeatherCityRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class DailyForecastServiceTest {
    @Mock
    private ApiWeatherHelper apiWeatherHelper;

    @Mock
    private DailyForecastRepository dailyForecastRepository;

    @Mock
    private WeatherCityRepository weatherCityRepository;

    @InjectMocks
    private DailyForecastService dailyForecastService;

    @Test
    void test_Save_Daily_Forecast() {
        // Simulación de datos
        String cityName = "CityName";
        WeatherCityEntity weatherCityEntity = new WeatherCityEntity();
        weatherCityEntity.setCityName(cityName);

        DailyForecastDTO dailyForecastDTO = new DailyForecastDTO();


        when(weatherCityRepository.findByCityName(cityName))
                .thenReturn(weatherCityEntity);

        when(apiWeatherHelper.getLocationKey(cityName))
                .thenReturn(Mono.just("someKey"));

        when(apiWeatherHelper.getDailyForecast("someKey"))
                .thenReturn(Mono.just(dailyForecastDTO));


        dailyForecastService.saveDailyForecast(cityName);


        verify(weatherCityRepository, times(1)).findByCityName(cityName);
        verify(apiWeatherHelper, times(1)).getLocationKey(cityName);
        verify(apiWeatherHelper, times(1)).getDailyForecast("someKey");
        verify(dailyForecastRepository, times(1)).save(any(DailyForecastEntity.class));


        ArgumentCaptor<DailyForecastEntity> captor = ArgumentCaptor.forClass(DailyForecastEntity.class);
        verify(dailyForecastRepository).save(captor.capture());

        DailyForecastEntity capturedEntity = captor.getValue();


        assertEquals(LocalDateTime.parse(dailyForecastDTO.getDate()), capturedEntity.getDate());
        assertEquals(dailyForecastDTO.getEpochDate(), capturedEntity.getEpochDate());
        assertEquals(dailyForecastDTO.getDayIcon(), capturedEntity.getDayIcon());

    }

}