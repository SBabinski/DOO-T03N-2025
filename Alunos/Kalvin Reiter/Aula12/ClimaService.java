import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class ClimaService {

    private static final String API_KEY = "87GDWQS24N3RYPEB9CQE2HLJS";
    private static final String BASE_URL = "https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/";

    public ClimaInfo obterClima(String cidade) throws Exception {

        String urlStr = BASE_URL + cidade.replace(" ", "%20") + "?unitGroup=metric&lang=pt&key=" + API_KEY;
        URL url = new URL(urlStr);

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        StringBuilder resposta = new StringBuilder();
        String linha;

        while ((linha = reader.readLine()) != null) {

            resposta.append(linha);

        }

        reader.close();

        JsonObject root = JsonParser.parseString(resposta.toString()).getAsJsonObject();
        String cityName = root.get("address").getAsString();

        JsonObject dia = root.getAsJsonArray("days").get(0).getAsJsonObject();
        String condicao = dia.get("conditions").getAsString();
        double tempAtual = dia.get("temp").getAsDouble();
        double tempMax = dia.get("tempmax").getAsDouble();
        double tempMin = dia.get("tempmin").getAsDouble();
        double umidade = dia.get("humidity").getAsDouble();
        double precipitacao = dia.has("precip") ? dia.get("precip").getAsDouble() : 0.0;
        double ventoVelocidade = dia.get("windspeed").getAsDouble();
        String ventoDirecao = dia.get("winddir").getAsString();

        return new ClimaInfo(cityName, tempAtual, tempMax, tempMin, umidade, condicao, precipitacao, ventoVelocidade, ventoDirecao);

    }
    
}