package Prova_2tri;

import java.net.*;
import java.io.*;
import java.util.*;
import com.google.gson.*;

public class TvMazeApi {

    public static List<Serie> buscarSeries(String termo) throws IOException {
        String url = construirUrlBusca(termo);
        String json = lerConteudoDaUrl(url);
        return converterJsonParaSeries(json);
    }

    private static String construirUrlBusca(String termo) throws UnsupportedEncodingException {
        String baseUrl = "https://api.tvmaze.com/search/shows?q=";
        return baseUrl + URLEncoder.encode(termo, "UTF-8");
    }

    private static String lerConteudoDaUrl(String urlStr) throws IOException {
        StringBuilder content = new StringBuilder();
        URL url = new URL(urlStr);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setConnectTimeout(5000);
        con.setReadTimeout(5000);

        try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
        } finally {
            con.disconnect();
        }
        return content.toString();
    }

    private static List<Serie> converterJsonParaSeries(String json) {
        List<Serie> series = new ArrayList<>();
        JsonArray arr = JsonParser.parseString(json).getAsJsonArray();

        for (JsonElement elem : arr) {
            JsonObject show = elem.getAsJsonObject().getAsJsonObject("show");
            series.add(criarSerieDeJson(show));
        }
        return series;
    }

    private static Serie criarSerieDeJson(JsonObject show) {
        int id = show.get("id").getAsInt();
        String nome = show.get("name").getAsString();
        String idioma = show.get("language").isJsonNull() ? "" : show.get("language").getAsString();

        List<String> generos = new ArrayList<>();
        for (JsonElement gen : show.get("genres").getAsJsonArray()) {
            generos.add(gen.getAsString());
        }

        double nota = 0.0;
        JsonObject ratingObj = show.get("rating").getAsJsonObject();
        if (!ratingObj.get("average").isJsonNull()) {
            nota = ratingObj.get("average").getAsDouble();
        }

        String estado = show.get("status").isJsonNull() ? "" : show.get("status").getAsString();
        String dataEstreia = show.get("premiered").isJsonNull() ? "" : show.get("premiered").getAsString();
        String dataFim = show.get("ended").isJsonNull() ? "" : show.get("ended").getAsString();

        String emissora = "";
        if (!show.get("network").isJsonNull()) {
            emissora = show.get("network").getAsJsonObject().get("name").getAsString();
        } else if (!show.get("webChannel").isJsonNull()) {
            emissora = show.get("webChannel").getAsJsonObject().get("name").getAsString();
        }

        return new Serie(id, nome, idioma, generos, nota, estado, dataEstreia, dataFim, emissora);
    }
}