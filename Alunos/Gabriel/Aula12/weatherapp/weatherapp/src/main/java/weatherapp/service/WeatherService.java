package Aula12.weatherapp.weatherapp.src.main.java.weatherapp.service;

import java.io.IOException;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class WeatherService {
    private static final String API_KEY = "XLJNQ64Y933YRUYZVKWW298W8";
    private static final String BASE_URL = "https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/";

    public WeatherData getWeather(String city) throws IOException {
        OkHttpClient client = new OkHttpClient();
        String url = BASE_URL + city + "/today?unitGroup=metric&key=" + API_KEY + "&include=current";

        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Erro na requisição: " + response);
            }

            String jsonResponse = response.body().string();
            return JsonParserUtil.parseWeatherData(jsonResponse);
        }
    }
}
