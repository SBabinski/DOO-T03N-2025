package weather;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class WeatherService {
    private static final String BASE_URL = "https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/";
    private final String apiKey;
    private final ObjectMapper mapper = new ObjectMapper();

    public WeatherService(String apiKey) {
        this.apiKey = apiKey;
    }

    public WeatherInfo getWeatherInfo(String city) throws ApiException {
        try {
            String urlString = BASE_URL + city.replace(" ", "%20") + "/today?unitGroup=metric&key=" + apiKey + "&contentType=json";
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            if (conn.getResponseCode() != 200) {
                throw new ApiException("Código de erro HTTP: " + conn.getResponseCode());
            }

            Scanner scanner = new Scanner(conn.getInputStream());
            StringBuilder json = new StringBuilder();
            while (scanner.hasNext()) {
                json.append(scanner.nextLine());
            }
            scanner.close();

            JsonNode root = mapper.readTree(json.toString());
            JsonNode today = root.get("days").get(0);

            return new WeatherInfo(
                    root.get("resolvedAddress").asText(),
                    today.get("temp").asDouble(),
                    today.get("tempmax").asDouble(),
                    today.get("tempmin").asDouble(),
                    today.get("humidity").asInt(),
                    today.get("conditions").asText(),
                    today.get("precip").asDouble(),
                    today.get("windspeed").asDouble(),
                    today.get("winddir").asDouble()
            );

        } catch (IOException e) {
            throw new ApiException("Erro ao conectar com a API: " + e.getMessage());
        }
    }
}
