package Aula12;

import java.io.IOException;
import java.net.URI;
import java.net.http.*;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class WeatherApiClient {

    private static final String API_KEY = "D67VUDNKSKNLQA8Z8SF3HKBQQ";

    public static WeatherInfo getWeather(String city) throws IOException, InterruptedException {
        String encodedCity = URLEncoder.encode(city, StandardCharsets.UTF_8);
        String url = "https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/"
                   + encodedCity
                   + "/today?unitGroup=metric&key="
                   + API_KEY
                   + "&contentType=json";

        HttpRequest request = HttpRequest.newBuilder()
                                         .uri(URI.create(url))
                                         .GET()
                                         .build();
        HttpClient client = HttpClient.newHttpClient();
        HttpResponse<String> resp = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (resp.statusCode() != 200) {
            throw new IOException("HTTP " + resp.statusCode() + " – " + resp.body());
        }

        Gson gson = new Gson();
        JsonObject json  = gson.fromJson(resp.body(), JsonObject.class);
        JsonArray  days  = json.getAsJsonArray("days");
        JsonObject today = days.get(0).getAsJsonObject();

        return new WeatherInfo(
            today.get("temp").getAsDouble(),
            today.get("tempmax").getAsDouble(),
            today.get("tempmin").getAsDouble(),
            today.get("humidity").getAsDouble(),
            today.get("conditions").getAsString(),
            today.has("precip") ? today.get("precip").getAsDouble() : 0.0,
            today.get("windspeed").getAsDouble(),
            toCardinal(today.get("winddir").getAsDouble())
        );
    }

    private static String toCardinal(double deg) {
        String[] dirs = {
            "N","NNE","NE","ENE","E","ESE","SE","SSE",
            "S","SSW","SW","WSW","W","WNW","NW","NNW"
        };
        return dirs[(int)Math.round((deg % 360) / 22.5) % 16];
    }
}
