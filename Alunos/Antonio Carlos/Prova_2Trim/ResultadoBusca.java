package com.trabalhotvmaze.series;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ResultadoBusca {
    @JsonProperty("show")
    private Serie show;

    public Serie getShow() {
        return show;
    }
}