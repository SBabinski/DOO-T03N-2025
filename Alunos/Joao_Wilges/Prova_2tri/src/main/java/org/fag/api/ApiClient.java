package org.fag.api;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ApiClient {
        private String jsonData;

        // Para implementar: APIResponse resposta = new APIResponse("https://api.tvmaze.com/search/shows?q={query}");

        public static String searchSeriesByName(String url) {
                try {
                        HttpClient client = HttpClient.newHttpClient();
                        HttpRequest request = HttpRequest.newBuilder()
                                .uri(URI.create(url))
                                .build();
                        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
                        return response.body();
                } catch (IOException e) {
                        // Recebe o erro e informa
                        throw new RuntimeException("Erro na resposta da API - Verifique sua internet");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
        }

        public ApiClient(String url){
                this.jsonData = searchSeriesByName(url);
        }

        public String getJsonData() {
                return jsonData;
        }

        @Override
        public String toString() {
                return jsonData;
        }
}