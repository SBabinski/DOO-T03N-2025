package util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.Show;
import model.ShowSearchSmallWrapper;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

public final class TVApiHandler {

    private static final HttpClient client = GlobalVarSingleton.getInstance().tvApiHTTPclient;
    private static final ObjectMapper jsonWrapper = GlobalVarSingleton.getInstance().globalJsonWrapper;
    private static final String apiRootURL = "https://api.tvmaze.com";

    public static List<Show> searchShowsByName(String name) {
        String searchPath = apiRootURL + "/search/shows?q=";
        String nameEncoded = URLEncoder.encode(name, StandardCharsets.UTF_8);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(searchPath + nameEncoded))
                .GET()
                .build();

        try {

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 429) {
                System.out.println("Rate limit exceeded, muitos requests ao mesmo tempo, tente novamente mais tarde");
                return null;
            }

            if (response.statusCode() != 200) {
                System.out.println("Resposta da API diferente de 200 OK, detalhes:\n " + response);
                return null;
            }

            List<ShowSearchSmallWrapper> wrappedResult = jsonWrapper.readValue(response.body(), new TypeReference<List<ShowSearchSmallWrapper>>() {});

            return wrappedResult.stream().map(ShowSearchSmallWrapper::getShow).collect(Collectors.toList());
        } catch (Exception e) {
            System.out.println("Exception no request da API: " + e + " " + e.getMessage());
            return null;
        }
    }
}
