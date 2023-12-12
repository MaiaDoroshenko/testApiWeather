package com.meteosolutions.weatherapi.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class WeatherInfoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "location_id")
    private LocationEntity locationName;
    @OneToMany(mappedBy = "weatherInfo", cascade = CascadeType.ALL)
    private List<DailyForecastEntity> dailyForecast;
}
