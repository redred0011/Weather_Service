package org.example.world_windsufers.client;


import org.example.world_windsufers.config.FeignClientConfig;
import org.example.world_windsufers.model.Weather;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "weatherClient", url = "${weather.api.base-url}", configuration = FeignClientConfig.class)
public interface WeatherClient {
    @GetMapping("/forecasts")
    Weather getForecast(@RequestParam("city") String city);
}






