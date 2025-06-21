package org.aplicacao;

public class Tempo {
    public float temp;
    public float tempMax;
    public float tempMin;
    public float humidade;
    public String condicao;
    public float precipitacao;
    public float velocidadeVento;
    public float direcaoVento;

    public Tempo() {
    }

    public float getTemp() {
        return temp;
    }

    public void setTemp(float temp) {
        this.temp = temp;
    }

    public float getDirecaoVento() {
        return direcaoVento;
    }

    public void setDirecaoVento(float direcaoVento) {
        this.direcaoVento = direcaoVento;
    }

    public float getPrecipitacao() {
        return precipitacao;
    }

    public void setPrecipitacao(float precipitacao) {
        this.precipitacao = precipitacao;
    }

    public String getCondicao() {
        return condicao;
    }

    public void setCondicao(String condicao) {
        this.condicao = condicao;
    }

    public float getTempMin() {
        return tempMin;
    }

    public void setTempMin(float tempMin) {
        this.tempMin = tempMin;
    }

    public float getTempMax() {
        return tempMax;
    }

    public void setTempMax(float tempMax) {
        this.tempMax = tempMax;
    }

    public float getHumidade() {
        return humidade;
    }

    public void setHumidade(float humidade) {
        this.humidade = humidade;
    }

    public float getVelocidadeVento() {
        return velocidadeVento;
    }

    public void setVelocidadeVento(float velocidadeVento) {
        this.velocidadeVento = velocidadeVento;
    }

    @Override
    public String toString() {
        return String.format("Temperatura: " + temp + "ºC " + "( mín: " + tempMin + "ºC " + "máx: " +
                tempMax + "ºC )" + "\nCondições: " + condicao + "\nUmidade: " + humidade +
                "\nPrecipitação: " + precipitacao * 100 + "\nVento: " + velocidadeVento +
                " km/h " + "direção: " + direcaoVento);
    }
}
