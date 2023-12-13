package com.meteosolutions.weatherapi.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="temperature")
public class TemperatureEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Double value;
    private String unit;
    private Integer unitType;

    @OneToOne
    @JoinColumn(name = "daily_weather_id")
    private DailyWeatherEntity dailyForecast;
}
