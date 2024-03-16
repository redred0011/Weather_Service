package org.example.world_windsufers.controller;

import org.example.world_windsufers.model.Weather;
import org.example.world_windsufers.service.WeatherService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class WeatherControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private WeatherService weatherService;

    @Test
    public void getForecastsForAllDestinations_ShouldReturnForecasts() throws Exception {
        given(weatherService.getForecastsForAllDestinations()).willReturn(List.of(new Weather()));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/forecasts")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    public void getBestForecastForWindsurfing_WhenFound_ShouldReturnWeather() throws Exception {
        Weather expectedWeather = new Weather();
        expectedWeather.setCity_name("Test City");

        given(weatherService.findBestWindsurfingLocation(anyString())).willReturn(Optional.of(expectedWeather));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/best/forecasts")
                        .param("email", "test@example.com")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    public void getBestForecastForWindsurfing_WhenNotFound_ShouldReturnNotFound() throws Exception {
        given(weatherService.findBestWindsurfingLocation(anyString())).willReturn(Optional.empty());
        mockMvc.perform(MockMvcRequestBuilders.get("/api/best/forecasts")
                        .param("email", "test@example.com")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}
