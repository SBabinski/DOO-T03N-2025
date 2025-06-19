import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

// Classe que busca informações de séries na API TVmaze

public class SerieService {

    public static Serie buscarSerie(String nome) { // Método que busca uma série pelo nome na API TVmaze
        try {
           String nomeFormatado = nome.replace(" ", "%20");
           URI url = new URI("https://api.tvmaze.com/search/shows?q=" + nomeFormatado);

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder(url).GET().build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                String json = response.body();
                JsonArray resultados = JsonParser.parseString(json).getAsJsonArray();

                if (resultados.size() > 0) { // Verifica se há resultados
                    JsonObject show = resultados.get(0).getAsJsonObject().get("show").getAsJsonObject();

                    String titulo = show.get("name").getAsString();
                    String idioma = show.get("language").getAsString();
                    JsonArray generosJson = show.get("genres").getAsJsonArray();
                    String[] generos = new String[generosJson.size()];
                    for (int i = 0; i < generosJson.size(); i++) {
                        generos[i] = generosJson.get(i).getAsString();
                    } 
                    double nota = show.get("rating").getAsJsonObject().get("average").isJsonNull() ? 0.0
                            : show.get("rating").getAsJsonObject().get("average").getAsDouble();
                    String status = show.get("status").getAsString(); 
                    String dataEstreia = show.get("premiered").isJsonNull() ? "" : show.get("premiered").getAsString();
                    String dataFim = show.get("ended").isJsonNull() ? "" : show.get("ended").getAsString();
                    String emissora = show.get("network") != null && !show.get("network").isJsonNull()
                            ? show.get("network").getAsJsonObject().get("name").getAsString()
                            : "Plataforma online";

                    return new Serie(titulo, idioma, generos, nota, status, dataEstreia, dataFim, emissora);
                } else {
                    return null; 
                } 
            } else {
                System.out.println("Erro na requisição: " + response.statusCode());
                return null;
            } 
        } catch (Exception e) {
            System.out.println("Erro ao buscar série: " + e.getMessage());
            return null;
        }
    }
} 