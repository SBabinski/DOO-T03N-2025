import com.google.gson.*;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class GerenciadorSeries {

    public static Serie buscarSerie(String nome) {
        try {
            String endereco = "https://api.tvmaze.com/search/shows?q=" + nome.replace(" ", "%20");
            URL url = new URL(endereco);

            HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
            conexao.setRequestMethod("GET");

            InputStreamReader leitor = new InputStreamReader(conexao.getInputStream());
            JsonArray resultados = JsonParser.parseReader(leitor).getAsJsonArray();

            if (resultados.size() > 0) {
                JsonObject show = resultados.get(0).getAsJsonObject().getAsJsonObject("show");

                int id = show.get("id").getAsInt();
                String titulo = show.get("name").getAsString();
                String idioma = show.get("language").isJsonNull() ? "Desconhecido" : show.get("language").getAsString();

                List<String> generos = new ArrayList<>();
                for (JsonElement g : show.get("genres").getAsJsonArray()) {
                    generos.add(g.getAsString());
                }

                double nota = 0.0;
                if (!show.get("rating").isJsonNull() && show.get("rating").getAsJsonObject().get("average") != null) {
                    if (!show.get("rating").getAsJsonObject().get("average").isJsonNull()) {
                        nota = show.get("rating").getAsJsonObject().get("average").getAsDouble();
                    }
                }

                String status = show.get("status").getAsString();
                String estreia = show.get("premiered").isJsonNull() ? "?" : show.get("premiered").getAsString();
                String fim = show.get("ended").isJsonNull() ? "?" : show.get("ended").getAsString();

                String emissora = "?";
                if (show.has("network") && !show.get("network").isJsonNull()) {
                    emissora = show.get("network").getAsJsonObject().get("name").getAsString();
                } else if (show.has("webChannel") && !show.get("webChannel").isJsonNull()) {
                    emissora = show.get("webChannel").getAsJsonObject().get("name").getAsString();
                }

                return new Serie(id, titulo, idioma, generos, nota, status, estreia, fim, emissora);
            }

        } catch (Exception e) {
            System.out.println("Erro ao buscar série: " + e.getMessage());
        }
        return null;
    }
}