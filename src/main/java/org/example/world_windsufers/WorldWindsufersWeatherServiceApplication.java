package org.example.world_windsufers;

import org.example.world_windsufers.properties.WeatherProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableAsync;


@SpringBootApplication
@EnableConfigurationProperties(WeatherProperties.class)
@EnableFeignClients
@EnableCaching
@EnableAsync
public class WorldWindsufersWeatherServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(WorldWindsufersWeatherServiceApplication.class, args);
	}

}
