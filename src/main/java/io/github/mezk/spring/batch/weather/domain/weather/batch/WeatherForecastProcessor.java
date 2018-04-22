package io.github.mezk.spring.batch.weather.domain.weather.batch;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.github.mezk.spring.batch.weather.clients.WeatherServiceClient;
import io.github.mezk.spring.batch.weather.clients.models.WeatherInfo;
import io.github.mezk.spring.batch.weather.domain.weather.models.City;
import io.github.mezk.spring.batch.weather.domain.weather.models.Weather;

@Component
public class WeatherForecastProcessor implements ItemProcessor<City, Weather> {

    private final WeatherServiceClient weatherServiceClient;

    @Autowired
    public WeatherForecastProcessor(WeatherServiceClient weatherServiceClient) {
        this.weatherServiceClient = weatherServiceClient;
    }

    @Override
    public Weather process(City city) throws Exception {
        return convertToWeather(weatherServiceClient.getByCityName(city.getName()), city);
    }

    private static Weather convertToWeather(WeatherInfo item, City city) {
        final Weather weather = new Weather();

        weather.setCity(city);
        weather.setTemperature(item.getTemperature());
        weather.setUnits(item.getUnits());

        return weather;
    }

}
