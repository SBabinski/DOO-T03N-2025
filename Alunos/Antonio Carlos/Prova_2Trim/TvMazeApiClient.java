package com.trabalhotvmaze.series;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class TvMazeApiClient {
    private final String API_BASE_URL = "https://api.tvmaze.com/search/shows?q=";
    private final HttpClient client = HttpClient.newHttpClient();
    private final ObjectMapper objectMapper = new ObjectMapper();

    public TvMazeApiClient() {
        // Registra o módulo para conseguir lidar com datas (LocalDate, etc.)
        objectMapper.registerModule(new JavaTimeModule());
    }

    public List<Serie> buscarSeries(String nome) {
        try {
            String encodedName = URLEncoder.encode(nome, StandardCharsets.UTF_8);
            String url = API_BASE_URL + encodedName;

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                List<ResultadoBusca> resultados = objectMapper.readValue(response.body(), new TypeReference<>() {});
                return resultados.stream()
                                 .map(ResultadoBusca::getShow)
                                 .collect(Collectors.toList());
            } else {
                System.out.println("Erro ao buscar séries. Status code: " + response.statusCode());
                return Collections.emptyList();
            }

        } catch (Exception e) {
            System.err.println("Ocorreu um erro na chamada da API: " + e.getMessage());
            return Collections.emptyList();
        }
    }
}
