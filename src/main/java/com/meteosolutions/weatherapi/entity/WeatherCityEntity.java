package com.meteosolutions.weatherapi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="head_line")
public class WeatherCityEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String cityKey;
    private String cityName;
    @OneToOne(cascade = CascadeType.ALL)
    private HeadlineEntity headline;
    @OneToMany(mappedBy = "weatherCity", cascade = CascadeType.ALL)
    private List<DailyForecastEntity> dailyForecasts;



}
