package Aula12.model;

import Aula12.Aula12.Main;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
@JsonIgnoreProperties(ignoreUnknown = true)
public class Dia {
    public List<Hora> hours;
    public double tempmax;
    public double tempmin;
}

