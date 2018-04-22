package io.github.mezk.spring.batch.weather.clients.impl;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import io.github.mezk.spring.batch.weather.clients.WeatherServiceClient;
import io.github.mezk.spring.batch.weather.domain.weather.models.enums.Units;
import io.github.mezk.spring.batch.weather.clients.models.WeatherInfo;

public class OpenWeatherClientTest {

    @Test
    public void getByCityName() throws Exception {
        // ARRANGE
        final WeatherServiceClient client = new OpenWeatherClient();

        // ACT
        final WeatherInfo weatherInfo = client.getByCityName("Moscow");

        // ASSERT
        assertNotNull(weatherInfo);
        Assert.assertEquals(Units.CELSIUS, weatherInfo.getUnits());
    }

}