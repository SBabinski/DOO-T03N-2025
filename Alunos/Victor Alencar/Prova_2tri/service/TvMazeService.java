package service;

import com.google.gson.*;
import model.Serie;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class TvMazeService {

    public static Serie buscarSerie(String nome) {
        try {
            String query = URLEncoder.encode(nome, StandardCharsets.UTF_8);
            String urlStr = "https://api.tvmaze.com/singlesearch/shows?q=" + query;
            URL url = new URL(urlStr);

            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setConnectTimeout(5000);
            con.setReadTimeout(5000);

            int status = con.getResponseCode();
            if (status != 200) {
                System.out.println("Erro na API: código " + status);
                return null;
            }

            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            StringBuilder content = new StringBuilder();
            String linha;
            while ((linha = in.readLine()) != null) {
                content.append(linha);
            }
            in.close();

            JsonObject json = JsonParser.parseString(content.toString()).getAsJsonObject();

            Serie serie = new Serie();
            serie.setNome(json.get("name").getAsString());
            serie.setIdioma(json.get("language").getAsString());

            // Gêneros
            List<String> generos = new ArrayList<>();
            JsonArray arrGeneros = json.getAsJsonArray("genres");
            for (JsonElement el : arrGeneros) {
                generos.add(el.getAsString());
            }
            serie.setGeneros(generos);

            // Nota
            JsonObject rating = json.getAsJsonObject("rating");
            if (rating != null && !rating.get("average").isJsonNull()) {
                serie.setNota(rating.get("average").getAsDouble());
            } else {
                serie.setNota(0.0);
            }

            serie.setStatus(json.get("status").getAsString());
            serie.setDataEstreia(json.get("premiered").isJsonNull() ? null : json.get("premiered").getAsString());
            serie.setDataTermino(json.get("ended").isJsonNull() ? null : json.get("ended").getAsString());

            // Emissora
            if (json.has("network") && !json.get("network").isJsonNull()) {
                JsonObject network = json.getAsJsonObject("network");
                serie.setEmissora(network.get("name").getAsString());
            } else if (json.has("webChannel") && !json.get("webChannel").isJsonNull()) {
                JsonObject webChannel = json.getAsJsonObject("webChannel");
                serie.setEmissora(webChannel.get("name").getAsString());
            } else {
                serie.setEmissora("Desconhecida");
            }

            // Descrição - remove tags HTML
            String descricao = json.get("summary").isJsonNull() ? "" : json.get("summary").getAsString();
            serie.setDescricao(descricao.replaceAll("<[^>]*>", ""));

            return serie;

        } catch (Exception e) {
            System.out.println("Erro ao buscar serie: " + e.getMessage());
            return null;
        }
    }
}
