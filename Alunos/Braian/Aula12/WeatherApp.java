package Alunos.Braian.Aula12;

import java.util.Scanner;

import Alunos.Braian.Aula12.weather.WeatherClient;
import Alunos.Braian.Aula12.weather.WeatherData;

public class WeatherApp {
    public static void main(String[] args) {
        System.out.println("=== Consulta do Clima ===");

        try (Scanner scanner = new Scanner(System.in)) {
            WeatherClient client = new WeatherClient();

            boolean executando = true;

            while (executando) {
                System.out.println("\nMenu de opções:");
                System.out.println("1 - Consultar clima completo");
                System.out.println("2 - Consultar apenas temperatura");
                System.out.println("3 - Consultar apenas umidade");
                System.out.println("4 - Consultar condição do tempo");
                System.out.println("5 - Sair");

                System.out.print("Escolha uma opção: ");
                String opcao = scanner.nextLine().trim();

                switch (opcao) {
                    case "1", "2", "3", "4" -> {
                        System.out.print("Digite o nome da cidade: ");
                        String cidade = scanner.nextLine().trim();

                        if (cidade.isEmpty() || cidade.isBlank()) {
                            System.out.println("Nome da cidade não pode ser vazio.");
                            break;
                        }

                        try {
                            WeatherData data = client.getWeather(cidade);

                            switch (opcao) {
                                case "1" -> System.out.println(data);
                                case "2" -> {
                                    System.out.printf("Temperatura atual em %s: %.1f°C\n", cidade, data.getTemp());
                                    System.out.printf("Máxima: %.1f°C | 🔻 Mínima: %.1f°C\n", data.getTempMax(), data.getTempMin());
                                }
                                case "3" -> System.out.printf("Umidade do ar em %s: %.1f%%\n", cidade, data.getHumidity());
                                case "4" -> System.out.printf("Condição do tempo em %s: %s\n", cidade, data.getConditions());
                            }
                        } catch (IllegalArgumentException e) {
                            System.out.println("Entrada inválida: " + e.getMessage());
                        }
                    }
                    case "5" -> {
                        System.out.println("Encerrando o programa.");
                        executando = false;
                    }
                    default -> System.out.println("Opção inválida. Escolha um número de 1 a 5.");
                }
            }
        }
    }
}
