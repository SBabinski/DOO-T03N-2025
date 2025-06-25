import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Main {

    public static void main(String[] args) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/cascavel?unitGroup=metric&include=current&key=&contentType=json"))
                .method("GET", HttpRequest.BodyPublishers.noBody()).build();

        ObjectMapper objectMapper = new ObjectMapper();
        ApiRequestModel responseWrappedObject = null;

        try (HttpClient client = HttpClient.newHttpClient()) {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            responseWrappedObject = objectMapper.readValue(response.body(), ApiRequestModel.class);
        } catch (Exception e) {
            System.out.println(e);
            System.exit(1);
        } finally {
            if (responseWrappedObject == null) {
                System.exit(1);
            }
        }

        DayRequestModel currentDay = responseWrappedObject.getDays().getFirst();

        System.out.println("📍 Clima em: " + responseWrappedObject.getResolvedAddress());
        System.out.println("🌡️ Atual: "   + currentDay.getTemp()    + "°C");
        System.out.println("🔼 Máx: "       + currentDay.getTempmax()+ "°C");
        System.out.println("🔽 Mín: "       + currentDay.getTempmin()+ "°C");
        System.out.println("💧 Umidade: "   + currentDay.getHumidity()+ "%");
        System.out.println("☔ Precip.: "    + currentDay.getPrecip()  + " mm");
        System.out.println("☁️ Cond.: "     + currentDay.getConditions());
        System.out.println("🌬️ Vento: "     + currentDay.getWindspeed()+ " km/h");
        System.out.println("↪ Direção: "    + currentDay.getWinddir() + "°");



    }
}
