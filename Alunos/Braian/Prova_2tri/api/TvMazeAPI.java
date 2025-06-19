package com.braian.seriestracker.api;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.braian.seriestracker.model.Serie;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class TvMazeAPI {
    private static final String BASE_URL = "https://api.tvmaze.com";
    private final OkHttpClient client = new OkHttpClient();

    private static final Map<String, String> statusTranslations = new HashMap<>();

    static {
        statusTranslations.put("Running", "Em exibição");
        statusTranslations.put("Ended", "Encerrado");
        statusTranslations.put("To Be Determined", "A ser determinado");
        statusTranslations.put("In Development", "Em desenvolvimento");
        statusTranslations.put("Canceled", "Cancelado");
    }

    private String translateStatus(String status) {
        return statusTranslations.getOrDefault(status, status);
    }

    public List<Serie> buscarSerie(String nome) throws IOException {
        String url = BASE_URL + "/search/shows?q=" + nome.replace(" ", "%20");
        Request request = new Request.Builder().url(url).build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                String errorMessage = "Erro na requisição: " + response.code() + " " + response.message();
                if (response.code() == 404) {
                    errorMessage = "Série não encontrada na API. Código de erro: 404.";
                } else if (response.code() == 429) {
                    errorMessage = "Limite de requisições excedido. Tente novamente mais tarde. Código de erro: 429.";
                }
                throw new IOException(errorMessage);
            }

            ResponseBody body = response.body();
            if (body == null) {
                throw new IOException("Resposta sem corpo.");
            }

            String responseBody = body.string();
            JsonArray array = JsonParser.parseString(responseBody).getAsJsonArray();

            List<Serie> series = new ArrayList<>();

            for (JsonElement elemento : array) {
                JsonObject show = elemento.getAsJsonObject().getAsJsonObject("show");

                int id = show.get("id").getAsInt();
                String titulo = show.get("name").getAsString();
                String idioma = show.has("language") && !show.get("language").isJsonNull()
                        ? show.get("language").getAsString() : "N/A";

                JsonArray generosJson = show.getAsJsonArray("genres");
                List<String> generos = new ArrayList<>();
                generosJson.forEach(g -> generos.add(g.getAsString()));

                double nota = show.has("rating")
                        && show.getAsJsonObject("rating").has("average")
                        && !show.getAsJsonObject("rating").get("average").isJsonNull()
                        ? show.getAsJsonObject("rating").get("average").getAsDouble()
                        : 0.0;

                String status = show.has("status") && !show.get("status").isJsonNull()
                        ? translateStatus(show.get("status").getAsString())
                        : "Desconhecido";

                String dataEstreia = show.has("premiered") && !show.get("premiered").isJsonNull()
                        ? show.get("premiered").getAsString()
                        : "N/A";

                String dataTermino = show.has("ended") && !show.get("ended").isJsonNull()
                        ? show.get("ended").getAsString()
                        : null;

                String emissora = "N/A";
                if (show.has("network") && !show.get("network").isJsonNull()) {
                    emissora = show.getAsJsonObject("network").get("name").getAsString();
                } else if (show.has("webChannel") && !show.get("webChannel").isJsonNull()) {
                    emissora = show.getAsJsonObject("webChannel").get("name").getAsString();
                }

                Serie serie = new Serie(id, titulo, idioma, generos, nota, status, dataEstreia, dataTermino, emissora);
                series.add(serie);
            }

            return series;
        }
    }
}

