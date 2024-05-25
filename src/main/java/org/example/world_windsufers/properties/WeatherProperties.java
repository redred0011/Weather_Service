package org.example.world_windsufers.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "weather.api")
@Getter
@Setter
public class WeatherProperties {
    private String baseUrl;
    private String apiKey;
}