package com.meteosolutions.weatherapi.controller;

import com.meteosolutions.weatherapi.service.WeatherCityService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/weather-city")
@AllArgsConstructor
public class WeatherCityController {
    private final WeatherCityService weatherCityService;

    @PostMapping("/{cityName}")
    public ResponseEntity<String> saveAndReturnWeatherCityJson(@PathVariable String cityName) {
        try {
            String weatherCityJson = weatherCityService.saveAndReturnWeatherCityJson(cityName);
            return new ResponseEntity<>(weatherCityJson, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to process weather information for " + cityName, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
