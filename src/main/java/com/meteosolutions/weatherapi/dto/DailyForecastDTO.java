package com.meteosolutions.weatherapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class DailyForecastDTO {
    @JsonProperty("Date")
    private String date;

    @JsonProperty("EpochDate")
    private Long epochDate;

    @JsonProperty("Day.Icon")
    private Integer dayIcon;

    @JsonProperty("Day.IconPhrase")
    private String dayIconPhrase;

    @JsonProperty("Day.HasPrecipitation")
    private Boolean dayHasPrecipitation;

    @JsonProperty("Night.Icon")
    private Integer nightIcon;

    @JsonProperty("Night.IconPhrase")
    private String nightIconPhrase;

    @JsonProperty("Night.HasPrecipitation")
    private Boolean nightHasPrecipitation;

    @JsonProperty("Night.PrecipitationType")
    private String precipitationType;

    @JsonProperty("Night.PrecipitationIntensity")
    private String precipitationIntensity;
    @JsonProperty("CityId")
    private Long cityId;
}
