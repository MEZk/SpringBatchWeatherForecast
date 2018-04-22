package io.github.mezk.spring.batch.weather.domain.weather.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.mezk.spring.batch.weather.domain.weather.dao.CityRepository;
import io.github.mezk.spring.batch.weather.domain.weather.models.City;
import io.github.mezk.spring.batch.weather.domain.weather.services.CityStorageService;

@Service
public class CityDbSTorageService implements CityStorageService {

    private final CityRepository repository;

    @Autowired
    public CityDbSTorageService(CityRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<City> getAll() {
        final List<City> cities = new ArrayList<>();

        repository.findAll().forEach(city -> cities.add(city));

        return cities;
    }
}
