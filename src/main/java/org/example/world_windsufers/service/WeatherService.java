package org.example.world_windsufers.service;

import lombok.extern.slf4j.Slf4j;
import org.example.world_windsufers.client.WeatherClient;
import org.example.world_windsufers.common.Destination;
import org.example.world_windsufers.model.Forecast;
import org.example.world_windsufers.model.Weather;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class WeatherService {
    private final WeatherClient weatherClient;
    private final WeatherAlgorithm weatherAlgorithm;
    private final EmailService emailService;

    @Cacheable("forecasts")
    public List<Weather> getForecastsForAllDestinations() {
        log.info("Downloading data from api");
        return Arrays.stream(Destination.values())
                .map(destination -> weatherClient.getForecast(destination.name()))
                .collect(Collectors.toList());

    }

    @Cacheable("bestLocation")
    public Optional<Weather> findBestWindsurfingLocation(String email) {
        List<Weather> forecasts = getForecastsForAllDestinations();
        Optional<Weather> bestLocation = weatherAlgorithm.findBestLocationForWindsurfing(forecasts);

        bestLocation.ifPresent(location -> {
            if (email != null && !email.isEmpty() && location.getData() != null && !location.getData().isEmpty()) {
                Forecast bestForecast = location.getData().get(0);
                String emailBody = emailService.createEmailBody(location, bestForecast);
                emailService.sendEmail(email, "Best Windsurfing Location Details", emailBody);
                log.info("Email sent to: {}", email);
            }
        });

        return bestLocation;
    }
}
