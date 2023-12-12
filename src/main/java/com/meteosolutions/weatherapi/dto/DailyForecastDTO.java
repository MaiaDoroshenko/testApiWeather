package com.meteosolutions.weatherapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.meteosolutions.weatherapi.entity.TemperatureEntity;
import com.meteosolutions.weatherapi.entity.WeatherDescriptionEntity;
import lombok.Data;

@Data
public class DailyForecastDTO {
    @JsonProperty("Temperature")
    private TemperatureDTO temperature;

    @JsonProperty("day")
    private WeatherDescriptionDTO  day;

    @JsonProperty("Night")
    private WeatherDescriptionDTO  night;
}
