import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;   
import java.net.http.HttpResponse;

// Classe que faz uma requisição HTTP para a API do TVmaze

public class HttpClienteMave {
      public static void main(String[] args) throws Exception {
        
        HttpClient client = HttpClient.newHttpClient();
        URI url = new URI("https://api.tvmaze.com/search/shows?q=breaking%20bad");
        
        HttpRequest request = HttpRequest.newBuilder(url)
                .GET()
                .build();
        
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() == 200) {
            String body = response.body();
            System.out.println("Resposta da API: " + body);
        } else {
            System.out.println("Erro na requisição: " + response.statusCode());
        }
    }
}