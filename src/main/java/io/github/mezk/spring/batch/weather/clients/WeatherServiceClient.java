package io.github.mezk.spring.batch.weather.clients;

import io.github.mezk.spring.batch.weather.clients.models.WeatherInfo;

public interface WeatherServiceClient {

    WeatherInfo getByCityName(String cityName);

}
