package com.meteosolutions.weatherapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class TemperatureDTO {
    @JsonProperty("Minimum")
    private ValueDTO minimum;

    @JsonProperty("Maximum")
    private ValueDTO maximum;
}
