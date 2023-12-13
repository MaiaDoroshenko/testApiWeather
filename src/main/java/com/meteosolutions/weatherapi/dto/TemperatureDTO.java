package com.meteosolutions.weatherapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class TemperatureDTO {
    @JsonProperty(value = "Value")
    private Double value;

    @JsonProperty(value = "Unit")
    private String unit;

    @JsonProperty(value = "UnitType")
    private Integer unitType;
}
