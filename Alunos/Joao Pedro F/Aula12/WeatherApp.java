
package com.visualcrossing;

import java.io.Console;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class WeatherApp {

    public static void main(String[] args) {
        Console console = System.console();
        if (console == null) {
            System.err.println("No console available. Please run this application from a command line.");
            System.exit(1);
        }

        String apiKey = "KE7NP6MFJQXPWPJ2HPRL2W6B7"; // Sua chave da API Visual Crossing

        APIClient apiClient = new APIClient();
        
        String locationInput;
        while (true) {
            console.printf("\nDigite o nome da cidade (ou \'sair\' para encerrar): ");
            locationInput = console.readLine();

            if (locationInput == null || locationInput.trim().equalsIgnoreCase("sair")) {
                console.printf("Encerrando o aplicativo.\n");
                System.exit(0);
            }

            if (locationInput.trim().isEmpty()) {
                console.printf("Por favor, digite um nome de cidade válido.\n");
            } else {
                break;
            }
        }

        String location = locationInput.trim();

        try {
            String jsonResponse = apiClient.getWeatherData(location, apiKey);
            WeatherData weatherData = apiClient.parseWeatherData(jsonResponse);

            System.out.println("\n--- Condições Atuais em " + location + " ---");
            System.out.println("Temperatura: " + weatherData.getCurrentTemp() + "°C");
            System.out.println("Máxima para o dia: " + weatherData.getMaxTemp() + "°C");
            System.out.println("Mínima para o dia: " + weatherData.getMinTemp() + "°C");
            System.out.println("Umidade: " + weatherData.getHumidity() + "%");
            System.out.println("Condição do tempo: " + weatherData.getConditions());
            System.out.println("Precipitação: " + weatherData.getPrecipitation() + " mm");
            System.out.println("Velocidade do vento: " + weatherData.getWindSpeed() + " km/h");
            System.out.println("Direção do vento: " + weatherData.getWindDirection() + "°");

        } catch (Exception e) {
            System.err.println("Erro ao buscar dados do clima: " + e.getMessage());
            e.printStackTrace();
        }
    }
}


