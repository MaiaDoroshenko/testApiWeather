package com.meteosolutions.weatherapi.controller;

import com.meteosolutions.weatherapi.dto.DailyWeatherDTO;
import com.meteosolutions.weatherapi.service.DailyWeatherService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/daily-weather")
@AllArgsConstructor
public class DailyWeatherController {

    private final DailyWeatherService dailyWeatherService;


    @PostMapping("/{cityName}")
    public ResponseEntity<String> saveDailyWeather(@RequestBody DailyWeatherDTO dailyWeatherDTO, @PathVariable String cityName) {
        try {
            dailyWeatherService.saveDailyWeather(dailyWeatherDTO, cityName);
            return new ResponseEntity<>("Daily weather saved for " + cityName, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to save daily weather for " + cityName, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
