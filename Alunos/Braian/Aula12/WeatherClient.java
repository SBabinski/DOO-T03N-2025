package Alunos.Braian.Aula12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.util.regex.Pattern;

import org.json.JSONObject;

import Alunos.Braian.Aula12.weather.WeatherData;

public class WeatherClient {

    private static final String API_KEY = "NNSK473GK6H78YL4R7BKBTN5A";

    public WeatherData getWeather(String city) throws IOException {
        if (city == null || city.trim().isEmpty()) {
            throw new IllegalArgumentException("O nome da cidade não pode ser vazio.");
        }

        if (!isValidCityFormat(city)) {
            throw new IllegalArgumentException("O nome da cidade contém caracteres inválidos.");
        }

        String urlString = "https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/"
                + URLEncoder.encode(city, "UTF-8")
                + "?unitGroup=metric&key=" + API_KEY + "&contentType=json";

        HttpURLConnection connection = null;
        try {
            URL url = new URL(urlString);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            int responseCode = connection.getResponseCode();

            if (responseCode != 200) {
                handleErrorResponse(responseCode, connection);
            }

            StringBuilder response;
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
            }

            JSONObject json = new JSONObject(response.toString());
            JSONObject day = json.getJSONArray("days").getJSONObject(0);

            double temp = day.getDouble("temp");
            double tempMax = day.getDouble("tempmax");
            double tempMin = day.getDouble("tempmin");
            double humidity = day.getDouble("humidity");
            double precip = day.optDouble("precip", 0.0);
            double windSpeed = day.optDouble("windspeed", 0.0);
            String windDir = day.has("winddir") ? day.get("winddir") + "°" : "N/A";
            String conditions = day.optString("conditions", "Não informado");

            return new WeatherData(city, temp, tempMax, tempMin, humidity, precip, windSpeed, windDir, conditions);

        } catch (UnknownHostException e) {
            throw new IOException("Falha de conexão. Verifique sua internet.");
        } catch (SocketTimeoutException e) {
            throw new IOException("Tempo de conexão excedido. Verifique sua internet ou tente novamente.");
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }

    private void handleErrorResponse(int responseCode, HttpURLConnection connection) throws IOException {
        StringBuilder errorResponse;
        try (BufferedReader errorReader = new BufferedReader(new InputStreamReader(connection.getErrorStream()))) {
            errorResponse = new StringBuilder();
            String errorLine;
            while ((errorLine = errorReader.readLine()) != null) {
                errorResponse.append(errorLine);
            }
        }

        switch (responseCode) {
            case 400 -> throw new IOException("Cidade inválida ou não encontrada.");
            case 401 -> throw new IOException("Chave da API inválida ou não autorizada.");
            case 403 -> throw new IOException("Acesso proibido. Verifique sua conta ou chave.");
            case 429 -> throw new IOException("Limite de requisições atingido. Aguarde ou verifique seu plano.");
            case 500, 502, 503 -> throw new IOException("Erro interno no servidor da API. Tente novamente mais tarde.");
            default -> throw new IOException("Erro inesperado. Código HTTP: " + responseCode + ". Detalhes: " + errorResponse);
        }
    }

    private boolean isValidCityFormat(String city) {
        String regex = "^[\\p{L}\\s]+$";
        return Pattern.matches(regex, city.trim());
    }
}
