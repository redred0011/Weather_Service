package org.example.world_windsufers.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Forecast {
    private String valid_date;
    private double temp;
    private double windSpd;
}