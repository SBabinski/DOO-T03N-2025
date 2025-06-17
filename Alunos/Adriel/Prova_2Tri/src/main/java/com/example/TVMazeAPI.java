package com.example;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class TVMazeAPI {
    private static final HttpClient httpClient = HttpClient.newHttpClient();
    private static final Gson gson = new Gson();

    public static List<Serie> buscarSeries(String nome) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.tvmaze.com/search/shows?q=" + nome.replace(" ", "%20")))
                .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            
            List<Map<String, Object>> resultados = gson.fromJson(response.body(), new TypeToken<List<Map<String, Object>>>(){}.getType());
            List<Serie> series = new ArrayList<>();
            
            for (Map<String, Object> item : resultados) {
                Map<String, Object> show = (Map<String, Object>) item.get("show");
                Serie serie = new Serie();
                
                serie.setId(((Double) show.get("id")).intValue());
                serie.setName((String) show.get("name"));
                serie.setLanguage((String) show.get("language"));
                
                // Gêneros
                List<String> generos = (List<String>) show.get("genres");
                serie.setGenres(generos);
                
                // Nota
                Map<String, Object> rating = (Map<String, Object>) show.get("rating");
                serie.setRating(rating.get("average") != null ? ((Double) rating.get("average")) : 0.0);
                
                serie.setStatus((String) show.get("status"));
                serie.setPremiered((String) show.get("premiered"));
                serie.setEnded((String) show.get("ended"));
                
                // Emissora
                Map<String, Object> network = (Map<String, Object>) show.get("network");
                serie.setNetwork(network != null ? (String) network.get("name") : "Desconhecida");
                
                series.add(serie);
            }
            return series;
        } catch (Exception e) {
            System.err.println("Erro na busca: " + e.getMessage());
            return Collections.emptyList();
        }
    }
}