package service;

import com.google.gson.*;
import model.serie;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

public class SerieService {

    public serie buscarPorNome(String nome) throws Exception {
        String query = nome.replace(" ", "%20");
        URL url = new URL("https://api.tvmaze.com/singlesearch/shows?q=" + query);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");

        if (con.getResponseCode() != 200) throw new RuntimeException("Erro na API");

        JsonObject json = JsonParser.parseReader(new InputStreamReader(con.getInputStream())).getAsJsonObject();

        serie serie = new serie(query, query, null, null, query, query, query, query);
        serie.setNome(json.get("name").getAsString());
        serie.setIdioma(json.get("language").getAsString());
        serie.setGeneros(new Gson().fromJson(json.get("genres"), List.class));
        serie.setNota(json.get("rating").getAsJsonObject().get("average").getAsDouble());
        serie.setEstado(json.get("status").getAsString());
        serie.setDataEstreia(json.get("premiered").getAsString());
        serie.setDataFim(json.has("ended") && !json.get("ended").isJsonNull() ? json.get("ended").getAsString() : "Em andamento");
        serie.setEmissora(json.has("network") && !json.get("network").isJsonNull()
                ? json.get("network").getAsJsonObject().get("name").getAsString()
                : "Desconhecida");

        return serie;
    }
}


