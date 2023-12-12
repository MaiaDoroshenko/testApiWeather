package com.meteosolutions.weatherapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class WeatherInfoDTO {
    @JsonProperty("locationName")
    private LocationDTO  locationName;
    @JsonProperty("DailyForecasts")
    private List<DailyForecastDTO> dailyForecasts;
}
