package com.example;


import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RespostaClima {

    @JsonProperty("days")
    private List<DiaClima> dias;

    public List<DiaClima> getDias() {
        return dias;
    }

    public void setDias(List<DiaClima> dias) {
        this.dias = dias;
    }
}
