package org.example.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SerieResultado implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @JsonProperty("show")
    private Serie serie;
    

    public SerieResultado() {
    }
    
    public Serie getSerie() {
        return serie;
    }
    
    public void setSerie(Serie serie) {
        this.serie = serie;
    }
    
    @Override
    public String toString() {
        return serie != null ? serie.toString() : "Resultado sem série";
    }
}
