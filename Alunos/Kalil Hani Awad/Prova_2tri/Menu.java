package org.example;

import java.util.InputMismatchException;
import java.util.Scanner;

import static org.example.Maratona.*;

public class Menu {
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        int opc = 0;
        pessoinha();

        while (opc != 9) {
            System.out.println("Gostaria de " +
                    "\n0 - Procurar uma serie," +
                    "\n1 - favoritar ou desfavoritar," +
                    "\n2 - Adicionar na lista de assitida" +
                    "\n3 - Adicionar na lista de assistirei" +
                    "\n4 - Exibir listas" +
                    "\n5 - Orderna-las por Ordem alfabetica" +
                    "\n6 - Ordena-las por Estado" +
                    "\n7 - Ordena-las por Nota" +
                    "\n8 - Ordena - las por data " +
                    "\n9 - Sair" +
                    "\n10 - Salvar lista series" +
                    "\n11 - Carregar lista series_Salva"
            );
            try {
                System.out.println("Qual opção gostaria de escolher");
                opc = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.err.println("Entrada inválida: " + e.getMessage());
                scanner.nextLine();
                opc = -1;
            }
            try {
                if (opc == 0) {
                    inicio();
                } else if (opc == 1) {
                    Favoritar_ou_Desfavoritar();
                } else if (opc == 2) {
                    Lista_Assistida();
                } else if (opc == 3) {
                    Lista_Assistirei();
                } else if (opc == 4) {
                    Exibir_listas();
                } else if (opc == 5) {
                    Ord_ALF();
                } else if (opc == 6) {
                    Estado_ALL();
                } else if (opc == 7) {
                    Nota();
                } else if (opc == 8) {
                    Ordenar_por_data();
                } else if (opc == 9) {
                    System.out.println("Você saiu");
                    break;
                } else if (opc == 10) {
                    salvarSeriesEmArquivo();
                } else if (opc == 11) {
                    carregarSeriesDoArquivo();
                } else {
                    System.out.println("Opção invalida");
                }
            } catch (Exception e) {
                System.err.println("Erro inesperado: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }
}