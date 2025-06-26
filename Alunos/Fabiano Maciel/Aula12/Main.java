package Aula12;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Consulta de Clima - Visual Crossing API ===");
        System.out.print("Informe a cidade: ");
        String cidade = scanner.nextLine();

        WeatherService service = new WeatherService();

        try {
            WeatherInfo info = service.getWeather(cidade);
            System.out.println();
            System.out.println(info);
        } catch (Exception e) {
            System.err.println("Erro ao consultar o clima: " + e.getMessage());
        }

        scanner.close();
    }
}