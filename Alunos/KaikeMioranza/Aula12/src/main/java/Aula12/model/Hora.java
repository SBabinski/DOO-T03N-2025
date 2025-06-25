package Aula12.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Hora {
    public double temp;
    public double humidity;
    public double precipprob;
    public double windspeed;
    public double winddir;
    public String conditions;


}