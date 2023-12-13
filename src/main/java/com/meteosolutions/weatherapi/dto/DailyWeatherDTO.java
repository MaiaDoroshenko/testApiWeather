package com.meteosolutions.weatherapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DailyWeatherDTO {
    @JsonProperty(value="Date")
    private LocalDateTime date;
    @JsonProperty(value="Temperature.Minimum.Value")
    private Double minTemperature;
    @JsonProperty(value="Temperature.Maximum.Value")
    private Double maxTemperature;
    @JsonProperty(value="Day.Icon")
    private Integer dayIcon;
    @JsonProperty(value="Day.IconPhrase")
    private String dayIconPhrase;
    @JsonProperty(value="Day.HasPrecipitation")
    private Boolean dayHasPrecipitation;
    @JsonProperty(value="Night.Icon")
    private Integer nightIcon;
    @JsonProperty(value="Night.IconPhrase")
    private String nightIconPhrase;
    @JsonProperty(value="Night.HasPrecipitation")
    private Boolean nightHasPrecipitation;
    @JsonProperty(value="Night.PrecipitationType")
    private String precipitationType;
    @JsonProperty(value="Night.PrecipitationIntensity")
    private String precipitationIntensity;
}
