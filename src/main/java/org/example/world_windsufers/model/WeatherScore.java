package org.example.world_windsufers.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class WeatherScore {
    private final String cityName;
    private final String validDate;
    private final double score;
    private final Forecast forecast;
}
