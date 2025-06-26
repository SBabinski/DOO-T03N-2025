import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class Main {
    private static final String API_KEY = "WFGPZ9EBXZ6DJBKY3YAW4GBHJ";
    private static final String BASE_URL = "https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/";

    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            while (true) {
                System.out.println("\n=== MENU PRINCIPAL ===");
                System.out.println("1. Consultar clima");
                System.out.println("2. Sair");
                System.out.print("Escolha: ");

                String option = reader.readLine();

                if (option.equals("1")) {
                    System.out.print("\nDigite a cidade: ");
                    String city = reader.readLine();
                    String data = getWeather(city);
                    showWeather(data);
                } else if (option.equals("2")) {
                    System.out.println("Saindo...");
                    break;
                } else {
                    System.out.println("Opção inválida!");
                }
            }
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private static String getWeather(String location) throws IOException {
        String encoded = URLEncoder.encode(location, StandardCharsets.UTF_8);
        String url = BASE_URL + encoded + "/today?unitGroup=metric&key=" + API_KEY + "&include=days,current";

        HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
        conn.setRequestMethod("GET");

        if (conn.getResponseCode() != 200) {
            throw new IOException("Erro HTTP: " + conn.getResponseCode());
        }

        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String line;
        StringBuilder response = new StringBuilder();

        while ((line = in.readLine()) != null) {
            response.append(line);
        }
        in.close();

        return response.toString();
    }

    private static void showWeather(String json) {
        try {
            String city = extractValue(json, "\"resolvedAddress\":\"", "\"");
            String conditions = extractValue(json, "\"conditions\":\"", "\"");
            String temp = extractValue(json, "\"temp\":", ",");
            String tempMax = extractValue(json, "\"tempmax\":", ",");
            String tempMin = extractValue(json, "\"tempmin\":", ",");
            String humidity = extractValue(json, "\"humidity\":", ",");
            String precip = extractValue(json, "\"precip\":", ",");
            String windSpeed = extractValue(json, "\"windspeed\":", ",");
            String windDir = extractValue(json, "\"winddir\":", ",");

            System.out.println("\n--- CLIMA EM " + city.toUpperCase() + " ---");
            System.out.println("Condição: " + conditions);
            System.out.println("Temperatura atual: " + temp + "°C");
            System.out.println("Máxima do dia: " + tempMax + "°C");
            System.out.println("Mínima do dia: " + tempMin + "°C");
            System.out.println("Umidade do ar: " + humidity + "%");
            System.out.println("Precipitação: " + precip + " mm");
            System.out.println("Vento: " + windSpeed + " km/h");
            System.out.println("Direção do vento: " + windDir + "°");

        } catch (Exception e) {
            System.out.println("Erro ao ler dados: " + e.getMessage());
        }
    }

    private static String extractValue(String json, String keyStart, String keyEnd) {
        try {
            return json.split(keyStart)[1].split(keyEnd)[0];
        } catch (Exception e) {
            return "Não disponível";
        }
    }
}
