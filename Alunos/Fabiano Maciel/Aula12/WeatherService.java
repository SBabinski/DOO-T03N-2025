package Aula12;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.*;

public class WeatherService {
    private static final String BASE_URL = "https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/";
    private static final String API_KEY = "JU3UWDHKD9VC5TJJY27GABJG6";

    public WeatherInfo getWeather(String city) throws Exception {
        String urlString = BASE_URL + city.replace(" ", "%20")
                + "?unitGroup=metric&include=current&key=" + API_KEY + "&contentType=json";

        URL url = new URL(urlString);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");

        if (con.getResponseCode() != 200) {
            throw new RuntimeException("Erro ao buscar informações do clima: código HTTP " + con.getResponseCode());
        }

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        StringBuilder responseJson = new StringBuilder();
        String line;
        while ((line = in.readLine()) != null) {
            responseJson.append(line);
        }
        in.close();

        JSONObject json = new JSONObject(responseJson.toString());

        JSONObject current = json.getJSONObject("currentConditions");
        double temp = current.optDouble("temp", Double.NaN);
        double windSpeed = current.optDouble("windspeed", Double.NaN);
        String windDir = current.optString("winddir", "");
        int humidity = current.optInt("humidity", -1);
        String conditions = current.optString("conditions", "");
        double precip = current.optDouble("precip", 0.0);

        JSONArray days = json.getJSONArray("days");
        if (days.length() == 0) {
            throw new RuntimeException("Nenhuma informação diária encontrada.");
        }
        JSONObject today = days.getJSONObject(0);
        double tempMax = today.optDouble("tempmax", Double.NaN);
        double tempMin = today.optDouble("tempmin", Double.NaN);

        String windCardinal = degreeToCardinal(windDir);

        return new WeatherInfo(
            temp,
            tempMax,
            tempMin,
            humidity,
            conditions,
            precip,
            windSpeed,
            windCardinal
        );
    }

    private String degreeToCardinal(String windDir) {
        try {
            double deg = Double.parseDouble(windDir);
            String[] compass = {"N", "NNE", "NE", "ENE", "E", "ESE", "SE", "SSE",
                                "S", "SSW", "SW", "WSW", "W", "WNW", "NW", "NNW"};
            int idx = (int) ((deg / 22.5) + 0.5) % 16;
            return compass[idx];
        } catch (Exception e) {
            return windDir; 
        }
    }
}