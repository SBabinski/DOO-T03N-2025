import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

public class WeatherService {


    private static final String API_KEY = "TAXMML8KFU7TWE7UXEMJKQFJC";
    private static final String API_BASE_URL = "https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/";

    public String getWeatherData(String city) throws IOException, InterruptedException {

        String encodedCity = URLEncoder.encode(city, StandardCharsets.UTF_8);


        String apiUrl = String.format("%s%s/today?unitGroup=metric&include=current&key=%s&contentType=json",
                API_BASE_URL, encodedCity, API_KEY);


        HttpClient client = HttpClient.newHttpClient();


        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(apiUrl))
                .build();


        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());


        return response.body();
    }
}