package io.github.mezk.spring.batch.weather.clients.impl;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.mezk.spring.batch.weather.clients.WeatherServiceClient;
import io.github.mezk.spring.batch.weather.clients.models.WeatherInfo;
import io.github.mezk.spring.batch.weather.domain.weather.models.enums.Units;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Service
public class OpenWeatherClient implements WeatherServiceClient {

    private final RestTemplate restTemplate;

    public OpenWeatherClient() {
        this.restTemplate = new RestTemplateBuilder().build();
    }

    @Override
    public WeatherInfo getByCityName(String cityName) {
        final ResponseEntity<WeatherDto> response = restTemplate.getForEntity(
            UriComponentsBuilder.fromHttpUrl("http://api.openweathermap.org/data/2.5/weather")
                .queryParam("q", cityName)
                .queryParam("units", "metrics")
                .queryParam("APPID", "00b2f34db1a9293f3b67a1e2e8e0a964")
                .toUriString(),
            WeatherDto.class
        );

        if (response.getStatusCode() == HttpStatus.OK) {
            return new WeatherInfo(
                Units.CELSIUS,
                response.getBody().getMain().getTemp(),
                cityName
            );
        }

        throw new OpenWeatherApiException(response.getStatusCode(), response.toString());
    }

    @Getter
    private static class WeatherDto {

        private Main main;

        @Getter
        private static class Main {

            private double temp;

        }

    }

}
