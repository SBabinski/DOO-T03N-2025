package org.example;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherResponse {
    @JsonProperty("days")
    private List<Clima> days;

    public WeatherResponse() {
    }

    public List<Clima> getDays() {
        return days;
    }

    public void setDays(List<Clima> days) {
        this.days = days;
    }
}