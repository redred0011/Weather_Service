package org.example.world_windsufers.controller;

import lombok.RequiredArgsConstructor;
import org.example.world_windsufers.model.Weather;
import org.example.world_windsufers.service.WeatherService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class WeatherController {

    private final WeatherService weatherService;

    @GetMapping("/forecasts")
    public List<Weather> getForecastsForAllDestinations() {
        return weatherService.getForecastsForAllDestinations();
    }
    @GetMapping("/best/forecasts")
    public Weather getBestForecastForWindsurfing() {
        Optional<Weather> bestForecast = weatherService.findBestWindsurfingLocation();
        return bestForecast.orElseThrow(() -> new RuntimeException("Not found the best localization for Wind Surfing...."));
    }

}
