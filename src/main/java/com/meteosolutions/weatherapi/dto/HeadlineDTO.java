package com.meteosolutions.weatherapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class HeadlineDTO {
    @JsonProperty("EffectiveDate")
    private String effectiveDate;

    @JsonProperty("EffectiveEpochDate")
    private Long effectiveEpochDate;

    @JsonProperty("Severity")
    private Integer severity;

    @JsonProperty("Text")
    private String text;

    @JsonProperty("Category")
    private String category;

}
