package org.example.services;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.example.models.Serie;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class ServicoSeries {

    public static List<Serie> buscarSeries(String nomeSerie) {
        try {
            String urlStr = "https://api.tvmaze.com/search/shows?q=" + nomeSerie.replace(" ", "%20");

            URL url = new URL(urlStr);
            HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
            conexao.setRequestMethod("GET");

            BufferedReader leitor = new BufferedReader(
                    new InputStreamReader(conexao.getInputStream())
            );

            StringBuilder respostaJson = new StringBuilder();
            String linha;
            while ((linha = leitor.readLine()) != null) {
                respostaJson.append(linha);
            }
            leitor.close();

            Gson gson = new Gson();
            JsonArray resultados = gson.fromJson(respostaJson.toString(), JsonArray.class);

            List<Serie> listaSeries = new ArrayList<>();

            for (int i = 0; i < resultados.size(); i++) {
                JsonObject item = resultados.get(i).getAsJsonObject();
                JsonObject show = item.getAsJsonObject("show");

                Serie serie = new Serie();
                serie.setId(show.get("id").getAsInt());
                serie.setNome(show.get("name").getAsString());
                serie.setIdioma(show.get("language").isJsonNull() ? "Desconhecido" : show.get("language").getAsString());

                List<String> generos = new ArrayList<>();
                JsonArray generosJson = show.getAsJsonArray("genres");
                for (int j = 0; j < generosJson.size(); j++) {
                    generos.add(generosJson.get(j).getAsString());
                }
                serie.setGeneros(generos);

                JsonObject rating = show.getAsJsonObject("rating");
                double nota = rating.get("average").isJsonNull() ? 0.0 : rating.get("average").getAsDouble();
                serie.setNotaGeral(nota);

                serie.setEstado(show.get("status").getAsString());

                serie.setDataEstreia(show.get("premiered").isJsonNull() ? "?" : show.get("premiered").getAsString());
                serie.setDataTermino(show.get("ended").isJsonNull() ? "Ainda em exibição" : show.get("ended").getAsString());

                if (!show.get("network").isJsonNull()) {
                    JsonObject network = show.getAsJsonObject("network");
                    serie.setEmissora(network.get("name").getAsString());
                } else if (!show.get("webChannel").isJsonNull()) {
                    JsonObject canal = show.getAsJsonObject("webChannel");
                    serie.setEmissora(canal.get("name").getAsString());
                } else {
                    serie.setEmissora("Desconhecida");
                }

                listaSeries.add(serie);
            }

            return listaSeries;

        } catch (Exception e) {
            System.out.println("Erro ao buscar séries: " + e.getMessage());
            return null;
        }
    }
}
