package com.meteosolutions.weatherapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class WeatherCityDTO {
    @JsonProperty("CityKey")
    private String cityKey;

    @JsonProperty("CityName")
    private String cityName;

    @JsonProperty("Headline")
    private HeadlineDTO headline;

    @JsonProperty("DailyForecasts")
    private List<DailyForecastDTO> dailyForecasts;
}
