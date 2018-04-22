package io.github.mezk.spring.batch.weather.domain.weather.services;

import java.util.List;

import io.github.mezk.spring.batch.weather.domain.weather.models.Weather;

public interface WeatherStorageService {

    List<Weather> saveAll(List<Weather> weather);
}
