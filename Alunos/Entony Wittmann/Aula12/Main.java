package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String apiKey = "2SMMRCYCXTZ8359PSY5CGKZZD"; 
        ClimaService climaService = new ClimaService(apiKey);

        while (true) {
            System.out.print("\nDigite o nome da cidade: ");
            String cidade = scanner.nextLine().trim();

            if (cidade.isEmpty()) {
                System.out.println("Nome da cidade não pode estar vazio.");
                continue;
            }

            climaService.buscarClima(cidade);

            System.out.print("\nDeseja consultar outra cidade? (s/n): ");
            String resposta = scanner.nextLine().trim().toLowerCase();

            if (!resposta.equals("s")) {
                System.out.println("Encerrando o programa.");
                break;
            }
        }

        scanner.close();
    }
}