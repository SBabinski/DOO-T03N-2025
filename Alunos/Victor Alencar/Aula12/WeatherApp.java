
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Properties;
import java.util.Scanner;
import org.json.JSONArray;
import org.json.JSONObject;

public class WeatherApp {

    private static final String API_KEY = carregarApiKey();
    private static final String BASE_URL = "https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite o nome da cidade: ");
        String cidade = scanner.nextLine();
        scanner.close();

        try {
            JSONObject dados = buscarDadosClima(cidade);
            if (dados != null) {
                exibirDadosClima(dados);
            } else {
                System.out.println("Não foi possível obter os dados.");
            }
        } catch (IOException | InterruptedException e) {
            System.err.println("Erro ao consultar a API: " + e.getMessage());
        }
    }

    private static String carregarApiKey() {
        Properties prop = new Properties();
        try {
            prop.load(new FileInputStream("config.properties"));
            return prop.getProperty("api.key");
        } catch (IOException e) {
            throw new RuntimeException("Erro ao carregar a API key: " + e.getMessage());
        }
    }

    private static JSONObject buscarDadosClima(String cidade) throws IOException, InterruptedException {
        String url = BASE_URL + cidade + "?unitGroup=metric&key=" + API_KEY + "&include=current";

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(url))
            .GET()
            .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            return new JSONObject(response.body());
        } else {
            System.err.println("Erro HTTP: " + response.statusCode());
            return null;
        }
    }

    private static void exibirDadosClima(JSONObject dados) {
        JSONObject atual = dados.getJSONObject("currentConditions");
        JSONArray dias = dados.getJSONArray("days");
        JSONObject diaHoje = dias.getJSONObject(0);

        System.out.println("\n--- Clima para: " + dados.getString("resolvedAddress") + " ---");
        System.out.println("Temperatura atual: " + atual.getDouble("temp") + "°C");
        System.out.println("Temperatura máxima: " + diaHoje.getDouble("tempmax") + "°C");
        System.out.println("Temperatura mínima: " + diaHoje.getDouble("tempmin") + "°C");
        System.out.println("Umidade: " + atual.getInt("humidity") + "%");
        System.out.println("Condição do tempo: " + atual.getString("conditions"));
        System.out.println("Precipitação: " + atual.optDouble("precip", 0.0) + " mm");
        System.out.println("Vento: " + atual.getDouble("windspeed") + " km/h - Direção: " + atual.getDouble("winddir") + "°");
    }
}
