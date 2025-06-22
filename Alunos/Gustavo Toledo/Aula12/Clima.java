public class Clima {
    private String cidade;
    private String temperatura;
    private String tempMax;
    private String tempMin;
    private String umidade;
    private String condicao;
    private String precipitacao;
    private String vento;
    private String direcaoVento;

    public Clima(String cidade, String temperatura, String tempMax, String tempMin, String umidade, String condicao, String precipitacao, String vento, String direcaoVento) {
        this.cidade = cidade;
        this.temperatura = temperatura;
        this.tempMax = tempMax;
        this.tempMin = tempMin;
        this.umidade = umidade;
        this.condicao = condicao;
        this.precipitacao = precipitacao;
        this.vento = vento;
        this.direcaoVento = direcaoVento;
    }

    public void exibir() {
        System.out.println("\n=== Clima em " + cidade + " ===");
        System.out.println("Temperatura atual: " + temperatura);
        System.out.println("Temperatura máxima: " + tempMax);
        System.out.println("Temperatura mínima: " + tempMin);
        System.out.println("Umidade: " + umidade);
        System.out.println("Condição: " + condicao);
        System.out.println("Precipitação: " + precipitacao);
        System.out.println("Vento: " + vento);
        System.out.println("Direção do vento: " + direcaoVento);
    }
}