
public class Clima {
    private String cidade;
    private String pais;
    private double temperatura;
    private double sensacaoTermica;
    private double ventoKph;
    private int umidade;
    private String condicao;

    public String getCidade() { return cidade; }
    public void setCidade(String cidade) { this.cidade = cidade; }

    public String getPais() { return pais; }
    public void setPais(String pais) { this.pais = pais; }

    public double getTemperatura() { return temperatura; }
    public void setTemperatura(double temperatura) { this.temperatura = temperatura; }

    public double getSensacaoTermica() { return sensacaoTermica; }
    public void setSensacaoTermica(double sensacaoTermica) { this.sensacaoTermica = sensacaoTermica; }

    public double getVentoKph() { return ventoKph; }
    public void setVentoKph(double ventoKph) { this.ventoKph = ventoKph; }

    public int getUmidade() { return umidade; }
    public void setUmidade(int umidade) { this.umidade = umidade; }

    public String getCondicao() { return condicao; }
    public void setCondicao(String condicao) { this.condicao = condicao; }

    @Override
    public String toString() {
        return String.format(
            "Cidade: %s, País: %s\nTemperatura: %.1f°C\nSensação Térmica: %.1f°C\nVento: %.1f km/h\nUmidade: %d%%\nCondição: %s",
            cidade, pais, temperatura, sensacaoTermica, ventoKph, umidade, condicao
        );
    }
}