import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Consulta de Clima Visual Crossing ===");
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite o nome da cidade: ");
        String cidade = scanner.nextLine();

        try {
            WeatherInfo info = WeatherApiClient.getWeather(cidade);
            System.out.println(info);
        } catch (Exception e) {
            System.out.println("Erro ao obter informações do clima: " + e.getMessage());
        }

        scanner.close();
    }
}