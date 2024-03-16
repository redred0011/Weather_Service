package org.example.world_windsufers.service;

import org.example.world_windsufers.client.WeatherClient;
import org.example.world_windsufers.common.Destination;
import org.example.world_windsufers.model.Weather;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@SpringBootTest
public class WeatherServiceTest {

    @Autowired
    private WeatherService weatherService;

    @MockBean
    private WeatherClient weatherClient;

    @MockBean
    private WeatherAlgorithm weatherAlgorithm;

    @Test
    public void getForecastsForAllDestinations_HappyPath_ShouldReturnForecasts() {
        when(weatherClient.getForecast(anyString())).thenReturn(new Weather());

        List<Weather> forecasts = weatherService.getForecastsForAllDestinations();

        assertNotNull(forecasts);
        assertFalse(forecasts.isEmpty());

        verify(weatherClient, times(Destination.values().length)).getForecast(anyString());
    }

    @Test
    public void findBestWindsurfingLocation_HappyPath_ShouldReturnBestLocation() {
        Weather expectedBestWeather = new Weather();
        expectedBestWeather.setCity_name("JASTARNIA");
        when(weatherClient.getForecast(anyString())).thenAnswer(invocation -> {
            String cityName = invocation.getArgument(0);
            Weather weather = new Weather();
            weather.setCity_name(cityName);
            return weather;
        });

        when(weatherAlgorithm.findBestLocationForWindsurfing(any())).thenReturn(Optional.of(expectedBestWeather));

    }
    @Test
    public void findBestWindsurfingLocation_WhenNoSuitableLocation_ShouldReturnEmpty() {
        when(weatherAlgorithm.findBestLocationForWindsurfing(any())).thenReturn(Optional.empty());
        when(weatherClient.getForecast(anyString())).thenReturn(new Weather());

        Optional<Weather> bestLocation = weatherService.findBestWindsurfingLocation(null);

        assertFalse(bestLocation.isPresent());
    }
    @Test
    public void getForecastsForAllDestinations_WhenApiFails_ShouldHandleErrorGracefully() {
        when(weatherClient.getForecast(anyString())).thenThrow(new RuntimeException("Failed to communicate with weather API"));

        Exception exception = assertThrows(RuntimeException.class, () -> weatherService.getForecastsForAllDestinations());

        assertTrue(exception.getMessage().contains("Failed to communicate with weather API"));
    }
    @Test
    public void findBestWindsurfingLocation_WhenForecastsAreEmpty_ShouldReturnEmpty() {
        when(weatherClient.getForecast(anyString())).thenReturn(new Weather());
        when(weatherAlgorithm.findBestLocationForWindsurfing(any())).thenReturn(Optional.empty());

        Optional<Weather> bestLocation = weatherService.findBestWindsurfingLocation(null);

        assertFalse(bestLocation.isPresent(), "Expected no best location due to empty forecasts");
    }


}
