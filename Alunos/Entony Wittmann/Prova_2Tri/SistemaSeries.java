package org.example;

import java.util.*;

public class SistemaSeries {
    private static Map<String, Usuario> usuarios = new HashMap<>();

    public static void executar() {
        usuarios = Persistencia.carregarDados();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Bem-vindo ao sistema de acompanhamento de séries!");

        System.out.print("Por favor, digite seu nome ou apelido: ");
        String nomeUsuario = scanner.nextLine();

        Usuario usuario = usuarios.computeIfAbsent(nomeUsuario, Usuario::new);

        int opcao = 0;
        do {
            System.out.println("\nOlá, " + usuario.getNome() + "! Escolha uma opção:");
            System.out.println("1. Buscar séries");
            System.out.println("2. Ver/ordenar/remover minhas listas");
            System.out.println("3. Salvar e sair");

            try {
                opcao = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Opção inválida. Tente novamente.");
                continue;
            }

            switch (opcao) {
                case 1 -> buscarSeries(scanner, usuario);
                case 2 -> gerenciarListas(scanner, usuario);
                case 3 -> {
                    Persistencia.salvarDados(usuarios);
                    System.out.println("Dados salvos com sucesso. Até logo!");
                }
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 3);

        scanner.close();
    }

    private static void buscarSeries(Scanner scanner, Usuario usuario) {
        System.out.print("Digite o nome da série que deseja buscar: ");
        String nomeBusca = scanner.nextLine();

        List<Serie> seriesEncontradas = TVMazeAPI.buscarSeries(nomeBusca);

        if (seriesEncontradas == null || seriesEncontradas.isEmpty()) {
            System.out.println("Nenhuma série encontrada.");
        } else {
            System.out.println("\nSéries encontradas:");
            for (int i = 0; i < seriesEncontradas.size(); i++) {
                System.out.printf("%d. %s\n", i + 1, seriesEncontradas.get(i).getNome());
            }

            System.out.print("\nEscolha o número da série para mais detalhes ou digite 0 para voltar: ");
            try {
                int escolha = Integer.parseInt(scanner.nextLine()) - 1;

                if (escolha >= 0 && escolha < seriesEncontradas.size()) {
                    Serie serie = seriesEncontradas.get(escolha);
                    System.out.println("\n" + serie);

                    System.out.println("\nO que deseja fazer?");
                    System.out.println("1. Adicionar aos Favoritos");
                    System.out.println("2. Adicionar às Séries Assistidas");
                    System.out.println("3. Adicionar às Séries para Assistir");
                    System.out.println("0. Voltar");

                    int decisao = Integer.parseInt(scanner.nextLine());
                    switch (decisao) {
                        case 1 -> usuario.adicionarFavorito(serie);
                        case 2 -> usuario.adicionarAssistida(serie);
                        case 3 -> usuario.adicionarParaAssistir(serie);
                        case 0 -> {}
                        default -> System.out.println("Opção inválida.");
                    }
                }
            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                System.out.println("Opção inválida.");
            }
        }
    }

    private static void gerenciarListas(Scanner scanner, Usuario usuario) {
        System.out.println("\nQual lista deseja visualizar?");
        System.out.println("1. Favoritos");
        System.out.println("2. Assistidas");
        System.out.println("3. Para Assistir");

        int escolha = Integer.parseInt(scanner.nextLine());
        List<Serie> lista = switch (escolha) {
            case 1 -> usuario.getFavoritos();
            case 2 -> usuario.getAssistidas();
            case 3 -> usuario.getParaAssistir();
            default -> {
                System.out.println("Opção inválida.");
                yield null;
            }
        };

        if (lista == null || lista.isEmpty()) {
            System.out.println("Nenhuma série nessa lista.");
            return;
        }

        System.out.println("\nComo deseja ordenar?");
        System.out.println("1. Por nome (A-Z)");
        System.out.println("2. Por nota (maior para menor)");
        System.out.println("3. Por estado (ex: Em Exibição, Finalizada)");
        System.out.println("4. Por data de estreia (mais recente primeiro)");

        int ordem = Integer.parseInt(scanner.nextLine());
        switch (ordem) {
            case 1 -> lista.sort(Comparator.comparing(Serie::getNome));
            case 2 -> lista.sort(Comparator.comparingDouble(Serie::getNotaGeral).reversed());
            case 3 -> lista.sort(Comparator.comparing(Serie::getEstado));
            case 4 -> lista.sort(Comparator.comparing(Serie::getDataEstreia, Comparator.nullsLast(Comparator.reverseOrder())));
            default -> System.out.println("Opção de ordenação inválida.");
        }

        for (int i = 0; i < lista.size(); i++) {
            System.out.printf("%d. %s\n", i + 1, lista.get(i).getNome());
        }

        System.out.print("\nDeseja remover alguma série? Digite o número correspondente ou 0 para sair: ");
        try {
            int indiceRemover = Integer.parseInt(scanner.nextLine());
            if (indiceRemover > 0 && indiceRemover <= lista.size()) {
                String nomeRemover = lista.get(indiceRemover - 1).getNome();
                switch (escolha) {
                    case 1 -> usuario.removerFavorito(nomeRemover);
                    case 2 -> usuario.removerAssistida(nomeRemover);
                    case 3 -> usuario.removerParaAssistir(nomeRemover);
                }
                System.out.println("Série removida com sucesso.");
            } else if (indiceRemover != 0) {
                System.out.println("Número inválido.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida.");
        }
    }
}
