import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class WeatherApp {
    private static final String API_KEY = "WFGPZ9EBXZ6DJBKY3YAW4GBHJ";
    private static final String BASE_URL = "https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/";

    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            while (true) {
                System.out.println("\n=== MENU PRINCIPAL ===");
                System.out.println("1. Consultar clima de uma cidade");
                System.out.println("2. Sair");
                System.out.print("Escolha uma opção: ");

                String option = reader.readLine();

                switch (option) {
                    case "1":
                        consultarClima(reader);
                        break;
                    case "2":
                        System.out.println("Saindo do sistema...");
                        return;
                    default:
                        System.out.println("Opção inválida! Tente novamente.");
                }
            }
        } catch (IOException e) {
            System.err.println("Erro no sistema: " + e.getMessage());
        }
    }

    private static void consultarClima(BufferedReader reader) throws IOException {
        System.out.println("\n=== CONSULTA DE CLIMA ===");
        System.out.print("Digite o nome da cidade: ");
        String location = reader.readLine();

        try {
            String weatherData = getWeatherData(location);
            displayWeatherInfo(weatherData);
        } catch (IOException e) {
            System.err.println("Erro na consulta: " + e.getMessage());
            System.out.println("Verifique:\n1. O nome da cidade\n2. Sua conexão com a internet");
        }
    }

    private static String getWeatherData(String location) throws IOException {
        String encodedLocation = URLEncoder.encode(location, StandardCharsets.UTF_8);
        String requestUrl = BASE_URL + encodedLocation + "/today?unitGroup=metric&include=current&key=" + API_KEY + "&contentType=json";

        System.out.println("Consultando API..."); // Feedback para o usuário

        URL url = new URL(requestUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setConnectTimeout(5000); // Timeout de 5 segundos

        int responseCode = connection.getResponseCode();
        if (responseCode != 200) {
            throw new IOException("Erro na API: Código HTTP " + responseCode);
        }

        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder response = new StringBuilder();
        String inputLine;

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        return response.toString();
    }

    private static void displayWeatherInfo(String jsonData) {
        try {

            if (jsonData == null || jsonData.isEmpty() || jsonData.contains("\"errorCode\"")) {
                throw new IOException("Dados meteorológicos não disponíveis para esta localização");
            }

            String city = extractValue(jsonData, "\"resolvedAddress\":\"", "\"");
            String currentConditions = extractBetween(jsonData, "\"currentConditions\":{", "}");

            if (currentConditions == null || currentConditions.isEmpty()) {
                throw new IOException("Dados atuais não disponíveis");
            }

            double temp = safeParseDouble(extractValue(currentConditions, "\"temp\":", ","));
            double tempMax = safeParseDouble(extractValue(jsonData, "\"tempmax\":", ","));
            double tempMin = safeParseDouble(extractValue(jsonData, "\"tempmin\":", ","));
            double humidity = safeParseDouble(extractValue(currentConditions, "\"humidity\":", ","));
            String conditions = extractValue(currentConditions, "\"conditions\":\"", "\"");
            double precip = safeParseDouble(extractValue(currentConditions, "\"precip\":", ","));
            double windSpeed = safeParseDouble(extractValue(currentConditions, "\"windspeed\":", ","));
            String windDir = extractValue(currentConditions, "\"winddir\":", ",");

            System.out.println("\n--- RESULTADO PARA " + city.toUpperCase() + " ---");
            System.out.printf("• Temperatura atual: %.1f°C\n", temp);
            System.out.printf("• Máxima/Mínima: %.1f°C / %.1f°C\n", tempMax, tempMin);
            System.out.printf("• Umidade: %.0f%%\n", humidity);
            System.out.println("• Condições: " + (conditions == null ? "Não disponível" : conditions));

            if (precip > 0) {
                System.out.printf("• Precipitação: %.1f mm\n", precip);
            }

            System.out.printf("• Vento: %.1f km/h, direção %s\n",
                    windSpeed,
                    windDir == null || windDir.equals("null") ? "Não disponível" : getWindDirection(windDir));

        } catch (Exception e) {
            System.err.println("\nErro ao processar dados: " + e.getMessage());
        }
    }

    private static String extractBetween(String str, String start, String end) {
        try {
            int startIndex = str.indexOf(start) + start.length();
            int endIndex = str.indexOf(end, startIndex);
            return str.substring(startIndex, endIndex);
        } catch (Exception e) {
            return null;
        }
    }

    private static String extractValue(String str, String key, String delimiter) {
        try {
            int keyIndex = str.indexOf(key);
            if (keyIndex == -1) return null;
            int valueStart = keyIndex + key.length();
            int valueEnd = str.indexOf(delimiter, valueStart);
            return str.substring(valueStart, valueEnd).replace("\"", "");
        } catch (Exception e) {
            return null;
        }
    }

    private static double safeParseDouble(String value) {
        if (value == null || value.equals("null")) return 0;
        try {
            return Double.parseDouble(value);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    private static String getWindDirection(String degrees) {
        try {
            double deg = Double.parseDouble(degrees);
            String[] directions = {"Norte", "Nordeste", "Leste", "Sudeste", "Sul", "Sudoeste", "Oeste", "Noroeste"};
            int index = (int) Math.round((deg % 360) / 45) % 8;
            return directions[index];
        } catch (Exception e) {
            return "Não disponível";
        }
    }
}