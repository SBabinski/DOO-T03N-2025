import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import com.google.gson.*;

public class Main {

    static String API_KEY = "J9Q329J6QRCP9MR362U3NDJC5";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite o nome da cidade: ");
        String cidade = scanner.nextLine().trim();

        String endpoint = "https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/"
                + cidade.replace(" ", "%20")
                + "?unitGroup=metric&key=" + API_KEY + "&contentType=json";

        try {
            URL url = new URL(endpoint);
            HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
            conexao.setRequestMethod("GET");

            BufferedReader leitor = new BufferedReader(new InputStreamReader(conexao.getInputStream()));
            StringBuilder respostaJson = new StringBuilder();
            String linha;

            while ((linha = leitor.readLine()) != null) {
                respostaJson.append(linha);
            }
            leitor.close();

            JsonObject json = JsonParser.parseString(respostaJson.toString()).getAsJsonObject();
            JsonArray dias = json.getAsJsonArray("days");
            JsonObject hoje = dias.get(0).getAsJsonObject();

            double tempAtual = hoje.get("temp").getAsDouble();
            double tempMax = hoje.get("tempmax").getAsDouble();
            double tempMin = hoje.get("tempmin").getAsDouble();
            double umidade = hoje.get("humidity").getAsDouble();
            double ventoVel = hoje.get("windspeed").getAsDouble();
            String ventoDir = hoje.get("winddir").getAsString();
            String condicao = hoje.get("conditions").getAsString();
            double precipitacao = hoje.get("precip").getAsDouble();

            System.out.println("\n Clima para: " + cidade);
            System.out.println(" Temperatura atual: " + tempAtual + "°C");
            System.out.println(" Maxima: " + tempMax + "°C");
            System.out.println(" Minima: " + tempMin + "°C");
            System.out.println(" Umidade do ar: " + umidade + "%");
            System.out.println(" Condição do tempo: " + condicao);
            System.out.println("️ Precipitacao: " + precipitacao + " mm");
            System.out.println("️ Vento: " + ventoVel + " km/h, direcao: " + ventoDir + "°");

        } catch (Exception e) {
            System.out.println("Erro ao obter dados do clima: " + e.getMessage());
        }
    }
}