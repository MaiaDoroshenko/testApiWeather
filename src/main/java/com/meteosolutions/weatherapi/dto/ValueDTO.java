package com.meteosolutions.weatherapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ValueDTO {
    @JsonProperty("value")
    private double value;

    @JsonProperty("unit")
    private String unit;

    @JsonProperty("unitType")
    private int unitType;
}
