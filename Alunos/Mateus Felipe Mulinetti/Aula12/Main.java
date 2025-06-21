package org.aplicacao;

import java.util.Scanner;
import java.util.InputMismatchException;

import static java.lang.System.exit;

public class Main {
    public static void main(String[] args) throws Exception {
        menu();

    }

    public static void menu() throws Exception {
        Scanner scanner = new Scanner(System.in);
        int opcao = 0;
        while (true) {
            System.out.println("Digite a opcão desejada: [1] consultar tempo [2] sair");
            try {
                opcao = scanner.nextInt();
                switch (opcao) {
                    case 1:
                        tempo();
                        break;
                    case 2:
                        System.out.println("\nSaindo...");
                        exit(0);
                    default:
                        System.out.println("Erro: argumentos inválidos");
                        menu();
                }
            } catch (InputMismatchException e) {
                System.out.println("Erro: Digite apenas números!");
                scanner.nextLine();
                opcao = 0;
            }
        }
    }


    public static void tempo() throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite a localização");
        String nomeCidade = scanner.nextLine();
        try {
            Tempo tempo = new Apiservico().getTempo(nomeCidade);
            System.out.println(tempo);
            menu();
        } catch (Exception e) {
            System.out.println("Erro ao obter dados do tempo nome invalido ou não encontrado");
            menu();
        }
    }
}