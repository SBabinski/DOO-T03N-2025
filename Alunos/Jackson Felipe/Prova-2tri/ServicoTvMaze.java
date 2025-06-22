package seriesapp;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

// Classe responsável por se comunicar com a API da TVMaze
public class ServicoTvMaze {
    private final HttpClient client = HttpClient.newHttpClient();
    private final Gson gson = new Gson();

    public List<Serie> buscarSerie(String nome) {
        try {
            String nomeCodificado = URLEncoder.encode(nome, StandardCharsets.UTF_8);
            String url = "https://api.tvmaze.com/search/shows?q=" + nomeCodificado;

            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                List<ResultadoBusca> searchResults = gson.fromJson(response.body(), new TypeToken<List<ResultadoBusca>>(){}.getType());
                List<Serie> series = new ArrayList<>();
                for (ResultadoBusca result : searchResults) {
                    if(result.getShow() != null) {
                        series.add(result.getShow());
                    }
                }
                return series;
            }
        } catch (Exception e) {
            System.err.println("ERRO: Falha ao buscar série na API. " + e.getMessage());
        }
        return new ArrayList<>();
    }
}