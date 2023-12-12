package com.meteosolutions.weatherapi.controller;

import com.meteosolutions.weatherapi.dto.WeatherInfoDTO;
import com.meteosolutions.weatherapi.service.WeatherService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/weather")
@AllArgsConstructor
public class WeatherController {
    private final WeatherService weatherService;



    @GetMapping("/{cityName}")
    public Mono<ResponseEntity<WeatherInfoDTO>> getWeatherForCity(@PathVariable String cityName) {
        return weatherService.getWeatherForLocation(cityName)
                .map(weatherInfo -> ResponseEntity.ok().body(weatherInfo))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
}
