package org.example;

import com.google.gson.Gson;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

public class TVMazeAPI {
    private static final String BASE_URL = "https://api.tvmaze.com";

    public static List<Serie> buscarSeries(String nomeBusca) {
        List<Serie> series = new ArrayList<>();
        try {
            URL url = new URL(BASE_URL + "/search/shows?q=" + nomeBusca);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Erro na chamada à API: " + conn.getResponseCode());
            }

            InputStream input = conn.getInputStream();
            String json = new String(input.readAllBytes());

            Gson gson = new Gson();
            var array = gson.fromJson(json, List.class);

            for (Object obj : array) {
                Map<String, Object> item = (Map<String, Object>) obj;
                Map<String, Object> show = (Map<String, Object>) item.get("show");

                String nome = (String) show.get("name");
                String idioma = (String) show.get("language");
                List<String> generos = (List<String>) show.get("genres");

                double notaGeral = 0;
                if (show.get("rating") instanceof Map) {
                    Map<String, Object> rating = (Map<String, Object>) show.get("rating");
                    if (rating.get("average") instanceof Number) {
                        notaGeral = ((Number) rating.get("average")).doubleValue();
                    }
                }

                String estado = (String) show.getOrDefault("status", "Desconhecido");
                String dataEstreia = (String) show.get("premiered");
                String dataTermino = (String) show.get("ended");

                String emissora = "Desconhecida";
                if (show.get("network") instanceof Map) {
                    Map<String, Object> network = (Map<String, Object>) show.get("network");
                    emissora = (String) network.get("name");
                }

                series.add(new Serie(nome, idioma, generos, notaGeral, estado, dataEstreia, dataTermino, emissora));
            }
        } catch (Exception e) {
            System.out.println("Erro ao buscar séries: " + e.getMessage());
        }

        return series;
    }
}
