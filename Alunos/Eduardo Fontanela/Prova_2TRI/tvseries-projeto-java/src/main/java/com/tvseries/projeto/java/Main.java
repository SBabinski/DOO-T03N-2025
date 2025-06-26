package com.tvseries.projeto.java;

import com.tvseries.projeto.java.exception.ApiException;
import com.tvseries.projeto.java.model.Serie;
import com.tvseries.projeto.java.model.Usuario;
import com.tvseries.projeto.java.service.ConsumoApi;
import com.tvseries.projeto.java.service.GerenciadorDeListas;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final ConsumoApi consumoApi = new ConsumoApi();
    private static final GerenciadorDeListas gerenciador = new GerenciadorDeListas();
    private static Usuario usuario;

    public static void main(String[] args) {
        System.out.println("=========================================");
        System.out.println("  Bem-vindo ao seu Gerenciador de Séries!");
        System.out.println("=========================================");
        System.out.print("Para começar, digite seu nome ou apelido: ");
        usuario = new Usuario(scanner.nextLine());
        System.out.println("\nOlá, " + usuario.getNome() + "! O que vamos fazer hoje?");

        int opcao = -1;
        while (opcao != 0) {
            exibirMenu();
            try {
                opcao = Integer.parseInt(scanner.nextLine());
                rotearOpcao(opcao);
            } catch (NumberFormatException e) {
                System.out.println("\n>>> Erro: Por favor, insira um número válido. <<<\n");
            } catch (ApiException e) {
                System.out.println("\n>>> Erro de API: " + e.getMessage() + " <<<\n");
            } catch (Exception e) {
                System.out.println("\n>>> Ocorreu um erro inesperado: " + e.getMessage() + " <<<\n");
            }
        }

        System.out.println("\nSalvando suas listas...");
        gerenciador.salvarListas();
        System.out.println("Tudo salvo! Até a próxima, " + usuario.getNome() + "!");
        scanner.close();
    }

    private static void exibirMenu() {
        System.out.println("\n---------- MENU PRINCIPAL ----------");
        System.out.println("1. Procurar por uma série na internet");
        System.out.println("2. Exibir minhas listas de séries");
        System.out.println("3. Adicionar série a uma lista");
        System.out.println("4. Remover série de uma lista");
        System.out.println("------------------------------------");
        System.out.println("0. Sair do programa");
        System.out.print("Escolha sua opção: ");
    }

    private static void rotearOpcao(int opcao) throws ApiException {
        switch (opcao) {
            case 1 -> procurarSerie();
            case 2 -> exibirListas();
            case 3 -> adicionarSerie();
            case 4 -> removerSerie();
            case 0 -> {} 
            default -> System.out.println("\n>>> Opção inválida! Tente novamente. <<<\n");
        }
    }

    private static void procurarSerie() throws ApiException {
        System.out.print("\nDigite o nome da série para buscar: ");
        String nomeSerie = scanner.nextLine();
        List<Serie> seriesEncontradas = consumoApi.buscarSeries(nomeSerie);

        if (seriesEncontradas.isEmpty()) {
            System.out.println("\nNenhuma série encontrada com o nome '" + nomeSerie + "'.");
            return;
        }

        System.out.println("\nSéries encontradas (as informações completas serão exibidas):");
        seriesEncontradas.forEach(System.out::println);
    }

    private static void exibirListas() {
        System.out.println("\nQual lista você deseja exibir?");
        System.out.println("1. Favoritos");
        System.out.println("2. Já assistidas");
        System.out.println("3. Desejo assistir");
        System.out.print("Opção: ");
        int escolha = Integer.parseInt(scanner.nextLine());

        List<Serie> lista;
        String nomeLista;

        switch (escolha) {
            case 1 -> { lista = gerenciador.getFavoritos(); nomeLista = "Favoritos"; }
            case 2 -> { lista = gerenciador.getAssistidas(); nomeLista = "Já Assistidas"; }
            case 3 -> { lista = gerenciador.getParaAssistir(); nomeLista = "Desejo Assistir"; }
            default -> { System.out.println("Opção inválida."); return; }
        }

        if (lista.isEmpty()) {
            System.out.println("\nA lista '" + nomeLista + "' está vazia.");
            return;
        }

        System.out.println("\n--- LISTA: " + nomeLista.toUpperCase() + " ---");
        for (int i = 0; i < lista.size(); i++) {
            System.out.println((i + 1) + ". " + lista.get(i).getNome());
        }

        System.out.print("\nDeseja ordenar esta lista? (s/n): ");
        if (scanner.nextLine().equalsIgnoreCase("s")) {
            ordenarLista(lista);
            System.out.println("\n--- LISTA ORDENADA: " + nomeLista.toUpperCase() + " ---");
            for (int i = 0; i < lista.size(); i++) {
                System.out.println((i + 1) + ". " + lista.get(i).getNome() + " (Nota: " + (lista.get(i).getRating() != null && lista.get(i).getRating().getNotaGeral() != null ? lista.get(i).getRating().getNotaGeral() : "N/A") + ")");
            }
        }

        System.out.print("\nDeseja ver detalhes de alguma série desta lista? (s/n): ");
        if (scanner.nextLine().equalsIgnoreCase("s")) {
            System.out.print("Digite o número da série: ");
            int index = Integer.parseInt(scanner.nextLine()) - 1;
            if (index >= 0 && index < lista.size()) {
                System.out.println(lista.get(index));
            } else {
                System.out.println("Número inválido.");
            }
        }
    }


    private static void ordenarLista(List<Serie> lista) {
        System.out.println("\nOrdenar por:");
        System.out.println("1. Ordem Alfabética (A-Z)");
        System.out.println("2. Nota Geral (maior para menor)");
        System.out.println("3. Estado (Ordem Alfabética)");
        System.out.println("4. Data de Estreia (mais recente primeiro)");
        System.out.print("Critério: ");
        int criterio = Integer.parseInt(scanner.nextLine());

        switch (criterio) {
            case 1 -> lista.sort(Comparator.comparing(Serie::getNome, String.CASE_INSENSITIVE_ORDER));
            case 2 -> lista.sort(Comparator.comparing(s -> (s.getRating() != null && s.getRating().getNotaGeral() != null) ? s.getRating().getNotaGeral() : 0.0, Comparator.reverseOrder()));
            case 3 -> lista.sort(Comparator.comparing(s -> s.getEstado() != null ? s.getEstado() : "", String.CASE_INSENSITIVE_ORDER));
            case 4 -> lista.sort(Comparator.comparing(s -> s.getDataEstreia() != null ? s.getDataEstreia() : "", Comparator.reverseOrder()));
            default -> { System.out.println("Critério inválido."); return; }
        }
        System.out.println("\nLista ordenada com sucesso!");
    }


    private static void adicionarSerie() throws ApiException {
        System.out.print("\nDigite o nome da série que deseja adicionar: ");
        String nomeSerie = scanner.nextLine();
        List<Serie> seriesEncontradas = consumoApi.buscarSeries(nomeSerie);

        if (seriesEncontradas.isEmpty()) {
            System.out.println("Nenhuma série encontrada com este nome.");
            return;
        }

        System.out.println("\nSelecione uma das séries encontradas:");
        for (int i = 0; i < seriesEncontradas.size(); i++) {
            System.out.println((i + 1) + ". " + seriesEncontradas.get(i).getNome());
        }
        System.out.print("Sua escolha: ");
        int escolhaSerie = Integer.parseInt(scanner.nextLine()) - 1;

        if (escolhaSerie < 0 || escolhaSerie >= seriesEncontradas.size()) {
            System.out.println("Escolha inválida.");
            return;
        }

        Serie serieEscolhida = seriesEncontradas.get(escolhaSerie);

        System.out.println("\nAdicionar '" + serieEscolhida.getNome() + "' em qual lista?");
        System.out.println("1. Favoritos");
        System.out.println("2. Já assistidas");
        System.out.println("3. Desejo assistir");
        System.out.print("Opção: ");
        int escolhaLista = Integer.parseInt(scanner.nextLine());

        switch (escolhaLista) {
            case 1 -> gerenciador.adicionarAFavoritos(serieEscolhida);
            case 2 -> gerenciador.adicionarAAssistidas(serieEscolhida);
            case 3 -> gerenciador.adicionarAParaAssistir(serieEscolhida);
            default -> { System.out.println("Opção de lista inválida."); return; }
        }
        System.out.println("\nSérie adicionada com sucesso!");
    }

    private static void removerSerie() {
        System.out.println("\nDe qual lista você deseja remover uma série?");
        System.out.println("1. Favoritos");
        System.out.println("2. Já assistidas");
        System.out.println("3. Desejo assistir");
        System.out.print("Opção: ");
        int escolhaLista = Integer.parseInt(scanner.nextLine());

        List<Serie> lista;
        String nomeLista;

        switch (escolhaLista) {
            case 1 -> { lista = gerenciador.getFavoritos(); nomeLista = "Favoritos"; }
            case 2 -> { lista = gerenciador.getAssistidas(); nomeLista = "Já Assistidas"; }
            case 3 -> { lista = gerenciador.getParaAssistir(); nomeLista = "Desejo Assistir"; }
            default -> { System.out.println("Opção inválida."); return; }
        }

        if (lista.isEmpty()) {
            System.out.println("\nA lista '" + nomeLista + "' já está vazia.");
            return;
        }

        System.out.println("\nQual série você deseja remover da lista '" + nomeLista + "'?");
        for (int i = 0; i < lista.size(); i++) {
            System.out.println((i + 1) + ". " + lista.get(i).getNome());
        }
        System.out.print("Sua escolha: ");
        int escolhaSerie = Integer.parseInt(scanner.nextLine()) - 1;

        if (escolhaSerie < 0 || escolhaSerie >= lista.size()) {
            System.out.println("Escolha inválida.");
            return;
        }

        Serie serieRemovida = lista.get(escolhaSerie);


        switch (escolhaLista) {
            case 1 -> gerenciador.removerDeFavoritos(escolhaSerie);
            case 2 -> gerenciador.removerDeAssistidas(escolhaSerie);
            case 3 -> gerenciador.removerDeParaAssistir(escolhaSerie);
        }

        System.out.println("\nA série '" + serieRemovida.getNome() + "' foi removida com sucesso!");
    }
}