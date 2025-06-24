import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Scanner;
import java.util.stream.Collectors;

public class GerenciadorDados {
    private static final String API_URL = "https://api.tvmaze.com/search/shows?q=";
    private static final Path USUARIO_PATH = Paths.get("usuario_series.json");
    private static final Path SERIES_DB_PATH = Paths.get("series_db.json");

    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private final HttpClient client = HttpClient.newHttpClient();
    private Map<Integer, Serie> seriesDatabase = new HashMap<>();

    public GerenciadorDados() {
        carregarSeriesDoDB();
    }
    
    public List<Serie> buscarSeriesPorNome(String nome) throws IOException, InterruptedException {
        String query = URLEncoder.encode(nome, StandardCharsets.UTF_8);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(API_URL + query))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        
        Type tipoListaResultado = new TypeToken<List<SearchResult>>() {}.getType();
        List<SearchResult> searchResults = gson.fromJson(response.body(), tipoListaResultado);

        return searchResults.stream().map(r -> r.show).collect(Collectors.toList());
    }

    public Usuario carregarUsuario(Scanner scanner) {
        try {
            if (Files.exists(USUARIO_PATH)) {
                FileReader reader = new FileReader(USUARIO_PATH.toFile());
                return gson.fromJson(reader, Usuario.class);
            }
        } catch (IOException e) {
            System.out.println("Não foi possível carregar o perfil de usuário existente. Um novo será criado.");
        }
        
        System.out.print("Bem-vindo! Parece ser seu primeiro acesso. Digite seu nome ou apelido: ");
        String nome = scanner.nextLine();
        return new Usuario(nome);
    }

    public void salvarUsuario(Usuario usuario) {
        try (FileWriter writer = new FileWriter(USUARIO_PATH.toFile())) {
            gson.toJson(usuario, writer);
        } catch (IOException e) {
            System.err.println("Erro ao salvar o perfil do usuário!");
        }
    }
    
    private void carregarSeriesDoDB() {
        try {
            if (Files.exists(SERIES_DB_PATH)) {
                FileReader reader = new FileReader(SERIES_DB_PATH.toFile());
                Type tipoMap = new TypeToken<HashMap<Integer, Serie>>() {}.getType();
                seriesDatabase = gson.fromJson(reader, tipoMap);
                if (seriesDatabase == null) {
                    seriesDatabase = new HashMap<>();
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao carregar o banco de dados de séries local.");
            seriesDatabase = new HashMap<>();
        }
    }

    private void salvarSeriesNoDB() {
        try (FileWriter writer = new FileWriter(SERIES_DB_PATH.toFile())) {
            gson.toJson(seriesDatabase, writer);
        } catch (IOException e) {
            System.err.println("Erro ao salvar séries no banco de dados local.");
        }
    }
    
    public void adicionarSerieAoDB(Serie serie) {
        if (!seriesDatabase.containsKey(serie.getId())) {
            seriesDatabase.put(serie.getId(), serie);
            salvarSeriesNoDB();
        }
    }
    
    public Serie getSeriePorId(int id) {
        return seriesDatabase.get(id);
    }
}