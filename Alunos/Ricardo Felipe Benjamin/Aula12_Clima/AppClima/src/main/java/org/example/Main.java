package org.example;

import java.util.Scanner;
import com.google.gson.Gson;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite o nome da cidade:");
        String cidade = scanner.nextLine();

        String chave = ChaveAPI.carregarChave("api.key");
        if (chave == null) {
            System.out.println("Não foi possível carregar a chave da API.");
            return;
        }

        String resposta = ServicoClima.buscarDadosClima(cidade, chave);
        if (resposta == null) {
            System.out.println("Erro ao buscar dados. Verifique a cidade ou conexão.");
            return;
        }

        Gson gson = new Gson();
        ClimaResposta clima = gson.fromJson(resposta, ClimaResposta.class);
        if (clima.days == null || clima.days.isEmpty()) {
            System.out.println("enhum dado de clima encontrado para a cidade: " + cidade);
            return;
        }

        DiaClima dia = clima.days.get(0);

        System.out.println("\nCidade: " + clima.resolvedAddress);
        System.out.println("Data: " + dia.datetime);
        System.out.println("🌡Temp. Atual: " + dia.temp + "°C");
        System.out.println("Máxima: " + dia.tempmax + "°C");
        System.out.println("Mínima: " + dia.tempmin + "°C");
        System.out.println("Umidade: " + dia.humidity + "%");
        System.out.println("Condição: " + dia.conditions);
        System.out.println("Precipitação: " + dia.precip + " mm");
        System.out.println("Vento: " + dia.windspeed + " km/h");
        System.out.println("Direção do vento: " + dia.winddir + "°");

        scanner.close();
    }
}
