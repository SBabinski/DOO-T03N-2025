package org.example.service;

import org.example.model.DadosClima;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;


public class ServicoClima {

    private static final String API_URL = "https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline";
    private static final String API_KEY = "PG7NQQ78Y8LD4CL5JJW8T7YW4";


    public DadosClima consultarClima(String cidade) throws Exception {
        try {
            // Construir a URL da requisição
            String urlString = construirURL(cidade);

            // Realizar a requisição HTTP
            String jsonResponse = fazerRequisicao(urlString);

            // Processar a resposta JSON
            return processarResposta(jsonResponse, cidade);

        } catch (Exception e) {
            throw new Exception("Erro ao consultar clima: " + e.getMessage(), e);
        }
    }


    private String construirURL(String cidade) {
        String cidadeEncoded = URLEncoder.encode(cidade, StandardCharsets.UTF_8);
        return API_URL + "/" + cidadeEncoded + 
                "?unitGroup=metric" + 
                "&include=current,days" + 
                "&key=" + API_KEY + 
                "&contentType=json";
    }


    private String fazerRequisicao(String urlString) throws IOException {
        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setConnectTimeout(10000);
        connection.setReadTimeout(10000);

        int responseCode = connection.getResponseCode();
        if (responseCode >= 200 && responseCode < 300) {
            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8))) {
                return reader.lines().collect(Collectors.joining());
            }
        } else {
            throw new IOException("Erro na API: Status " + responseCode);
        }
    }


    private DadosClima processarResposta(String jsonResponse, String cidade) {
        DadosClima dadosClima = new DadosClima();


        dadosClima.setCidade(cidade);


        String currentConditions = JSONParser.getObject(jsonResponse, "currentConditions");
        if (!currentConditions.isEmpty()) {
            dadosClima.setTemperaturaAtual(JSONParser.getDouble(currentConditions, "temp"));
            dadosClima.setUmidade(JSONParser.getDouble(currentConditions, "humidity"));
            dadosClima.setCondicaoTempo(JSONParser.getString(currentConditions, "conditions"));
            dadosClima.setPrecipitacao(JSONParser.getDouble(currentConditions, "precip"));
            dadosClima.setVelocidadeVento(JSONParser.getDouble(currentConditions, "windspeed"));
            dadosClima.setDirecaoVento(JSONParser.getString(currentConditions, "winddir"));
            dadosClima.setDescricao(JSONParser.getString(currentConditions, "description"));
        }


        String today = JSONParser.getFirstArrayObject(jsonResponse, "days");
        if (!today.isEmpty()) {
            dadosClima.setTemperaturaMaxima(JSONParser.getDouble(today, "tempmax"));
            dadosClima.setTemperaturaMinima(JSONParser.getDouble(today, "tempmin"));


            if (dadosClima.getDescricao() == null || dadosClima.getDescricao().isEmpty()) {
                dadosClima.setDescricao(JSONParser.getString(today, "description"));
            }
        }

        return dadosClima;
    }
}
