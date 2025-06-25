import java.util.*;

public class ConsoleApp {
    private static final Scanner scanner = new Scanner(System.in);
    private static String nickname;
    private static final TvMazeClient client = new TvMazeClient();
    private static final LibraryService service = new LibraryService();

    public static void main(String[] args) {
        System.out.println("=======================================");
        System.out.println("     Bem-vindo ao Gerenciador de Séries");
        System.out.println("=======================================");
        System.out.print("Por favor, digite seu apelido: ");
        nickname = scanner.nextLine().trim();

        int option;
        do {
            printMenu();
            option = getIntInput("Selecione uma opção do menu: ");
            switch (option) {
                case 1 ->
                    searchSeries();
                case 2 ->
                    showList("Favoritas", service.getList(nickname, ListName.FAVORITES), ListName.FAVORITES);
                case 3 ->
                    showList("Assistidas", service.getList(nickname, ListName.WATCHED), ListName.WATCHED);
                case 4 ->
                    showList("Para assistir", service.getList(nickname, ListName.TO_WATCH), ListName.TO_WATCH);
                case 5 ->
                    sortListMenu();
                case 0 ->
                    System.out.println("\nObrigado por utilizar o Gerenciador de Séries. Até logo!");
                default ->
                    System.out.println("\n[Erro] Opção inválida. Por favor, selecione uma opção existente no menu.");
            }
        } while (option != 0);
    }

    private static void printMenu() {
        System.out.println("\n---------------------------------------");
        System.out.println("MENU PRINCIPAL - Usuário: " + nickname);
        System.out.println("---------------------------------------");
        System.out.println("1) Buscar série por nome");
        System.out.println("2) Ver lista de séries favoritas");
        System.out.println("3) Ver lista de séries assistidas");
        System.out.println("4) Ver lista de séries para assistir");
        System.out.println("5) Ordenar suas listas");
        System.out.println("0) Sair do programa");
    }

    private static void searchSeries() {
        System.out.println("\n---------------------------------------");
        System.out.println("BUSCA DE SÉRIES");
        System.out.println("---------------------------------------");
        System.out.print("Digite parte ou o nome completo da série desejada: ");
        String name = scanner.nextLine().trim();

        if (name.isEmpty()) {
            System.out.println("[Aviso] Nenhum nome foi digitado. Retornando ao menu principal.");
            return;
        }

        List<Show> results = client.searchShows(name);
        if (results.isEmpty()) {
            System.out.println("[Aviso] Nenhuma série encontrada com o nome fornecido.");
            return;
        }

        System.out.println("\nForam encontradas as seguintes séries:");
        for (int i = 0; i < results.size(); i++) {
            Show show = results.get(i);
            System.out.printf("%d) %s\n", i + 1, show.toStringCompact());
        }

        while (true) {
            int choice = getIntInput("Digite o número da série para ver detalhes (ou 0 para voltar ao menu): ");
            if (choice == 0) {
                System.out.println("Retornando ao menu principal.");
                return;
            } else if (choice > 0 && choice <= results.size()) {
                Show selected = results.get(choice - 1);
                System.out.println("\n=======================================");
                System.out.println("DETALHES DA SÉRIE SELECIONADA");
                System.out.println("=======================================");
                System.out.println(selected.toStringDetail());
                manageShow(selected);
                return;
            } else {
                System.out.println("[Erro] Opção inválida. Digite um número correspondente a uma das séries listadas ou 0 para voltar.");
            }
        }
    }

    private static void manageShow(Show show) {
        while (true) {
            System.out.println("\nGerenciamento de Listas para a série: " + show.getName());
            System.out.println("---------------------------------------");
            System.out.println("1) Adicionar/remover dos Favoritos");
            System.out.println("2) Adicionar/remover de Assistidas");
            System.out.println("3) Adicionar/remover de Para Assistir");
            System.out.println("0) Voltar ao menu anterior");
            System.out.print("Digite o(s) número(s) da(s) lista(s) separados por vírgula (exemplo: 1,3): ");
            String input = scanner.nextLine().trim();

            if (input.equals("0")) {
                System.out.println("Retornando ao menu anterior.");
                return;
            }

            boolean anyValid = false;
            String[] parts = input.split(",");
            for (String part : parts) {
                int opt;
                try {
                    opt = Integer.parseInt(part.trim());
                } catch (NumberFormatException e) {
                    System.out.println("[Erro] Valor inválido: " + part.trim());
                    continue;
                }

                ListName list = switch (opt) {
                    case 1 -> ListName.FAVORITES;
                    case 2 -> ListName.WATCHED;
                    case 3 -> ListName.TO_WATCH;
                    default -> null;
                };

                if (list == null) {
                    System.out.println("[Erro] Opção inválida: " + part.trim());
                    continue;
                }

                anyValid = true;
                boolean exists = service.isInList(nickname, show.getId(), list);
                if (exists) {
                    service.removeFromList(nickname, show.getId(), list);
                    System.out.println("Removido de '" + list.getLabel() + "' com sucesso.");
                } else {
                    service.addToList(nickname, show.getId(), list);
                    System.out.println("Adicionado a '" + list.getLabel() + "' com sucesso.");
                }
            }
            if (anyValid) {
                return;
            }
            // Se nenhuma opção válida foi processada, o laço continua até o usuário acertar ou digitar 0.
        }
    }

    private static void showList(String title, Set<Integer> ids, ListName listName) {
        System.out.println("\n=======================================");
        System.out.println("LISTA DE SÉRIES - " + title.toUpperCase());
        System.out.println("=======================================");

        if (ids.isEmpty()) {
            System.out.println("[Aviso] Sua lista de '" + title + "' está vazia no momento.");
            return;
        }

        List<Show> shows = new ArrayList<>();
        int idx = 1;
        for (int id : ids) {
            Show show = client.getShowById(id);
            if (show != null) {
                shows.add(show);
                System.out.printf("%d) %s\n", idx, show.getName());
                idx++;
            }
        }

        while (true) {
            int choice = getIntInput("Digite o número da série para remover da lista (ou 0 para voltar ao menu): ");
            if (choice == 0) {
                System.out.println("Retornando ao menu principal.");
                return;
            } else if (choice > 0 && choice <= shows.size()) {
                Show selected = shows.get(choice - 1);
                service.removeFromList(nickname, selected.getId(), listName);
                System.out.println("A série '" + selected.getName() + "' foi removida da lista '" + title + "'.");

                // Atualiza a lista após remoção
                Set<Integer> updatedIds = service.getList(nickname, listName);
                if (updatedIds.isEmpty()) {
                    System.out.println("[Aviso] Sua lista de '" + title + "' agora está vazia.");
                    return;
                }
                shows.clear();
                idx = 1;
                System.out.println("\nLista atualizada de '" + title + "':");
                for (int id : updatedIds) {
                    Show show = client.getShowById(id);
                    if (show != null) {
                        shows.add(show);
                        System.out.printf("%d) %s\n", idx, show.getName());
                        idx++;
                    }
                }
            } else {
                System.out.println("[Erro] Opção inválida. Digite o número de uma série da lista ou 0 para voltar.");
            }
        }
    }

    private static void sortListMenu() {
        System.out.println("\n---------------------------------------");
        System.out.println("ORDENAÇÃO DE LISTAS");
        System.out.println("---------------------------------------");
        System.out.println("Qual lista deseja ordenar?");
        System.out.println("1) Favoritas");
        System.out.println("2) Assistidas");
        System.out.println("3) Para assistir");
        int opt = getIntInput("Digite o número da lista desejada: ");

        Set<Integer> ids;
        ListName listName;

        switch (opt) {
            case 1 -> {
                listName = ListName.FAVORITES;
                ids = service.getList(nickname, listName);
            }
            case 2 -> {
                listName = ListName.WATCHED;
                ids = service.getList(nickname, listName);
            }
            case 3 -> {
                listName = ListName.TO_WATCH;
                ids = service.getList(nickname, listName);
            }
            default -> {
                System.out.println("[Erro] Opção inválida. Retornando ao menu principal.");
                return;
            }
        }

        List<Show> shows = new ArrayList<>();
        for (int id : ids) {
            Show show = client.getShowById(id);
            if (show != null) {
                shows.add(show);
            }
        }

        if (shows.isEmpty()) {
            System.out.println("[Aviso] A lista selecionada está vazia. Não é possível ordenar uma lista vazia.");
            return;
        }

        System.out.println("\nEscolha o critério de ordenação:");
        System.out.println("1) Nome (ordem alfabética)");
        System.out.println("2) Nota geral (decrescente)");
        System.out.println("3) Status da série");
        System.out.println("4) Data de estreia");
        int sortOpt = getIntInput("Digite o número do critério desejado: ");

        Comparator<Show> comparator = null;

        switch (sortOpt) {
            case 1 -> comparator = Comparator.comparing(Show::getName, String.CASE_INSENSITIVE_ORDER);
            case 2 -> comparator = Comparator.comparingDouble(Show::getRating).reversed();
            case 3 -> comparator = Comparator.comparing(Show::getStatus, String.CASE_INSENSITIVE_ORDER);
            case 4 -> comparator = Comparator.comparing(Show::getPremiered, Comparator.nullsLast(Comparator.naturalOrder()));
            default -> {
                System.out.println("[Erro] Critério inválido. Retornando ao menu principal.");
                return;
            }
        }

        shows.sort(comparator);

        System.out.println("\nResultado da lista '" + listName.getLabel() + "' ordenada:");
        for (Show show : shows) {
            System.out.println("- " + show.getName());
        }
    }

    private static int getIntInput(String prompt) {
        System.out.print(prompt);
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}
