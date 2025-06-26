package com.sistematv.api;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.sistematv.model.Serie;

public class TvMazeAPI {

    public List<Serie> buscarSeries(String nomeBusca, int ordem) throws Exception {
        String url = "https://api.tvmaze.com/search/shows?q=" + nomeBusca.replace(" ", "%20");
        HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
        conn.setRequestMethod("GET");

        JsonArray jsonArray = JsonParser.parseReader(new InputStreamReader(conn.getInputStream())).getAsJsonArray();
        List<Serie> series = new ArrayList<>();

        for (JsonElement element : jsonArray) {
            JsonObject show = element.getAsJsonObject().get("show").getAsJsonObject();
            int id = show.get("id").getAsInt();
            String nome = show.get("name").getAsString();
            String idioma = show.has("language") && !show.get("language").isJsonNull()
                    ? show.get("language").getAsString()
                    : "-";
            Double nota = 0.0;
            if (show.has("rating") && !show.get("rating").isJsonNull()) {
                JsonObject ratingObj = show.get("rating").getAsJsonObject();
                if (ratingObj.has("average") && !ratingObj.get("average").isJsonNull()) {
                    nota = ratingObj.get("average").getAsDouble();
                }
            }
            String status = show.has("status") ? show.get("status").getAsString() : "-";
            // Traduzindo o status para português do Brasil
            String statusPtBr;
            switch (status.toLowerCase()) {
                case "ended" ->
                    statusPtBr = "Concluída";
                case "running" ->
                    statusPtBr = "Em exibição";
                case "to be determined", "tbd" ->
                    statusPtBr = "A ser determinado";
                default ->
                    statusPtBr = status;
            }
            status = statusPtBr;
            String dataEstreia = show.has("premiered") && !show.get("premiered").isJsonNull()
                    ? show.get("premiered").getAsString()
                    : "-";
            String dataFim = show.has("ended") && !show.get("ended").isJsonNull() ? show.get("ended").getAsString()
                    : null;
            String emissora = "-";
            if (show.has("network") && !show.get("network").isJsonNull()) {
                emissora = show.get("network").getAsJsonObject().get("name").getAsString();
            } else if (show.has("webChannel") && !show.get("webChannel").isJsonNull()) {
                JsonObject webChannel = show.get("webChannel").getAsJsonObject();
                if (webChannel.has("name") && !webChannel.get("name").isJsonNull()) {
                    emissora = webChannel.get("name").getAsString();
                }
            }
            List<String> generos = new ArrayList<>();
            show.get("genres").getAsJsonArray().forEach(g -> generos.add(g.getAsString()));

            series.add(new Serie(id, nome, idioma, generos, nota, status, dataEstreia, dataFim, emissora));
        }

        switch (ordem) {
            case 1 ->
                series.sort((s1, s2) -> s1.getNome().compareToIgnoreCase(s2.getNome()));
            case 2 ->
                series.sort((s1, s2) -> Double.compare(s2.getNota(), s1.getNota()));
            case 3 ->
                series.sort((s1, s2) -> s1.getStatus().compareToIgnoreCase(s2.getStatus()));
            case 4 ->
                series.sort((s1, s2) -> s1.getDataEstreia().compareToIgnoreCase(s2.getDataEstreia()));
            default -> {
            }
        }

        return series;
    }
}
