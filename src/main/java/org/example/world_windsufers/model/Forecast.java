package org.example.world_windsufers.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Getter
@Setter
public class Forecast {
    private String valid_date;
    private double temp;
    private double wind_spd;
    private double score;

    public BigDecimal getScore() {
        BigDecimal score = new BigDecimal(3 * wind_spd + temp);
        score = score.setScale(2, RoundingMode.CEILING);
        return score;
    }
}