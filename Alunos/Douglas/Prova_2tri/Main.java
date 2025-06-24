package fag;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class Main {

    static Scanner scanner = new Scanner(System.in);
    static User user;
    private static ObjectMapper mapper = new ObjectMapper();
    static ArrayList<Series> lastSearchResults = new ArrayList<>();

    static {
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    public static void main(String[] args) {
        user = UserManager.configUser(scanner);
        System.out.println("\nBem-vindo, " + user.getName() + "!");
        showMenu();
    }

    public static void showMenu() {
        int option = -1;

        while (true) {
            System.out.println("\n----- Menu -----");
            System.out.println("1 - Procurar séries");
            System.out.println("2 - Séries favoritas");
            System.out.println("3 - Séries já assistidas");
            System.out.println("4 - Séries que deseja assistir");
            System.out.println("0 - Sair");
            System.out.println("----------------");

            System.out.print("Digite a opção desejada: ");

            try {
                option = scanner.nextInt();
                scanner.nextLine();

                switch (option) {
                    case 0:
                        System.out.println("Saindo do sistema...");
                        System.exit(0);
                        break;
                    case 1:
                        String title = "";
                        while (title.trim().isEmpty()) {
                            System.out.print("Digite o nome da série: ");
                            title = scanner.nextLine();
                            if (title.trim().isEmpty()) {
                                System.out.println("Digite algo válido!");
                            }
                        }
                        searchSeries(title);
                        break;
                    case 2:
                        showSeriesList(user.getFavorites(), "Favoritas");
                        break;
                    case 3:
                        showSeriesList(user.getWatched(), "Já assistidas");
                        break;
                    case 4:
                        showSeriesList(user.getWantToWatch(), "Deseja assistir");
                        break;
                    default:
                        System.out.println("Opção inválida! Tente novamente.");
                        break;
                }

            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida! Digite apenas números.");
                scanner.nextLine();
            }
        }
    }

    public static void searchSeries(String title) {
        HttpClient client = HttpClient.newHttpClient();

        String fullUrl = ApiConfig.BASE_URL + "/search/shows?q=" + title.replace(" ", "+");

        try {
            URI url = new URI(fullUrl);

            HttpRequest request = HttpRequest.newBuilder(url)
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                parseSeriesResponse(response.body());

                if (!lastSearchResults.isEmpty()) {
                    int choice = -1;

                    do {
                        System.out.print("Digite o número da série que deseja adicionar, ou 0 para voltar ao menu: ");
                        try {
                            choice = scanner.nextInt();
                            scanner.nextLine();

                            if (choice == 0) {
                                System.out.println("Voltando ao menu...");
                                break;
                            } else if (choice < 0 || choice > lastSearchResults.size()) {
                                System.out.println("Opção inválida. Digite um número válido.");
                            } else {
                                Series selectedSeries = lastSearchResults.get(choice - 1);

                                System.out
                                        .println("Onde deseja adicionar a série '" + selectedSeries.getTitle() + "'?");
                                System.out.println("1 - Favoritos");
                                System.out.println("2 - Já assistidas");
                                System.out.println("3 - Deseja assistir");
                                System.out.print("Digite a opção: ");

                                int listChoice = scanner.nextInt();
                                scanner.nextLine();

                                switch (listChoice) {
                                    case 1:
                                        if (!user.getFavorites().contains(selectedSeries)) {
                                            user.getFavorites().add(selectedSeries);
                                            System.out.println("Adicionado aos favoritos!");
                                        } else {
                                            System.out.println("Série já está nos favoritos.");
                                        }
                                        break;
                                    case 2:
                                        if (!user.getWatched().contains(selectedSeries)) {
                                            user.getWatched().add(selectedSeries);
                                            System.out.println("Adicionado às séries já assistidas!");
                                        } else {
                                            System.out.println("Série já está na lista de assistidas.");
                                        }
                                        break;
                                    case 3:
                                        if (!user.getWantToWatch().contains(selectedSeries)) {
                                            user.getWantToWatch().add(selectedSeries);
                                            System.out.println("Adicionado à lista de deseja assistir!");
                                        } else {
                                            System.out.println("Série já está na lista de deseja assistir.");
                                        }
                                        break;
                                    default:
                                        System.out.println("Opção inválida. Voltando ao menu.");
                                        break;
                                }

                                UserManager.saveUser(user);
                                break;
                            }

                        } catch (InputMismatchException e) {
                            System.out.println("Entrada inválida. Digite um número.");
                            scanner.nextLine();
                        }
                    } while (true);
                }

            } else if (response.statusCode() == 400 || response.statusCode() == 404) {
                System.out.println("Erro na pesquisa.");
            } else {
                System.out.println("Erro na requisição. Código HTTP: " + response.statusCode());
            }

        } catch (Exception e) {
            System.out.println("Erro ao fazer a requisição: " + e.getMessage());
        }
    }

    public static void parseSeriesResponse(String json) {
        try {
            JsonNode root = mapper.readTree(json);

            if (root.isArray() && root.size() > 0) {
                lastSearchResults.clear();

                System.out.println("\n--- Resultados da busca ---");
                for (int i = 0; i < root.size(); i++) {
                    JsonNode show = root.get(i).get("show");

                    String title = show.hasNonNull("name") ? show.get("name").asText() : "N/A";
                    String language = show.hasNonNull("language") ? show.get("language").asText() : "N/A";

                    ArrayList<String> genres = new ArrayList<>();
                    if (show.has("genres") && show.get("genres").isArray()) {
                        for (JsonNode genreNode : show.get("genres")) {
                            genres.add(genreNode.asText());
                        }
                    }

                    double rating = show.has("rating") && show.get("rating").hasNonNull("average")
                            ? show.get("rating").get("average").asDouble()
                            : 0.0;

                    String status = show.hasNonNull("status") ? show.get("status").asText() : "N/A";

                    LocalDate premiered = null;
                    if (show.hasNonNull("premiered")) {
                        premiered = LocalDate.parse(show.get("premiered").asText());
                    }

                    LocalDate ended = null;
                    if (show.hasNonNull("ended")) {
                        ended = LocalDate.parse(show.get("ended").asText());
                    }

                    String network = show.has("network") && show.get("network").hasNonNull("name")
                            ? show.get("network").get("name").asText()
                            : "N/A";

                    Series series = new Series(title, language, genres, rating, status, premiered, ended, network);

                    lastSearchResults.add(series);

                    System.out.println("[" + (i + 1) + "] " + series.getTitle());
                    System.out.println("Idioma: " + series.getLanguage());
                    System.out.println("Gêneros: " + series.getGenres());
                    System.out.println("Nota: " + series.getNote());
                    System.out.println("Status: " + series.getStatus());
                    System.out
                            .println("Estreia: " + (series.getPremiered() != null ? series.getPremiered() : "N/A"));
                    System.out.println("Final: " + (series.getEnded() != null ? series.getEnded() : "N/A"));
                    System.out.println("Canal: " + series.getNetwork());
                    System.out.println();
                }
            } else {
                System.out.println("Nenhuma série encontrada.");
                lastSearchResults.clear();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Erro no parse: " + e.getMessage());
        }
    }

    public static void chooseSeriesAndAddToList() {
        if (lastSearchResults.isEmpty()) {
            System.out.println("Nenhuma série para escolher. Faça uma busca primeiro.");
            return;
        }

        System.out.println("Digite o número da série que deseja adicionar:");
        int choice = -1;

        while (true) {
            try {
                System.out.print("> ");
                choice = scanner.nextInt();
                scanner.nextLine();

                if (choice < 1 || choice > lastSearchResults.size()) {
                    System.out.println("Número inválido. Tente novamente.");
                } else {
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Digite um número válido.");
                scanner.nextLine();
            }
        }

        Series selected = lastSearchResults.get(choice - 1);

        System.out.println("Para qual lista deseja adicionar?");
        System.out.println("1 - Favoritos");
        System.out.println("2 - Já assistidas");
        System.out.println("3 - Deseja assistir");

        int list = -1;
        while (true) {
            try {
                System.out.print("> ");
                list = scanner.nextInt();
                scanner.nextLine();

                if (list < 1 || list > 3) {
                    System.out.println("Opção inválida. Tente novamente.");
                } else {
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Digite um número válido.");
                scanner.nextLine();
            }
        }

        switch (list) {
            case 1:
                if (!user.getFavorites().contains(selected)) {
                    user.getFavorites().add(selected);
                    System.out.println("Série adicionada aos Favoritos.");
                } else {
                    System.out.println("Série já está nos Favoritos.");
                }
                break;
            case 2:
                if (!user.getWatched().contains(selected)) {
                    user.getWatched().add(selected);
                    System.out.println("Série adicionada às Já assistidas.");
                } else {
                    System.out.println("Série já está nas Já assistidas.");
                }
                break;
            case 3:
                if (!user.getWantToWatch().contains(selected)) {
                    user.getWantToWatch().add(selected);
                    System.out.println("Série adicionada às Deseja assistir.");
                } else {
                    System.out.println("Série já está nas Deseja assistir.");
                }
                break;
        }

        UserManager.saveUser(user);
    }

    public static void showSeriesList(ArrayList<Series> list, String title) {
        System.out.println("\n--- " + title + " ---");
        if (list.isEmpty()) {
            System.out.println("Nenhuma série nesta lista.");
            return;
        }

        sortSeriesList(list, title);

        int i = 1;
        for (Series s : list) {
            System.out.println("[" + i++ + "]");
            System.out.println("Título: " + s.getTitle());
            System.out.println("Idioma: " + s.getLanguage());
            System.out.println("Gêneros: " + s.getGenres());
            System.out.println("Nota: " + s.getNote());
            System.out.println("Status: " + s.getStatus());
            System.out.println("Estreia: " + (s.getPremiered() != null ? s.getPremiered() : "N/A"));
            System.out.println("Final: " + (s.getEnded() != null ? s.getEnded() : "N/A"));
            System.out.println("Canal: " + s.getNetwork());
            System.out.println("-----------------------------");
        }

        System.out.print("Deseja remover alguma série desta lista? (S/N): ");

        String response = "";
        while (true) {
            response = scanner.nextLine().trim().toLowerCase();
            if (response.equals("s") || response.equals("n")) {
                break;
            } else {
                System.out.print("Resposta inválida. Digite 's' para sim ou 'n' para não: ");
            }
        }

        if (response.equals("s")) {
            int choice = -1;
            while (true) {
                System.out.print("Digite o número da série que deseja remover (ou 0 para cancelar): ");
                String input = scanner.nextLine().trim();

                try {
                    choice = Integer.parseInt(input);

                    if (choice == 0) {
                        System.out.println("Remoção cancelada.");
                        break;
                    }

                    if (choice < 1 || choice > list.size()) {
                        System.out.println("Número inválido. Tente novamente.");
                    } else {
                        Series removed = list.remove(choice - 1);
                        System.out.println("Série '" + removed.getTitle() + "' removida da lista.");
                        UserManager.saveUser(user);
                        break;
                    }

                } catch (NumberFormatException e) {
                    System.out.println("Digite um número válido.");
                }
            }
        }
    }

    public static void sortSeriesList(ArrayList<Series> list, String listName) {
        if (list.isEmpty()) {
            System.out.println("A lista está vazia. Nada para ordenar.");
            return;
        }

        System.out.println("\nEscolha o critério de ordenação:");
        System.out.println("1 - Ordem alfabética (Nome)");
        System.out.println("2 - Nota geral (decrescente)");
        System.out.println("3 - Estado da série (Status: Running, Ended, Cancelled)");
        System.out.println("4 - Data de estreia (da mais antiga para a mais nova)");
        System.out.print("Digite a opção: ");

        int option = -1;
        while (true) {
            try {
                option = scanner.nextInt();
                scanner.nextLine();

                if (option < 1 || option > 4) {
                    System.out.print("Opção inválida. Escolha entre 1 e 4: ");
                } else {
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.print("Digite um número válido: ");
                scanner.nextLine();
            }
        }

        switch (option) {
            case 1:
                Collections.sort(list, Comparator.comparing(Series::getTitle, String.CASE_INSENSITIVE_ORDER));
                System.out.println("Lista ordenada por nome.");
                break;
            case 2:
                Collections.sort(list, Comparator.comparingDouble(Series::getNote).reversed());
                System.out.println("Lista ordenada por nota geral.");
                break;
            case 3:
                Collections.sort(list, Comparator.comparing(Series::getStatus, String.CASE_INSENSITIVE_ORDER));
                System.out.println("Lista ordenada por status.");
                break;
            case 4:
                Collections.sort(list, Comparator.comparing(Series::getPremiered,
                        Comparator.nullsLast(Comparator.naturalOrder())));
                System.out.println("Lista ordenada por data de estreia.");
                break;
        }

        UserManager.saveUser(user);
        System.out.println("Lista salva com a nova ordem.");
    }

}