package org.example.world_windsufers.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@Getter
public class WeatherScore {
    private final String cityName;
    private final String validDate;
    private final double score;
    private final Forecast forecast;

    public Weather toWeather() {
        Weather weather = new Weather();
        weather.setCity_name(this.cityName);
        weather.setData(List.of(this.forecast));
        return weather;
    }
}
