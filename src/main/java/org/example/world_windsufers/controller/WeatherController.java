package org.example.world_windsufers.controller;

import lombok.RequiredArgsConstructor;
import org.example.world_windsufers.model.Weather;
import org.example.world_windsufers.service.WeatherService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class WeatherController {

    private final WeatherService weatherService;

    @GetMapping("/forecasts")
    public ResponseEntity<List<Weather>> getForecastsForAllDestinations() {
        List<Weather> forecasts = weatherService.getForecastsForAllDestinations();
        return ResponseEntity.ok(forecasts);
    }
}
