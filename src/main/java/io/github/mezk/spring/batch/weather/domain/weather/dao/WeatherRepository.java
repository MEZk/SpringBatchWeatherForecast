package io.github.mezk.spring.batch.weather.domain.weather.dao;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import io.github.mezk.spring.batch.weather.domain.weather.models.Weather;

public interface WeatherRepository extends CrudRepository<Weather, UUID> {
}
