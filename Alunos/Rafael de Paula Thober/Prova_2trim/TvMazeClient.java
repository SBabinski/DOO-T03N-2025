import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class TvMazeClient {

    private static final String BASE_URL = "https://api.tvmaze.com";
    private final Gson gson = new Gson();

    public List<Show> searchShows(String name) {
        try {
            String encoded = java.net.URLEncoder.encode(name, "UTF-8");
            URL url = new URL(BASE_URL + "/search/shows?q=" + encoded);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            try (InputStreamReader reader = new InputStreamReader(conn.getInputStream())) {
                List<SearchResult> results = gson.fromJson(reader, new TypeToken<List<SearchResult>>() {}.getType());
                return results.stream()
                    .map(result -> result.show)
                    .toList();
            }
        } catch (java.io.IOException e) {
            System.out.println("❌ Erro de conexão: verifique sua internet ou a disponibilidade da API.");
        } catch (Exception e) {
            System.out.println("❌ Erro inesperado ao buscar séries: " + e.getMessage());
        }
        return List.of();
    }

    public Show getShowById(int id) {
        try {
            URL url = new URL(BASE_URL + "/shows/" + id);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            try (InputStreamReader reader = new InputStreamReader(conn.getInputStream())) {
                return gson.fromJson(reader, Show.class);
            }
        } catch (java.io.IOException e) {
            System.out.println("❌ Erro de conexão ao buscar série por ID: verifique sua internet.");
        } catch (Exception e) {
            System.out.println("❌ Erro inesperado ao buscar série por ID: " + e.getMessage());
        }
        return null;
    }

    private static class SearchResult {
        Show show;
    }
}
