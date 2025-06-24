package weather;

import java.util.Scanner;
import weather.WeatherService;
import weather.WeatherInfo;
import weather.ApiException;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        WeatherService service = new WeatherService("MINHA-SENHA"); // Substitua pela sua chave

        System.out.print("Digite o nome da cidade: ");
        String city = scanner.nextLine();

        try {
            WeatherInfo info = service.getWeatherInfo(city);
            System.out.println(info);
        } catch (ApiException e) {
            System.err.println("Erro ao obter dados do clima: " + e.getMessage());
        }

        scanner.close();
    }
}
