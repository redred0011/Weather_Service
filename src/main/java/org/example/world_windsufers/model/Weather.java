package org.example.world_windsufers.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@Setter
@SuperBuilder
public class Weather {
    private String city_name;
    private List<Forecast> data;
}
