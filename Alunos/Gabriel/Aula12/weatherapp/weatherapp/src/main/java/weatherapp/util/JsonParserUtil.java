package Aula12.weatherapp.weatherapp.src.main.java.weatherapp.util;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class JsonParserUtil {
    public static WeatherData parseWeatherData(String jsonResponse) {
        JsonObject jsonObject = JsonParser.parseString(jsonResponse).getAsJsonObject();
        JsonObject todayData = jsonObject.getAsJsonArray("days").get(0).getAsJsonObject();
        JsonObject currentConditions = jsonObject.getAsJsonObject("currentConditions");

        double currentTemp = currentConditions.get("temp").getAsDouble();
        double maxTemp = todayData.get("tempmax").getAsDouble();
        double minTemp = todayData.get("tempmin").getAsDouble();
        int humidity = currentConditions.get("humidity").getAsInt();
        String conditions = currentConditions.get("conditions").getAsString();
        double precipitation = todayData.has("precip") ? todayData.get("precip").getAsDouble() : 0.0;
        double windSpeed = currentConditions.get("windspeed").getAsDouble();
        double windDir = currentConditions.get("winddir").getAsDouble();

        return new WeatherData(currentTemp, maxTemp, minTemp, humidity, conditions, precipitation, windSpeed, windDir);
    }
}
