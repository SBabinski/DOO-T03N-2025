package com.example;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DiaClima {

    @JsonProperty("datetime")
    private String data;

    @JsonProperty("temp")
    private double temperatura;

    @JsonProperty("tempmax")
    private double temperaturaMaxima;

    @JsonProperty("tempmin")
    private double temperaturaMinima;

    @JsonProperty("humidity")
    private double umidade;

    @JsonProperty("conditions")
    private String condicao;

    @JsonProperty("precip")
    private double precipitacao;

    @JsonProperty("windspeed")
    private double velocidadeVento;

    @JsonProperty("winddir")
    private double direcaoVento;

    
    public String getData() {
        return data;
    }

    public double getTemperatura() {
        return temperatura;
    }

    public double getTemperaturaMaxima() {
        return temperaturaMaxima;
    }

    public double getTemperaturaMinima() {
        return temperaturaMinima;
    }

    public double getUmidade() {
        return umidade;
    }

    public String getCondicao() {
        return condicao;
    }

    public double getPrecipitacao() {
        return precipitacao;
    }

    public double getVelocidadeVento() {
        return velocidadeVento;
    }

    public double getDirecaoVento() {
        return direcaoVento;
    }

    
    public void setData(String data) {
        this.data = data;
    }

    public void setTemperatura(double temperatura) {
        this.temperatura = temperatura;
    }

    public void setTemperaturaMaxima(double temperaturaMaxima) {
        this.temperaturaMaxima = temperaturaMaxima;
    }

    public void setTemperaturaMinima(double temperaturaMinima) {
        this.temperaturaMinima = temperaturaMinima;
    }

    public void setUmidade(double umidade) {
        this.umidade = umidade;
    }

    public void setCondicao(String condicao) {
        this.condicao = condicao;
    }

    public void setPrecipitacao(double precipitacao) {
        this.precipitacao = precipitacao;
    }

    public void setVelocidadeVento(double velocidadeVento) {
        this.velocidadeVento = velocidadeVento;
    }

    public void setDirecaoVento(double direcaoVento) {
        this.direcaoVento = direcaoVento;
    }
}
