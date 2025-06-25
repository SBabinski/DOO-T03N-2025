package com.example;

import com.fasterxml.jackson.databind.ObjectMapper;


import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.util.Scanner;

public class ConsultaClima {

    private static final String URL_BASE = "https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/";
    private static final Scanner leitor = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("=== Sistema de Consulta de Clima ===");

        String chaveApi = solicitarChaveApi();

        while (true) {
            String cidade = solicitarCidade();

            DiaClima climaHoje = buscarClimaAtual(chaveApi, cidade);

            if (climaHoje != null) {
                exibirDadosClimaticos(cidade, climaHoje);
            }

            if (!querContinuar()) {
                System.out.println("Programa finalizado. Até logo!");
                break;
            }
        }
    }

    private static String solicitarChaveApi() {
        System.out.print("Digite sua chave de API: ");
        return leitor.nextLine().trim();
    }

    private static String solicitarCidade() {
        System.out.print("Informe o nome da cidade (Ex: São Paulo,BR): ");
        return leitor.nextLine().trim();
    }

    private static boolean querContinuar() {
        System.out.print("Deseja consultar outra cidade? (s/n): ");
        String resposta = leitor.nextLine().trim().toLowerCase();
        return resposta.equals("s");
    }

    private static DiaClima buscarClimaAtual(String chaveApi, String cidade) {
        try {
            String dataHoje = LocalDate.now().toString();
            String urlConsulta = URL_BASE + cidade + "/" + dataHoje + "?unitGroup=metric&key=" + chaveApi;

            HttpClient cliente = HttpClient.newHttpClient();
            HttpRequest requisicao = HttpRequest.newBuilder()
                    .uri(URI.create(urlConsulta))
                    .GET()
                    .build();

            HttpResponse<String> resposta = cliente.send(requisicao, HttpResponse.BodyHandlers.ofString());

            System.out.println("Status HTTP: " + resposta.statusCode());
            // System.out.println("Resposta JSON: " + resposta.body());

            if (resposta.statusCode() == 200) {
                ObjectMapper mapper = new ObjectMapper();
                RespostaClima respostaClima = mapper.readValue(resposta.body(), RespostaClima.class);

                if (respostaClima != null && respostaClima.getDias() != null && !respostaClima.getDias().isEmpty()) {
                    return respostaClima.getDias().get(0);
                } else {
                    System.out.println("Nenhum dado de clima encontrado para essa cidade.");
                }
            } else if (resposta.statusCode() == 401) {
                System.out.println("Erro: Chave de API inválida.");
            } else if (resposta.statusCode() == 429) {
                System.out.println("Erro: Limite de consultas atingido. Tente mais tarde.");
            } else {
                System.out.println("Erro: " + resposta.body());
            }

        } catch (Exception e) {
            System.out.println("Erro ao buscar dados do clima: " + e.getMessage());
            e.printStackTrace();
        }

        return null;
    }

    private static void exibirDadosClimaticos(String cidade, DiaClima clima) {
        System.out.println("\n--- Clima em " + cidade + " ---");
        System.out.printf("Data: %s%n", clima.getData());
        System.out.printf("Temperatura Atual: %.1f°C%n", clima.getTemperatura());
        System.out.printf("Máxima: %.1f°C | Mínima: %.1f°C%n", clima.getTemperaturaMaxima(), clima.getTemperaturaMinima());
        System.out.printf("Umidade: %.1f%%%n", clima.getUmidade());
        System.out.printf("Condição: %s%n", clima.getCondicao());
        System.out.printf("Precipitação: %.1f mm%n", clima.getPrecipitacao());
        System.out.printf("Vento: %.1f km/h | Direção: %.1f°%n", clima.getVelocidadeVento(), clima.getDirecaoVento());
        System.out.println("-------------------------------\n");
    }
}
