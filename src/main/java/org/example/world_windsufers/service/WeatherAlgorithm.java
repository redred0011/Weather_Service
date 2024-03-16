package org.example.world_windsufers.service;

import org.example.world_windsufers.model.Forecast;
import org.example.world_windsufers.model.Weather;
import org.example.world_windsufers.model.WeatherScore;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Component
public class WeatherAlgorithm {

    public Optional<Weather> findBestLocationForWindsurfing(List<Weather> forecasts) {
        return forecasts.stream()
                .map(this::findBestDayForLocation)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .max(Comparator.comparingDouble(WeatherScore::getScore))
                .map(this::convertWeatherScoreToWeather);
    }

    private Optional<WeatherScore> findBestDayForLocation(Weather weather) {
        return weather.getData().stream()
                .filter(this::isSuitableForWindsurfing)
                .map(forecast -> new WeatherScore(
                        weather.getCity_name(),
                        forecast.getValid_date(),
                        calculateScore(forecast),
                        forecast))
                .max(Comparator.comparingDouble(WeatherScore::getScore));
    }

    private boolean isSuitableForWindsurfing(Forecast forecast) {
        return forecast.getWind_spd() >= 5 && forecast.getWind_spd() <= 18 &&
                forecast.getTemp() >= 5 && forecast.getTemp() <= 35;
    }

    private double calculateScore(Forecast forecast) {
        return forecast.getWind_spd() * 3 + forecast.getTemp();
    }

    private Weather convertWeatherScoreToWeather(WeatherScore weatherScore) {
        Weather weather = new Weather();
        weather.setCity_name(weatherScore.getCityName());
        weather.setData(List.of(weatherScore.getForecast()));
        return weather;
    }
}

