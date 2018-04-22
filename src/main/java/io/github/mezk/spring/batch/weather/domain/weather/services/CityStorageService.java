package io.github.mezk.spring.batch.weather.domain.weather.services;

import java.util.List;

import io.github.mezk.spring.batch.weather.domain.weather.models.City;

public interface CityStorageService {

    List<City> getAll();

}
