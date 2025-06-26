package com.tvseries.projeto.java.service;

import com.tvseries.projeto.java.exception.ApiException;
import com.tvseries.projeto.java.model.Serie;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ConsumoApi {
    private final String ENDERECO_BASE = "https://api.tvmaze.com/search/shows?q=";
    private final Gson gson = new Gson();

    public List<Serie> buscarSeries(String nomeSerie) throws ApiException {
        HttpClient client = HttpClient.newHttpClient();
        String nomeCodificado = URLEncoder.encode(nomeSerie, StandardCharsets.UTF_8);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(ENDERECO_BASE + nomeCodificado))
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() != 200) {
                throw new ApiException("Erro na busca da API. Status: " + response.statusCode());
            }

            Type listType = new TypeToken<ArrayList<ShowWrapper>>() {}.getType();
            List<ShowWrapper> showWrappers = gson.fromJson(response.body(), listType);

            return showWrappers.stream()
                               .map(ShowWrapper::getShow)
                               .collect(Collectors.toList());

        } catch (IOException | InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new ApiException("Não foi possível conectar à API da TVMaze: " + e.getMessage());
        }
    }

    private static class ShowWrapper {
        private Serie show;
        public Serie getShow() { return show; }
    }
}