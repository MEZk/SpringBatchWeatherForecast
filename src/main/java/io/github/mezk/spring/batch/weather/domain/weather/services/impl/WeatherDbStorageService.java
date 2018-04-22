package io.github.mezk.spring.batch.weather.domain.weather.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.mezk.spring.batch.weather.domain.weather.dao.WeatherRepository;
import io.github.mezk.spring.batch.weather.domain.weather.models.Weather;
import io.github.mezk.spring.batch.weather.domain.weather.services.WeatherStorageService;

@Service
public class WeatherDbStorageService implements WeatherStorageService {

    private final WeatherRepository repository;

    @Autowired
    public WeatherDbStorageService(WeatherRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Weather> saveAll(List<Weather> weather) {
        final List<Weather> saved = new ArrayList<>(weather.size());

        repository.saveAll(weather).forEach(savedWeather -> saved.add(savedWeather));

        return saved;
    }
}
