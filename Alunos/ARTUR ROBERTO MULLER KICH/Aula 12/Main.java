package org.example;

import org.example.model.DadosClima;
import org.example.service.ServicoClima;

import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ServicoClima servicoClima = new ServicoClima();

        System.out.println("=== Aplicativo de Consulta de Clima ===\n");

        while (true) {
            try {
                System.out.print("Digite o nome da cidade (ou 'sair' para encerrar): ");
                String cidade = scanner.nextLine().trim();

                if (cidade.equalsIgnoreCase("sair")) {
                    break;
                }

                if (cidade.isEmpty()) {
                    System.out.println("Por favor, digite um nome de cidade válido.\n");
                    continue;
                }

                System.out.println("\nConsultando dados do clima para " + cidade + "...");
                DadosClima dadosClima = servicoClima.consultarClima(cidade);

                System.out.println("\n" + dadosClima);
                System.out.println("\n-----------------------------------\n");

            } catch (Exception e) {
                System.out.println("\nErro: " + e.getMessage());
                System.out.println("Verifique o nome da cidade e sua conexão com a internet.\n");
                // Para depuração
                // e.printStackTrace();
            }
        }

        System.out.println("\nObrigado por usar o aplicativo de clima!");
        scanner.close();
    }
}
