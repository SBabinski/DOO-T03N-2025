import java.io.*;
import java.net.*;
import java.util.*;
import com.google.gson.*;
import com.google.gson.reflect.*;

public class TVMazeAPI {

    private static final String BASE_URL = "https://api.tvmaze.com";

    public static List<Serie> buscarSeries(String nome) {

        List<Serie> series = new ArrayList<>();

        try {

            String urlStr = BASE_URL + "/search/shows?q=" + URLEncoder.encode(nome, "UTF-8");
            String resposta = fazerRequisicao(urlStr);

            JsonArray array = JsonParser.parseString(resposta).getAsJsonArray();

            for (JsonElement elem : array) {

                JsonObject show = elem.getAsJsonObject().getAsJsonObject("show");
                series.add(parseSerie(show));

            }

        } catch (UnknownHostException e) {

            System.out.println("| Sem conexao com a internet. Verifique sua conexao e tente novamente.");

        } catch (IOException e) {

            System.out.println("| Erro de conexao. Verifique sua conexao e tente novamente." + e.getMessage());

        } catch (Exception e) {

            System.out.println("| Erro ao buscar series. Tente novamente." + e.getMessage());

        }

        return series;

    }

    private static String fazerRequisicao(String urlStr) throws IOException {

        try {

            URL url = new URL(urlStr);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
            StringBuilder resposta = new StringBuilder();
            String inputLine;

            while ((inputLine = in.readLine()) != null) {

                resposta.append(inputLine);

            }

            in.close();
            return resposta.toString();

        } catch (UnknownHostException e) {

            throw new UnknownHostException("| Sem conexao com a internet.");

        } catch (IOException e) {

            throw new IOException("| Erro ao acessar a internet. " + e.getMessage(), e);

        }
    }

    private static Serie parseSerie(JsonObject show) {

        long id = show.get("id").getAsLong();
        String nome = show.get("name").getAsString();
        String idioma = show.has("language") && !show.get("language").isJsonNull() ? show.get("language").getAsString() : "";
        List<String> generos = new Gson().fromJson(show.get("genres"), new TypeToken<List<String>>(){}.getType());
        Double nota = show.has("rating") && show.get("rating").isJsonObject() && show.getAsJsonObject("rating").has("average") && !show.getAsJsonObject("rating").get("average").isJsonNull() ? show.getAsJsonObject("rating").get("average").getAsDouble() : null;
        String estado = show.has("status") && !show.get("status").isJsonNull() ? show.get("status").getAsString() : "";
        String estreia = show.has("premiered") && !show.get("premiered").isJsonNull() ? show.get("premiered").getAsString() : "";
        String termino = show.has("ended") && !show.get("ended").isJsonNull() ? show.get("ended").getAsString() : "";
        String emissora = "Desconhecida";

        if (show.has("network") && !show.get("network").isJsonNull())

            emissora = show.getAsJsonObject("network").get("name").getAsString();

        return new Serie(id, nome, idioma, generos, nota, estado, estreia, termino, emissora);

    }
    
}