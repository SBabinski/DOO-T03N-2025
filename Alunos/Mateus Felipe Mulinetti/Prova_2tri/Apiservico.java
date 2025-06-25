package org.aplicacao;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;


public class Apiservico {
    public final HttpClient client;
    public final ObjectMapper objectMapper;
    public static final String API_URL = "https://api.tvmaze.com/search/shows?q=";

    public Apiservico() {
        this.client = HttpClient.newHttpClient();
        this.objectMapper = new ObjectMapper();
    }

    public Series buscarSerie(String nome) throws IOException, InterruptedException {
        String encodedNome = URLEncoder.encode(nome, StandardCharsets.UTF_8);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(API_URL + encodedNome))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            JsonNode jsonArray = objectMapper.readTree(response.body());
            if (!jsonArray.isEmpty()) {
                JsonNode showData = jsonArray.get(0).get("show");


                int id = showData.get("id").asInt();
                String name = showData.get("name").asText();
                String language = showData.get("language") != null ? showData.get("language").asText() : "N/A";
                JsonNode rating = showData.get("rating");
                double average = rating.get("average") != null ? rating.get("average").asDouble() : 0.0;
                String genres = !showData.get("genres").isEmpty() ? showData.get("genres").get(0).asText() : "N/A";
                String status = showData.get("status") != null ? showData.get("status").asText() : "N/A";
                String premiered = showData.get("premiered") != null ? showData.get("premiered").asText() : "N/A";
                String ended = showData.get("ended") != null ? showData.get("ended").asText() : "N/A";
                String network = showData.get("network") != null && showData.get("network").get("name") != null ?
                        showData.get("network").get("name").asText() : "N/A";

                return new Series(id, name, language, average, genres, status, premiered, ended, network);
            }
        }
        return null;
    }
}

