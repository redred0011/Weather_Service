package org.example.world_windsufers.config;

import feign.RequestInterceptor;
import lombok.RequiredArgsConstructor;
import org.example.world_windsufers.properties.WeatherProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class FeignClientConfig {

    private final WeatherProperties weatherProperties;

    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> requestTemplate.query("key", weatherProperties.getApiKey());
    }
}


