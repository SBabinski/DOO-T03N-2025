import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;


import java.util.List;

public class VisualCrossingApiService {
    
    private static final String API_KEY = "W7HCBTB9M3JGSUYT3J7TYSWWE";
    private static final String BASE_URL = "https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/";
    private final Gson gson = new Gson();
    private final HttpClient httpClient = HttpClient.newHttpClient();
    private static class WeatherApiResponse {
        private String address;
        @SerializedName("currentConditions")
        private CurrentConditions currentConditions;
        private List<DailyData> days; 

        public String getAddress() { return address; }
        public CurrentConditions getCurrentConditions() { return currentConditions; }
        public List<DailyData> getDays() { return days; }
    }
    private static class CurrentConditions {
        private double temp;
        private double humidity;
        private String conditions;
  
        private double windspeed;
        private double winddir; 

        public double getTemp() { return temp; }
        public double getHumidity() { return humidity; }
        public String getConditions() { return conditions; }
       
        public double getWindspeed() { return windspeed; }
        public double getWinddir() { return winddir; }
    }

    private static class DailyData {
        private double tempmax;
        private double tempmin;
        private double precip; 

        public double getTempmax() { return tempmax; }
        public double getTempmin() { return tempmin; }
        public double getPrecip() { return precip; }
    }


    /**
     * @param cidade 
     * @return 
     * @throws IOException 
     * @throws InterruptedException 
     */
    public Clima buscarClimaPorCidade(String cidade) throws IOException, InterruptedException {
        if (API_KEY.equals("SEU_API_KEY") || API_KEY.isEmpty()) {
            System.err.println("Erro: API_KEY não configurada. Obtenha sua chave em visualcrossing.com e substitua 'SEU_API_KEY' na classe VisualCrossingApiService.");
            return null;
        }

        String encodedCidade = URLEncoder.encode(cidade, StandardCharsets.UTF_8.toString());
        String url = BASE_URL + encodedCidade + "/today?unitGroup=metric&key=" + API_KEY + "&include=current,days";

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                String jsonResponse = response.body();
                WeatherApiResponse apiResponse = gson.fromJson(jsonResponse, WeatherApiResponse.class);

                if (apiResponse == null || apiResponse.getCurrentConditions() == null || apiResponse.getDays() == null || apiResponse.getDays().isEmpty()) {
                    System.out.println("Não foi possível obter dados detalhados para esta cidade.");
                    return null;
                }

                CurrentConditions current = apiResponse.getCurrentConditions();
                DailyData todayData = apiResponse.getDays().get(0);

                Clima clima = new Clima();
                clima.setAddress(apiResponse.getAddress());
                clima.setCurrentTemp(current.getTemp());
                clima.setMaxTemp(todayData.getTempmax());
                clima.setMinTemp(todayData.getTempmin());
                clima.setHumidity(current.getHumidity());
                clima.setConditions(current.getConditions());
                clima.setPrecipitation(todayData.getPrecip());
                clima.setWindSpeed(current.getWindspeed());
                clima.setWindDir(current.getWinddir());

                return clima;

            } else if (response.statusCode() == 400) {
                System.err.println("Cidade não encontrada ou requisição inválida. Verifique o nome da cidade. Status: " + response.statusCode());
                System.err.println("Resposta da API: " + response.body());
                return null;
            } else {
                System.err.println("Erro na requisição à API Visual Crossing: Status " + response.statusCode());
                System.err.println("Resposta da API: " + response.body());
                return null;
            }
        } catch (IOException | InterruptedException e) {
            System.err.println("Ocorreu um erro de comunicação com a API Visual Crossing: " + e.getMessage());
            throw e;
        } catch (com.google.gson.JsonSyntaxException e) {
            System.err.println("Erro ao processar a resposta JSON da API (JsonSyntaxException): " + e.getMessage());
            System.err.println("Verifique a estrutura JSON retornada pela API e seu mapeamento nas classes modelo.");
            return null; 
        }
    }
}