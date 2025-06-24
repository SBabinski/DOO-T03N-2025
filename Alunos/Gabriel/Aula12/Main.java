package weatherapp;

import weatherapp.model.WeatherData;
import weatherapp.service.WeatherService;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        WeatherService weatherService = new WeatherService();
        Scanner scanner = new Scanner(System.in);
        String city = "";

        while (true) {
            System.out.print("Digite a cidade: ");
            city = scanner.nextLine();

            try {
                WeatherData data = weatherService.getWeather(city);

                while (true) {
                    System.out.println("\n===== Menu =====");
                    System.out.println("1 - Exibir clima completo");
                    System.out.println("2 - Exibir temperatura (Atual, Máxima, Mínima)");
                    System.out.println("3 - Exibir umidade");
                    System.out.println("4 - Exibir velocidade e direção do vento");
                    System.out.println("5 - Exibir condição do tempo");
                    System.out.println("6 - Exibir precipitação");
                    System.out.println("7 - Digitar outra cidade");
                    System.out.println("0 - Sair");
                    System.out.print("Escolha: ");
                    int opcao = scanner.nextInt();
                    scanner.nextLine(); // Consumir o enter

                    switch (opcao) {
                        case 1:
                            System.out.println("Temperatura Atual: " + data.getCurrentTemp() + "°C");
                            System.out.println("Máxima: " + data.getMaxTemp() + "°C");
                            System.out.println("Mínima: " + data.getMinTemp() + "°C");
                            System.out.println("Umidade: " + data.getHumidity() + "%");
                            System.out.println("Condições: " + data.getConditions());
                            System.out.println("Precipitação: " + data.getPrecipitation() + " mm");
                            System.out.println("Vento: " + data.getWindSpeed() + " km/h, direção: " + data.getWindDirectionCardinal());
                            break;
                        case 2:
                            System.out.println("Temperatura Atual: " + data.getCurrentTemp() + "°C");
                            System.out.println("Máxima: " + data.getMaxTemp() + "°C");
                            System.out.println("Mínima: " + data.getMinTemp() + "°C");
                            break;
                        case 3:
                            System.out.println("Umidade: " + data.getHumidity() + "%");
                            break;
                        case 4:
                            System.out.println("Vento: " + data.getWindSpeed() + " km/h, direção: " + data.getWindDirectionCardinal());
                            break;
                        case 5:
                            System.out.println("Condição: " + data.getConditions());
                            break;
                        case 6:
                            System.out.println("Precipitação: " + data.getPrecipitation() + " mm");
                            break;
                        case 7:
                            System.out.println("Mudando de cidade...");
                            break;
                        case 0:
                            System.out.println("Saindo...");
                            scanner.close();
                            return;
                        default:
                            System.out.println("Opção inválida.");
                    }

                    if (opcao == 7) break;
                }
            } catch (IOException e) {
                System.out.println("Erro ao buscar os dados do clima: " + e.getMessage());
            }
        }
    }
}