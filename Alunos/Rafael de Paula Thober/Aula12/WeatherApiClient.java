import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class WeatherApiClient {

    private static final String API_KEY = "QUTFEMD4VY3WCZN6T9KJGEZET";

    public static WeatherInfo getWeather(String city) throws IOException, InterruptedException {
        String url = String.format(
                "https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/%s/today?unitGroup=metric&key=%s&contentType=json",
                city, API_KEY);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if(response.statusCode() != 200) {
            throw new IOException("Erro ao consultar API: " + response.body());
        }

        Gson gson = new Gson();
        JsonObject json = gson.fromJson(response.body(), JsonObject.class);
        JsonArray days = json.getAsJsonArray("days");
        JsonObject today = days.get(0).getAsJsonObject();

        double temp = today.get("temp").getAsDouble();
        double tempMax = today.get("tempmax").getAsDouble();
        double tempMin = today.get("tempmin").getAsDouble();
        double humidity = today.get("humidity").getAsDouble();
        String condition = today.get("conditions").getAsString();
        double precipitation = today.has("precip") ? today.get("precip").getAsDouble() : 0.0;
        double windSpeed = today.get("windspeed").getAsDouble();
        double windDir = today.get("winddir").getAsDouble();
        String windDirStr = degreeToCompass(windDir);

        return new WeatherInfo(temp, tempMax, tempMin, humidity, condition, precipitation, windSpeed, windDirStr);
    }

    // Converte direção do vento em graus para pontos cardeais
    private static String degreeToCompass(double degree) {
        String[] directions = {"N", "NNE", "NE", "ENE", "E", "ESE", "SE", "SSE",
                "S", "SSW", "SW", "WSW", "W", "WNW", "NW", "NNW"};
        return directions[(int)Math.round(((degree % 360) / 22.5)) % 16];
    }
}