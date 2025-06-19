package com.example;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        try (Scanner scan = new Scanner(System.in)) {

            int escolha;
            Usuario usuario = new Usuario(scan);
            ListaManager listas = new ListaManager();
            String nome = usuario.getNome();
            if (nome.equals("")) {
                nome = "Convidado";
            }
            System.out.println("\nBem-vindo, " + nome + "!");

            do {
                System.out.println("\nMenu:");
                System.out.println("1. Procurar série");
                System.out.println("2. Adicionar série a uma lista");
                System.out.println("3. Remover série de uma lista");
                System.out.println("4. Exibir lista");
                System.out.println("5. Ordenar lista");
                System.out.println("6. Sair");

                System.out.print("Escolha uma opção: ");

                escolha = scan.nextInt();
                scan.nextLine();
                switch (escolha) {
                    case 1 -> {
                        System.out.print("Digite o nome da série: ");
                        String nomeSerie = scan.nextLine();
                        Serie serie = SeriesManager.buscarSerie(nomeSerie);
                        if (serie != null) {
                            System.out.println("\n" + serie);
                        }
                    }
                    case 2 -> {
                        System.out.print("Digite o nome da série: ");
                        String nomeSerie = scan.nextLine();
                        Serie serie = SeriesManager.buscarSerie(nomeSerie);
                        if (serie != null) {
                            System.out.print("Adicionar em (favoritos / assistidas / deseja): ");
                            String lista = scan.nextLine();
                            if (lista.equals("favoritos") || lista.equals("assistidas") || lista.equals("deseja")) {
                                listas.adicionarSerie(serie, lista);
                                System.out.println("Série adicionada!");
                            } else {
                                System.out.println("Escolha uma lista válida!");
                            }
                        } else {
                            System.out.println("Série não encontrada!");
                        }
                    }
                    case 3 -> {
                        System.out.print("Digite o nome da série: ");
                        String nomeSerie = scan.nextLine();
                        System.out.print("Remover de (favoritos / assistidas / deseja): ");
                        String lista = scan.nextLine();
                        listas.removerSerie(nomeSerie, lista);
                        System.out.println("Série removida!");
                    }
                    case 4 -> {
                        System.out.print("Qual lista quer exibir? (favoritos / assistidas / deseja): ");
                        String lista = scan.nextLine();
                        listas.exibirLista(lista);
                    }
                    case 5 -> {
                        System.out.print("Qual lista? (favoritos / assistidas / deseja): ");
                        String lista = scan.nextLine();
                        System.out.print("Ordenar por (nome / nota / status / estreia): ");
                        String criterio = scan.nextLine();
                        listas.ordenarLista(lista, criterio);
                    }
                    case 6 -> {
                        JsonManager.salvarLista("favoritos.json", listas.favoritos);
                        JsonManager.salvarLista("assistidas.json", listas.assistidas);
                        JsonManager.salvarLista("deseja.json", listas.desejaAssistir);
                        System.out.println("Listas salvas. Até mais!");
                        scan.close();
                        return;
                    }
                    default -> System.out.println("Opção inválida!");
                }
            } while (escolha != 0);
            scan.close();
        }
    }
}
