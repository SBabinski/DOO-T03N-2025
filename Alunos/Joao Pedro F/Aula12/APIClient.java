
package com.visualcrossing;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class APIClient {

    private static final String BASE_URL = "https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/";

    public String getWeatherData(String location, String apiKey) throws IOException {
        String encodedLocation = URLEncoder.encode(location, StandardCharsets.UTF_8.toString());
        String url = BASE_URL + encodedLocation + "?unitGroup=metric&include=current,days&key=" + apiKey;

        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet request = new HttpGet(url);
            try (CloseableHttpResponse response = httpClient.execute(request)) {
                String jsonResponse = EntityUtils.toString(response.getEntity());
                return jsonResponse;
            }
        }
    }

    public WeatherData parseWeatherData(String jsonResponse) {
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(jsonResponse, JsonObject.class);

        WeatherData weatherData = new WeatherData();

        // Current Conditions
        JsonObject currentConditions = jsonObject.getAsJsonObject("currentConditions");
        if (currentConditions != null) {
            weatherData.setCurrentTemp(currentConditions.get("temp").getAsDouble());
            weatherData.setHumidity(currentConditions.get("humidity").getAsDouble());
            weatherData.setConditions(currentConditions.get("conditions").getAsString());
            weatherData.setPrecipitation(currentConditions.get("precip").getAsDouble());
            weatherData.setWindSpeed(currentConditions.get("windspeed").getAsDouble());
            weatherData.setWindDirection(currentConditions.get("winddir").getAsDouble());
        }

        // Daily Data (for max and min temp)
        JsonObject dayData = jsonObject.getAsJsonArray("days").get(0).getAsJsonObject();
        if (dayData != null) {
            weatherData.setMaxTemp(dayData.get("tempmax").getAsDouble());
            weatherData.setMinTemp(dayData.get("tempmin").getAsDouble());
        }

        return weatherData;
    }
}

class WeatherData {
    private double currentTemp;
    private double maxTemp;
    private double minTemp;
    private double humidity;
    private String conditions;
    private double precipitation;
    private double windSpeed;
    private double windDirection;

    // Getters and Setters
    public double getCurrentTemp() {
        return currentTemp;
    }

    public void setCurrentTemp(double currentTemp) {
        this.currentTemp = currentTemp;
    }

    public double getMaxTemp() {
        return maxTemp;
    }

    public void setMaxTemp(double maxTemp) {
        this.maxTemp = maxTemp;
    }

    public double getMinTemp() {
        return minTemp;
    }

    public void setMinTemp(double minTemp) {
        this.minTemp = minTemp;
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public String getConditions() {
        return conditions;
    }

    public void setConditions(String conditions) {
        this.conditions = conditions;
    }

    public double getPrecipitation() {
        return precipitation;
    }

    public void setPrecipitation(double precipitation) {
        this.precipitation = precipitation;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }

    public double getWindDirection() {
        return windDirection;
    }

    public void setWindDirection(double windDirection) {
        this.windDirection = windDirection;
    }
}


