import com.google.gson.Gson;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite o nome da cidade (ex: Cascavel,BR): ");
        String city = scanner.nextLine();

        WeatherService weatherService = new WeatherService();
        Gson gson = new Gson();

        try {
            // 1. Chama o serviço para buscar os dados da API em formato JSON
            String jsonResponse = weatherService.getWeatherData(city);

            // Verifica se a API retornou um erro (ex: cidade não encontrada)
            if (jsonResponse.contains("Invalid location")) {
                System.err.println("\n--- ERRO ---");
                System.err.println("Cidade não encontrada. Verifique o nome e tente novamente.");
                System.err.println("------------");
                return; // Encerra o programa
            }

            // 2. Usa o Gson para converter o texto JSON em nossos objetos Java
            WeatherData weatherData = gson.fromJson(jsonResponse, WeatherData.class);

            // 3. Pega os objetos com as informações que precisamos
            WeatherData.CurrentConditions current = weatherData.currentConditions;
            WeatherData.Day today = weatherData.days[0];

            // 4. Imprime os resultados formatados no console
            System.out.println("\n--- Clima/Tempo em " + city + " ---");
            System.out.printf("Temperatura Atual: %.1f°C\n", current.temp);
            System.out.printf("Condição: %s\n", current.conditions);
            System.out.printf("Temp. Máxima Hoje: %.1f°C\n", today.tempmax);
            System.out.printf("Temp. Mínima Hoje: %.1f°C\n", today.tempmin);
            System.out.printf("Umidade: %.1f%%\n", current.humidity);
            System.out.printf("Precipitação (chuva): %.1f mm\n", current.precip);
            System.out.printf("Vento: %.1f km/h, Direção: %.0f°\n", current.windspeed, current.winddir);
            System.out.println("------------------------------------");

        } catch (UnknownHostException e) {
            // Este erro é o mais provável de acontecer quando não há internet
            System.err.println("\n--- ERRO DE CONEXÃO ---");
            System.err.println("Não foi possível conectar ao servidor do clima.");
            System.err.println("Por favor, verifique sua conexão com a internet.");
            System.err.println("-------------------------");
        } catch (IOException | InterruptedException e) {
            // Captura outros erros de comunicação ou interrupção
            System.err.println("\n--- ERRO ---");
            System.err.println("Ocorreu um erro de comunicação ao buscar os dados, verefique o nome da cidade e tente novamente ou verefica se você está com internet ");
            // e.printStackTrace(); // Descomente para ver detalhes do erro
            System.err.println("------------");
        } finally {
            scanner.close();
        }
    }
}