package org.example.world_windsufers.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Weather {
    private String city_name;
    private List<Forecast> data;
}
