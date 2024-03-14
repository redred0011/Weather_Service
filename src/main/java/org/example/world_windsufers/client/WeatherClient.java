package org.example.world_windsufers.client;

import org.example.world_windsufers.common.Destination;
import org.example.world_windsufers.model.dto.WeatherDto;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "weatherClient", url = "${weather.api.base-url}")
public abstract class WeatherClient {
    @GetMapping("/weather")
    abstract WeatherDto weather(@RequestParam Destination destination);
}
