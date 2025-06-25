package fag.api;


import com.fasterxml.jackson.databind.ObjectMapper;
import fag.model.Clima;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

public class APIResponse {

    // Método para buscar os dados do clima e retornar um objeto Clima
    public static Clima getWeatherData(String cidade, String apiKey) throws IOException, InterruptedException, IOException {

        String cidadeCodificada = URLEncoder.encode(cidade, StandardCharsets.UTF_8);

        String url = String.format(
                "https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/%s/today?unitGroup=metric&lang=pt&include=hours&key=%s&contentType=json",
                cidadeCodificada, apiKey
        );


        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(URI.create(url)).GET().build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        String json = response.body();
        if (response.statusCode() != 200) {
            System.err.println("Erro ao consultar a API. Código: " + response.statusCode() + " | Resposta: " + json);
            throw new IOException("Erro na resposta da API.");
        }

        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(json, Clima.class);
    }
}
