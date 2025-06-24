package fag;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite a cidade que deseja visualizar o clima e tempo no momento ou digite '0' para sair: ");
        String cidade = scanner.nextLine();

        if (cidade.equals("0")) {
            System.out.println("Saindo do sistema...");
            System.exit(0);
        } else {
            requestAPI(cidade);
        }

        main(args);

        scanner.close();
    }

    public static void requestAPI(String cidade) {
        HttpClient client = HttpClient.newHttpClient();

        String fullUrl = ApiConfig.BASE_URL + cidade + "?key=" + ApiConfig.API_KEY
                + "&unitGroup=metric&include=current&lang=pt";

        try {
            URI url = new URI(fullUrl);

            HttpRequest request = HttpRequest.newBuilder(url)
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                if (response.body().contains("\"error\"")) {
                    System.out.println("Erro da API: Cidade não encontrada ou erro de chave.");
                    System.out.println("Resposta da API: " + response.body());
                    return;
                }

                processarRespostaClima(response.body());

            } else if (response.statusCode() == 400 || response.statusCode() == 404) {
                System.out.println("Erro: Cidade não encontrada. Por favor, verifique o nome digitado.");
            } else {
                System.out.println("Erro na requisição. Código HTTP: " + response.statusCode());
            }

        } catch (URISyntaxException | IOException | InterruptedException e) {
            System.out.println("Erro ao fazer a requisição: " + e.getMessage());
        }
    }

    public static void processarRespostaClima(String json) {
        ObjectMapper mapper = new ObjectMapper();

        try {
            JsonNode root = mapper.readTree(json);

            JsonNode currentConditions = root.get("currentConditions");
            JsonNode days = root.get("days").get(0);

            double tempAtual = currentConditions.get("temp").asDouble();
            double tempMax = days.get("tempmax").asDouble();
            double tempMin = days.get("tempmin").asDouble();
            double umidade = currentConditions.get("humidity").asDouble();
            String condicao = currentConditions.get("conditions").asText();
            double precipitacao = currentConditions.has("precip") ? currentConditions.get("precip").asDouble() : 0.0;
            double velocidadeVento = currentConditions.get("windspeed").asDouble();
            String direcaoVento = currentConditions.get("winddir").asText();

            System.out.println("------- Clima atual em " + root.get("address").asText() + " -------");
            System.out.println("Temperatura Atual: " + tempAtual + "°C");
            System.out.println("Temperatura Máxima: " + tempMax + "°C");
            System.out.println("Temperatura Mínima: " + tempMin + "°C");
            System.out.println("Umidade: " + umidade + "%");
            System.out.println("Condição do Tempo: " + condicao);
            System.out.println("Precipitação: " + precipitacao + " mm");
            System.out.println("Velocidade do Vento: " + velocidadeVento + " km/h");
            System.out.println("Direção do Vento: " + direcaoVento + "°");
            System.out.println("--------------");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}