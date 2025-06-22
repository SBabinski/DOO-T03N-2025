public class Clima {
    private double temperaturaAtual;
    private double tempMax;
    private double tempMin;
    private double umidade;
    private String condicaoTempo;
    private double precipitacao;
    private double velocidadeVento;
    private double direcaoVento;

    public Clima(double temperaturaAtual, double tempMax, double tempMin, double umidade, String condicaoTempo,
                 double precipitacao, double velocidadeVento, double direcaoVento) {
        this.temperaturaAtual = temperaturaAtual;
        this.tempMax = tempMax;
        this.tempMin = tempMin;
        this.umidade = umidade;
        this.condicaoTempo = condicaoTempo;
        this.precipitacao = precipitacao;
        this.velocidadeVento = velocidadeVento;
        this.direcaoVento = direcaoVento;
    }

    @Override
    public String toString() {
        return String.format(
            "Temperatura Atual: %.1f°F\nMáxima: %.1f°F\nMínima: %.1f°F\nUmidade: %.1f%%\n" +
            "Condição: %s\nPrecipitação: %.2f mm\nVelocidade do Vento: %.1f mph\nDireção do Vento: %.1f°",
            temperaturaAtual, tempMax, tempMin, umidade, condicaoTempo, precipitacao, velocidadeVento, direcaoVento);
    }
}
