package com.meteosolutions.weatherapi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="daily_weather")
public class DailyWeatherEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime date;
    private Double minTemperature;
    private Double maxTemperature;
    private Integer dayIcon;
    private String dayIconPhrase;
    private Boolean dayHasPrecipitation;
    private Integer nightIcon;
    private String nightIconPhrase;
    private Boolean nightHasPrecipitation;
    private String precipitationType;
    private String precipitationIntensity;

    @ManyToOne
    @JoinColumn(name = "city_info_id")
    private CityInfo cityInfo;

}
