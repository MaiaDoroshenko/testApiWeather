package com.meteosolutions.weatherapi.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.meteosolutions.weatherapi.dto.TemperatureDTO;
import com.meteosolutions.weatherapi.dto.WeatherDescriptionDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class DailyForecastEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    private TemperatureEntity temperature;

    @OneToOne(cascade = CascadeType.ALL)
    private WeatherDescriptionEntity day;

    @OneToOne(cascade = CascadeType.ALL)
    private WeatherDescriptionEntity night;

    @ManyToOne
    @JoinColumn(name = "weather_info_id")
    private WeatherInfoEntity weatherInfo;
}
