package io.github.mezk.spring.batch.weather.domain.weather.dao;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import io.github.mezk.spring.batch.weather.domain.weather.models.City;

public interface CityRepository extends CrudRepository<City, UUID> {
}
