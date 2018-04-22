package io.github.mezk.spring.batch.weather.clients.models;

import io.github.mezk.spring.batch.weather.domain.weather.models.enums.Units;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class WeatherInfo {

    private Units units;
    private double temperature;
    private String cityName;

}
