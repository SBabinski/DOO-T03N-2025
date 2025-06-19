import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

public class ServicoClima {
    private static final String API_KEY = "BRC2QSUZJGDF3ZPQ7CJ9K2XJC";
    private final HttpClient httpClient; 

    public ServicoClima() {
        this.httpClient = HttpClient.newHttpClient(); 
    }

    public String buscarClimaHoje(String cidade) throws Exception {
        String dataHoje = java.time.LocalDate.now().toString();
        // Use StandardCharsets.UTF_8 for URLEncoder
        String encodedCidade = URLEncoder.encode(cidade, StandardCharsets.UTF_8);
        String url = "https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/"
                + encodedCidade + "/" + dataHoje + "?unitGroup=metric&lang=pt&include=days,current"
                + "&key=" + API_KEY + "&contentType=json";

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET() 
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200) {
            throw new RuntimeException("Erro: HTTP " + response.statusCode() + " - " + response.body());
        } else if (response.body().isEmpty()) {
            throw new RuntimeException("Erro: Resposta vazia da API");
        }

        return response.body();
    }
}