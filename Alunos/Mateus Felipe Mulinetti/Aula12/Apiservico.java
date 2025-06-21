package org.aplicacao;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Apiservico {

    //git da restições se adicionar senhas
    public static final String API_KEY = "";

    public Tempo getTempo(String nomeCidade) throws IOException, InterruptedException {
        try {
            HttpClient client = HttpClient.newHttpClient();
            String url = String.format(
                    "https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services" +
                            "/timeline/%s/today?unitGroup=metric&include=current&key=%s&contentType=json",
                    nomeCidade.replace(" ", "%20"),
                    API_KEY
            );
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.readTree(response.body());
            JsonNode currentNode = rootNode.path("currentConditions");
            
            Tempo tempo = new Tempo();
            tempo.setTemp((float) currentNode.path("temp").asDouble());
            tempo.setTempMax((float) rootNode.path("days").get(0).path("tempmax").asDouble());
            tempo.setTempMin((float) rootNode.path("days").get(0).path("tempmin").asDouble());
            tempo.setHumidade((float) currentNode.path("humidity").asDouble());
            tempo.setCondicao(currentNode.path("conditions").asText());
            tempo.setPrecipitacao((float) rootNode.path("days").get(0).path("precipprob").asDouble() / 100);
            tempo.setVelocidadeVento((float) currentNode.path("windspeed").asDouble());
            tempo.setDirecaoVento((float) currentNode.path("winddir").asDouble());

            return tempo;
        } catch (Exception e) {
            throw new IOException("Erro ao obter dados do tempo: " + e.getMessage(), e);
        }
    }
}

