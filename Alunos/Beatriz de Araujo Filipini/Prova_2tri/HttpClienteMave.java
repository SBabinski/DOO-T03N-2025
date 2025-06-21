import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName; 
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

public class HttpClienteMave {
    private static final String BASE_URL = "https://api.tvmaze.com";
    private final Gson gson = new Gson();
    private final HttpClient httpClient = HttpClient.newHttpClient();

   
    private static class SearchResultItem {
        @SerializedName("score") 
        private double score;
        @SerializedName("show") 
        private Serie show;

        public Serie getShow() {
            return show;
        }
    
    }

    public List<Serie> buscarSeriesPorNome(String nome) throws IOException, InterruptedException {
        String encodedNome = URLEncoder.encode(nome, StandardCharsets.UTF_8.toString());
        String url = BASE_URL + "/search/shows?q=" + encodedNome;

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                String result = response.body();
               
                Type listType = new TypeToken<List<SearchResultItem>>(){}.getType();
                List<SearchResultItem> searchResults = gson.fromJson(result, listType);

                List<Serie> series = new ArrayList<>();
                for (SearchResultItem item : searchResults) {
                    series.add(item.getShow()); 
                }
                return series;
            } else {
                System.err.println("Erro na requisição à API TVMaze: Status " + response.statusCode());
                return new ArrayList<>();
            }
        } catch (IOException | InterruptedException e) {
            System.err.println("Ocorreu um erro durante a comunicação com a API TVMaze: " + e.getMessage());
            throw e;
        } catch (com.google.gson.JsonSyntaxException e) { 
            System.err.println("Erro de sintaxe JSON ao processar resposta da API: " + e.getMessage());
            
            throw new IOException("Erro ao interpretar resposta da API TVMaze.", e);
        }
    }
}