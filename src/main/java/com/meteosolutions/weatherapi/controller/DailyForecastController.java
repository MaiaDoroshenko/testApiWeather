package com.meteosolutions.weatherapi.controller;

import com.meteosolutions.weatherapi.service.DailyForecastService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/daily-forecast")
@AllArgsConstructor
public class DailyForecastController {
    private final DailyForecastService dailyForecastService;

    @GetMapping("/{cityName}")
    public ResponseEntity<String> getDailyForecastByCityName(@PathVariable String cityName) {
        try {
            dailyForecastService.saveDailyForecast(cityName);
            return new ResponseEntity<>("Daily forecast saved for " + cityName, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to get daily forecast for " + cityName, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
