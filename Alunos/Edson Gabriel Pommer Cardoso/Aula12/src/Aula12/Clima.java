package Aula12;

import java.util.Scanner;

public class Clima {
    public static void main(String[] args) {
        System.out.println("Bem-vindo à Consulta de Clima");
        System.out.print("Digite o nome da cidade: ");

        try (Scanner scanner = new Scanner(System.in)) {
            String cidade = scanner.nextLine().trim();
            if (cidade.isEmpty()) {
                System.out.println("Cidade não pode ficar em branco.");
                return;
            }

            WeatherInfo info = WeatherApiClient.getWeather(cidade);
            System.out.println(info);
        } catch (Exception e) {
            System.out.println("Erro ao buscar o clima: " + e.getMessage());
        }
    }
}
