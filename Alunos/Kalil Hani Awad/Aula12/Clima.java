package org.example;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;

@JsonIgnoreProperties(ignoreUnknown = true)

public class Clima {
    public static Scanner scanner = new Scanner(System.in);
    private static String API_KEY;
    private static final String BASE_URL = "https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/";

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
    private String condicaoTempo;

    @JsonProperty("precip")
    private double precipitacao;

    @JsonProperty("windspeed")
    private double velocidadeVento;

    @JsonProperty("winddir")
    private double direcaoVento;

    public Clima() {
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public double getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(double temperatura) {
        this.temperatura = temperatura;
    }

    public double getTemperaturaMaxima() {
        return temperaturaMaxima;
    }

    public void setTemperaturaMaxima(double temperaturaMaxima) {
        this.temperaturaMaxima = temperaturaMaxima;
    }

    public double getTemperaturaMinima() {
        return temperaturaMinima;
    }

    public void setTemperaturaMinima(double temperaturaMinima) {
        this.temperaturaMinima = temperaturaMinima;
    }

    public double getUmidade() {
        return umidade;
    }

    public void setUmidade(double umidade) {
        this.umidade = umidade;
    }

    public String getCondicaoTempo() {
        return condicaoTempo;
    }

    public void setCondicaoTempo(String condicaoTempo) {
        this.condicaoTempo = condicaoTempo;
    }

    public double getPrecipitacao() {
        return precipitacao;
    }

    public void setPrecipitacao(double precipitacao) {
        this.precipitacao = precipitacao;
    }

    public double getVelocidadeVento() {
        return velocidadeVento;
    }

    public void setVelocidadeVento(double velocidadeVento) {
        this.velocidadeVento = velocidadeVento;
    }

    public double getDirecaoVento() {
        return direcaoVento;
    }

    public void setDirecaoVento(double direcaoVento) {
        this.direcaoVento = direcaoVento;
    }

    @Override
    public String toString() {
        return String.format(
                "Clima [Data: %s, Temperatura: %.1f°C, Máxima: %.1f°C, Mínima: %.1f°C, Umidade: %.1f%%, Condição: %s, Precipitação: %.1f mm, Velocidade do Vento: %.1f km/h, Direção do Vento: %.1f°]",
                data, temperatura, temperaturaMaxima, temperaturaMinima, umidade, condicaoTempo, precipitacao, velocidadeVento, direcaoVento
        );
    }

    public static void API_Resposta() {
        Boolean escolha = true;
        while (escolha) {
            try {
                System.out.println("Coloque a sua chave");
                String KEY = scanner.nextLine().trim();

                API_KEY = KEY;

                System.out.println("Coloque o nome de uma cidade, exemplo: London,UK");
                String cidade = scanner.nextLine().trim();
                Clima clima = fetchWeatherData(cidade);

                if (clima != null) {
                    System.out.println("Clima atual para " + cidade + ":");
                    System.out.println(clima);
                } else {
                    System.out.println("Não foi possível obter os dados climáticos.");
                }
                try {
                    System.out.println("Gostaria de continuar procurando climas? 1 - não e 2 - sim");
                    int opc = scanner.nextInt();

                    if (opc == 1) {
                        escolha = false;
                        System.out.println("Você saiu");
                        return;
                    } else if (opc == 2) {
                        break;
                    } else {
                        System.out.println("Opção incorreta");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Coloque um número");
                }
            } catch (Exception e) {
                System.err.println("Erro ao consultar a API: " + e.getMessage());
                scanner.nextLine();
            }
        }
    }

    public static Clima fetchWeatherData(String cidade) throws Exception {
        String dataAtual = LocalDate.now().toString();

        String urlString = BASE_URL + cidade + "/" + dataAtual + "?key=" + API_KEY;
        URI url = new URI(urlString);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(url)
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println("Status da resposta: " + response.statusCode());

        if (response.statusCode() == 200) {
            ObjectMapper mapper = new ObjectMapper();
            WeatherResponse weatherResponse = mapper.readValue(response.body(), WeatherResponse.class);

            if (weatherResponse.getDays() != null && !weatherResponse.getDays().isEmpty()) {
                return weatherResponse.getDays().get(0);
            } else {
                System.out.println("Nenhum dado climático retornado para " + cidade);
                return null;
            }
        } else if (response.statusCode() == 429) {
            System.out.println("Erro 429: Limite de chamadas excedido. Tente novamente mais tarde.");
            return null;
        } else if (response.statusCode() == 401) {
            System.out.println("Erro 401: Chave de API inválida ou não autorizada.");
            return null;
        } else {
            System.out.println("Erro " + response.statusCode() + ": " + response.body());
            return null;
        }
    }


}