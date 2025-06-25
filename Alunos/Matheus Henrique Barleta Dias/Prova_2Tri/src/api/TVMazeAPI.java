package api;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import model.Serie;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

public class TVMazeAPI {
    private static final String BASE_URL = "https://api.tvmaze.com/search/shows?q=";
    private static final Gson gson = new Gson();

    public static List<Serie> buscarSeries(String nome) {
        List<Serie> seriesEncontradas = new ArrayList<>();

        try {
            String urlString = BASE_URL + nome.replace(" ", "%20");
            URL url = new URL(urlString);
            HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
            conexao.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(new InputStreamReader(conexao.getInputStream()));
            StringBuilder resposta = new StringBuilder();
            String linha;

            while ((linha = reader.readLine()) != null) {
                resposta.append(linha);
            }
            reader.close();

            JsonArray array = JsonParser.parseString(resposta.toString()).getAsJsonArray();

            for (JsonElement elemento : array) {
                JsonObject showObj = elemento.getAsJsonObject().get("show").getAsJsonObject();

                int id = showObj.get("id").getAsInt();
                String titulo = showObj.get("name").getAsString();
                String idioma = showObj.get("language") != null ? showObj.get("language").getAsString() : "Desconhecido";
                double nota = showObj.get("rating").getAsJsonObject().get("average").isJsonNull()
                        ? 0.0 : showObj.get("rating").getAsJsonObject().get("average").getAsDouble();

                JsonArray generosArray = showObj.getAsJsonArray("genres");
                Type listType = new TypeToken<List<String>>(){}.getType();
                List<String> generos = gson.fromJson(generosArray, listType);

                String status = showObj.get("status").getAsString();
                String dataEstreia = showObj.get("premiered") != null && !showObj.get("premiered").isJsonNull()
                        ? showObj.get("premiered").getAsString() : "Desconhecida";
                String dataFim = showObj.get("ended") != null && !showObj.get("ended").isJsonNull()
                        ? showObj.get("ended").getAsString() : "Ainda em exibição";

                String emissora = "Desconhecida";
                if (showObj.has("network") && !showObj.get("network").isJsonNull()) {
                    emissora = showObj.get("network").getAsJsonObject().get("name").getAsString();
                } else if (showObj.has("webChannel") && !showObj.get("webChannel").isJsonNull()) {
                    emissora = showObj.get("webChannel").getAsJsonObject().get("name").getAsString();
                }

                Serie serie = new Serie(id, titulo, idioma, generos, nota, status, dataEstreia, dataFim, emissora);
                seriesEncontradas.add(serie);
            }

        } catch (Exception e) {
            System.out.println("Erro ao buscar séries: " + e.getMessage());
        }

        return seriesEncontradas;
    }
}