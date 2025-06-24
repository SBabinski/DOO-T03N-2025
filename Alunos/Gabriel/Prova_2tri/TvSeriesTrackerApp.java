
package com.tvseriestracker;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class TvSeriesTrackerApp {
    private User currentUser;
    private TvMazeApiClient apiClient;
    private JsonPersistence persistence;
    private Scanner scanner;

    public TvSeriesTrackerApp() {
        apiClient = new TvMazeApiClient();
        persistence = new JsonPersistence();
        scanner = new Scanner(System.in);
    }

    public void start() {
        currentUser = persistence.loadUser();
        if (currentUser == null) {
            System.out.print("Bem-vindo! Por favor, digite seu apelido: ");
            String nickname = scanner.nextLine();
            currentUser = new User(nickname);
            persistence.saveUser(currentUser);
        }
        System.out.println("Olá, " + currentUser.getNickname() + "! Bem-vindo de volta ao seu rastreador de séries.");
        showMainMenu();
    }

    private void showMainMenu() {
        int choice;
        do {
            System.out.println("\n--- Menu Principal ---");
            System.out.println("1. Procurar por séries");
            System.out.println("2. Ver minhas listas");
            System.out.println("3. Sair");
            System.out.print("Escolha uma opção: ");
            try {
                choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        searchShows();
                        break;
                    case 2:
                        viewMyLists();
                        break;
                    case 3:
                        persistence.saveUser(currentUser);
                        System.out.println("Obrigado por usar o rastreador de séries! Seus dados foram salvos.");
                        break;
                    default:
                        System.out.println("Opção inválida. Por favor, tente novamente.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, digite um número.");
                scanner.nextLine();
                choice = 0;
            }
        } while (choice != 3);
    }

    private void searchShows() {
        System.out.print("Digite o nome da série para procurar: ");
        String query = scanner.nextLine();
        try {
            List<SearchResult> results = apiClient.searchShows(query);
            if (results.isEmpty()) {
                System.out.println("Nenhuma série encontrada com este nome.");
                return;
            }

            System.out.println("\n--- Resultados da Busca ---");
            for (int i = 0; i < results.size(); i++) {
                Show show = results.get(i).getShow();
                System.out.println((i + 1) + ". " + show.getName() + " (" + show.getPremiered() + ")");
            }

            System.out.print("Digite o número da série para ver detalhes (0 para voltar): ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice > 0 && choice <= results.size()) {
                Show selectedShow = results.get(choice - 1).getShow();
                displayShowDetails(selectedShow);
                showShowOptionsMenu(selectedShow);
            }
        } catch (Exception e) {
            System.err.println("Erro ao procurar séries: " + e.getMessage());
        }
    }

    private void displayShowDetails(Show show) {
        System.out.println("\n--- Detalhes da Série ---");
        System.out.println("Nome: " + show.getName());
        System.out.println("Idioma: " + show.getLanguage());
        System.out.println("Gêneros: " + (show.getGenres() != null ? String.join(", ", show.getGenres()) : "N/A"));
        System.out.println("Nota Geral: " + (show.getRating() != null && show.getRating().getAverage() != null ? show.getRating().getAverage() : "N/A"));
        System.out.println("Estado: " + show.getStatus());
        System.out.println("Data de Estreia: " + show.getPremiered());
        System.out.println("Data de Término: " + (show.getEnded() != null ? show.getEnded() : "Ainda transmitindo"));
        System.out.println("Emissora: " + (show.getNetwork() != null ? show.getNetwork().getName() : "N/A"));
        System.out.println("Resumo: " + (show.getSummary() != null ? show.getSummary().replaceAll("<[^>]*>", "") : "N/A"));
    }

    private void showShowOptionsMenu(Show show) {
        int choice;
        do {
            System.out.println("\n--- Opções da Série ---");
            System.out.println("1. Adicionar aos Favoritos");
            System.out.println("2. Adicionar às Séries Já Assistidas");
            System.out.println("3. Adicionar às Séries Que Deseja Assistir");
            System.out.println("4. Remover dos Favoritos");
            System.out.println("5. Remover das Séries Já Assistidas");
            System.out.println("6. Remover das Séries Que Deseja Assistir");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");
            try {
                choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        currentUser.addFavorite(show);
                        System.out.println("Adicionado aos favoritos.");
                        break;
                    case 2:
                        currentUser.addWatched(show);
                        System.out.println("Adicionado às séries já assistidas.");
                        break;
                    case 3:
                        currentUser.addWatchlist(show);
                        System.out.println("Adicionado às séries que deseja assistir.");
                        break;
                    case 4:
                        currentUser.removeFavorite(show);
                        System.out.println("Removido dos favoritos.");
                        break;
                    case 5:
                        currentUser.removeWatched(show);
                        System.out.println("Removido das séries já assistidas.");
                        break;
                    case 6:
                        currentUser.removeWatchlist(show);
                        System.out.println("Removido das séries que deseja assistir.");
                        break;
                    case 0:
                        break;
                    default:
                        System.out.println("Opção inválida. Por favor, tente novamente.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, digite um número.");
                scanner.nextLine();
                choice = -1;
            }
        } while (choice != 0);
    }

    private void viewMyLists() {
        int choice;
        do {
            System.out.println("\n--- Minhas Listas ---");
            System.out.println("1. Favoritos");
            System.out.println("2. Séries Já Assistidas");
            System.out.println("3. Séries Que Deseja Assistir");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma lista para ver: ");
            try {
                choice = scanner.nextInt();
                scanner.nextLine();

                List<Show> listToDisplay = null;
                String listName = "";

                switch (choice) {
                    case 1:
                        listToDisplay = currentUser.getFavorites();
                        listName = "Favoritos";
                        break;
                    case 2:
                        listToDisplay = currentUser.getWatched();
                        listName = "Séries Já Assistidas";
                        break;
                    case 3:
                        listToDisplay = currentUser.getWatchlist();
                        listName = "Séries Que Deseja Assistir";
                        break;
                    case 0:
                        break;
                    default:
                        System.out.println("Opção inválida. Por favor, tente novamente.");
                }

                if (listToDisplay != null) {
                    displayAndSortList(listToDisplay, listName);
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, digite um número.");
                scanner.nextLine();
                choice = -1;
            }
        } while (choice != 0);
    }

    private void displayAndSortList(List<Show> shows, String listName) {
        if (shows.isEmpty()) {
            System.out.println("Sua lista de " + listName + " está vazia.");
            return;
        }

        int sortChoice;
        do {
            System.out.println("\n--- " + listName + " ---");
            System.out.println("Ordenar por:");
            System.out.println("1. Ordem alfabética do nome");
            System.out.println("2. Nota geral");
            System.out.println("3. Estado da série");
            System.out.println("4. Data de estreia");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção de ordenação: ");
            try {
                sortChoice = scanner.nextInt();
                scanner.nextLine();

                List<Show> sortedShows = new ArrayList<>(shows);

                switch (sortChoice) {
                    case 1:
                        sortedShows.sort(Comparator.comparing(Show::getName));
                        break;
                    case 2:
                        sortedShows.sort(Comparator.comparing(s -> s.getRating() != null && s.getRating().getAverage() != null ? s.getRating().getAverage() : 0.0, Comparator.reverseOrder()));
                        break;
                    case 3:
                        sortedShows.sort(Comparator.comparing(Show::getStatus));
                        break;
                    case 4:
                        sortedShows.sort(Comparator.comparing(Show::getPremiered));
                        break;
                    case 0:
                        return;
                    default:
                        System.out.println("Opção inválida. Por favor, tente novamente.");
                        continue;
                }

                for (int i = 0; i < sortedShows.size(); i++) {
                    Show show = sortedShows.get(i);
                    System.out.println((i + 1) + ". " + show.getName() + " (Nota: " + (show.getRating() != null && show.getRating().getAverage() != null ? show.getRating().getAverage() : "N/A") + ", Estado: " + show.getStatus() + ", Estreia: " + show.getPremiered() + ")");
                }

                System.out.print("Digite o número da série para ver detalhes (0 para voltar): ");
                int detailChoice = scanner.nextInt();
                scanner.nextLine();

                if (detailChoice > 0 && detailChoice <= sortedShows.size()) {
                    Show selectedShow = sortedShows.get(detailChoice - 1);
                    displayShowDetails(selectedShow);
                    showShowOptionsMenu(selectedShow);
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, digite um número.");
                scanner.nextLine();
                sortChoice = -1;
            }
        } while (sortChoice != 0);
    }

    public static void main(String[] args) {
        TvSeriesTrackerApp app = new TvSeriesTrackerApp();
        app.start();
    }
}


