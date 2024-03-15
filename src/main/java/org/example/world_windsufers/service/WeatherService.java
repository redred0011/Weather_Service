package org.example.world_windsufers.service;
import org.example.world_windsufers.client.WeatherClient;
import org.example.world_windsufers.common.Destination;
import org.example.world_windsufers.model.Weather;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WeatherService {
    private final WeatherClient weatherClient;

    public List<Weather> getForecastsForAllDestinations() {
        return Arrays.stream(Destination.values())
                .map(destination -> weatherClient.getForecast(destination.name()))
                .collect(Collectors.toList());
    }
}
