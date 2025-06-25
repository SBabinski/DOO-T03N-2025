package fag.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import fag.Main;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Dia {
    public double tempmax; // Temperatura máxima para o dia
    public double tempmin; // Temperatura mínima para o dia
    public double precip;  // Quantidade de precipitação para o dia
    public String conditions; // Condição geral do dia
    public List<Hora> hours;   // Lista de horas para o dia
}