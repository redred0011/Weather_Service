package org.example.world_windsufers.service;

import org.example.world_windsufers.client.WeatherClient;
import org.example.world_windsufers.common.Destination;
import org.example.world_windsufers.model.Forecast;
import org.example.world_windsufers.model.Weather;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import java.text.MessageFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
    private final EmailService emailService;
    private static final Logger logger = LoggerFactory.getLogger(WeatherService.class);

    @Cacheable("forecasts")
    public List<Weather> getForecastsForAllDestinations() {
        logger.info("Downloading data from api");
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
                String emailBody = MessageFormat.format(
                        "The request for finding the best windsurfing location was made on: {0}\n" +
                                "The best Localization: {1}\n" +
                                "Wind speed: {2} m/s\n" +
                                "Temperature: {3} °C\n" +
                                "Score: {4}",
                        LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                        location.getCity_name(),
                        bestForecast.getWind_spd(),
                        bestForecast.getTemp(),
                        bestForecast.getScore().doubleValue()
                );
                emailService.sendEmail(email, "Best Windsurfing Location Details", emailBody);
                logger.info("Email sent to: {}", email);
            }
        });

        return bestLocation;
    }
}
