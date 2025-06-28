public class ClimaInfo {

    private String cidade;
    private double temperaturaAtual;
    private double temperaturaMax;
    private double temperaturaMin;
    private double umidade;
    private String condicao;
    private double precipitacao;
    private double velocidadeVento;
    private String direcaoVento;


    public ClimaInfo(String cidade, double temperaturaAtual, double temperaturaMax, double temperaturaMin, double umidade,
                     String condicao, double precipitacao, double velocidadeVento, String direcaoVento) {

        this.cidade = cidade;
        this.temperaturaAtual = temperaturaAtual;
        this.temperaturaMax = temperaturaMax;
        this.temperaturaMin = temperaturaMin;
        this.umidade = umidade;
        this.condicao = condicao;
        this.precipitacao = precipitacao;
        this.velocidadeVento = velocidadeVento;
        this.direcaoVento = direcaoVento;

    }


    @Override
    public String toString() {

        return String.format(

            "Cidade: %s\nTemperatura atual: %.1f°C\nMaxima: %.1f°C\nMinima: %.1f°C\nHumidade: %.0f%%\nCondicao: %s\nPrecipitacao: %.1f mm\nVento: %.1f km/h",
            cidade, temperaturaAtual, temperaturaMax, temperaturaMin, umidade, condicao, precipitacao, velocidadeVento, direcaoVento
        
        );

    }

}
