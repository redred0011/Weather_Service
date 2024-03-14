package org.example.world_windsufers;

import org.example.world_windsufers.properties.WeatherProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

@SpringBootApplication
@EnableConfigurationProperties(WeatherProperties.class)
@EnableFeignClients
public class WorldWindsufersWeatherServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(WorldWindsufersWeatherServiceApplication.class, args);
	}

}
