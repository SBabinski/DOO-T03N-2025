
public class ClimaDia {
    private String data;
    private double temperaturaAtual;
    private double temperaturaMinima;
    private double temperaturaMaxima;
    private int umidade;
    private String condicao;
    private double precipitacao;
    private double velocidadeVento;
    private double direcaoVento;

    public ClimaDia(String data, double temperaturaAtual, double temperaturaMinima, double temperaturaMaxima,
                    int umidade, String condicao, double precipitacao, double velocidadeVento, double direcaoVento) {
        this.data = data;
        this.temperaturaAtual = temperaturaAtual;
        this.temperaturaMinima = temperaturaMinima;
        this.temperaturaMaxima = temperaturaMaxima;
        this.umidade = umidade;
        this.condicao = condicao;
        this.precipitacao = precipitacao;
        this.velocidadeVento = velocidadeVento;
        this.direcaoVento = direcaoVento;
    }

    @Override
    public String toString() {
        return String.format("""
             Data: %s
             Temp atual: %.1f°C
             Temp mínima: %.1f°C
             Temp máxima: %.1f°C
             Umidade: %d%%
             Condição: %s
             Precipitação: %.1f mm
             Vento: %.1f km/h (%.0f°)
            """, data, temperaturaAtual, temperaturaMinima, temperaturaMaxima,
                umidade, condicao, precipitacao, velocidadeVento, direcaoVento);
    }
}