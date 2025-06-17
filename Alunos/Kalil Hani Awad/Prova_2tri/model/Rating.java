package org.example.model;

import com.fasterxml.jackson.annotation.JsonProperty;


public class Rating {
    @JsonProperty("average")
    private String average;

    public String getAverage() {
        return average;
    }

    public void setAverage(String average) {
        this.average = average;
    }
}
