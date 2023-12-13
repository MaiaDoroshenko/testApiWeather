package com.meteosolutions.weatherapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CityInfoDTO {
    @JsonProperty(value = "Key")
    private String key;

    @NotBlank(message = "Location name must not be blank")
    @JsonProperty(value = "LocalizedName")
    private String  localizedName;
}
