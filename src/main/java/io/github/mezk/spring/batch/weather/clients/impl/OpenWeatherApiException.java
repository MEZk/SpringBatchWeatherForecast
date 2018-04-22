package io.github.mezk.spring.batch.weather.clients.impl;

import org.springframework.http.HttpStatus;

public class OpenWeatherApiException extends RuntimeException {
    public OpenWeatherApiException(HttpStatus statusCode, String body) {
        super("Http status: " + statusCode + ". Body: " + body);
    }
}
