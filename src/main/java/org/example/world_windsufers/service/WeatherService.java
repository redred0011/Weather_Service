package org.example.world_windsufers.service;

import org.example.world_windsufers.client.WeatherClient;
import org.example.world_windsufers.common.Destination;
import org.example.world_windsufers.model.Weather;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
@RequiredArgsConstructor
public class WeatherService {
    private final WeatherClient weatherClient;
    private final WeatherAlgorithm weatherAlgorithm;
    private static final Logger logger = LoggerFactory.getLogger(WeatherService.class);
    @Cacheable("forecasts")
    public List<Weather> getForecastsForAllDestinations() {
        logger.info("Downloading data from api");
        return Arrays.stream(Destination.values())
                .map(destination -> weatherClient.getForecast(destination.name()))
                .collect(Collectors.toList());
    }
    @Cacheable("bestLocation")
    public Optional<Weather> findBestWindsurfingLocation() {
        List<Weather> forecasts = getForecastsForAllDestinations();
        return weatherAlgorithm.findBestLocationForWindsurfing(forecasts);
    }
}
