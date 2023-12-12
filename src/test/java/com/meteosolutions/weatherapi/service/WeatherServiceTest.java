package com.meteosolutions.weatherapi.service;

import com.meteosolutions.weatherapi.dto.WeatherInfoDTO;
import com.meteosolutions.weatherapi.helper.ApiWeatherHelper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class WeatherServiceTest {
    @Mock
    private ApiWeatherHelper apiWeatherHelper;
    @InjectMocks
    private WeatherService weatherService;


    @Test
    public void testGetWeatherForLocation(){
        String cityName= "New York";
        String locationKey = "123456";
        WeatherInfoDTO weatherInfoDTO = new WeatherInfoDTO();

        when(apiWeatherHelper.getLocationKey(anyString())).thenReturn(Mono.just(locationKey));
        when(apiWeatherHelper.getWeatherInfo(locationKey)).thenReturn(Mono.just(weatherInfoDTO));


        when(apiWeatherHelper.getLocationKey(anyString())).thenReturn(Mono.just(locationKey));
        when(apiWeatherHelper.getWeatherInfo(locationKey)).thenReturn(Mono.just(weatherInfoDTO));

        StepVerifier.create(weatherService.getWeatherForLocation(cityName))
                .expectNext(weatherInfoDTO)
                .verifyComplete();

        verify(apiWeatherHelper, times(1)).getLocationKey(cityName);
        verify(apiWeatherHelper, times(1)).getWeatherInfo(locationKey);
    }

    }




