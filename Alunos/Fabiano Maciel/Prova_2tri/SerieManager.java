// TESTE GIT STATUS - 26/06/]

// Teste git status 26/06/2025
package Prova_2tri;

import java.util.*;

public class SerieManager {
    private User user;

    public SerieManager(User user) {
        this.user = user;
    }

    public void menuBusca(Scanner sc) {
        System.out.print("Digite o nome da série para buscar: ");
        String termo = sc.nextLine();
        try {
            List<Serie> resultados = TvMazeApi.buscarSeries(termo);
            if (resultados.isEmpty()) {
                System.out.println("Nenhuma série encontrada.");
                return;
            }
            for (int i = 0; i < resultados.size(); i++) {
                System.out.printf("%d. %s\n", i + 1, resultados.get(i).getNome());
            }
            while (true) {
                System.out.print("Ver detalhes de qual série (0 para cancelar)? ");
                String input = sc.nextLine();
                try {
                    int idx = Integer.parseInt(input) - 1;
                    if (idx == -1) {
                        break;
                    }
                    if (idx >= 0 && idx < resultados.size()) {
                        Serie s = resultados.get(idx);
                        System.out.println("\nDetalhes da série:");
                        System.out.println(s);
                        menuAdicionarSerie(sc, s);
                        break; 
                    } else {
                        System.out.println("Opção inválida, tente novamente.");
                    }
                } catch (Exception e) {
                    System.out.println("Opção inválida, tente novamente.");
                }
            }
        } catch (Exception e) {
            System.out.println("Erro na busca: Verifique sua conexão com a internet.");
        }
    }

    private void menuAdicionarSerie(Scanner sc, Serie s) {
        while (true) {
            System.out.println("1. Adicionar aos favoritos");
            System.out.println("2. Adicionar a já assistidas");
            System.out.println("3. Adicionar a deseja assistir");
            System.out.println("0. Voltar");
            System.out.print("Escolha: ");
            String input = sc.nextLine();
            try {
                int op = Integer.parseInt(input);
                switch (op) {
                    case 1:
                        if (user.getFavoritos().contains(s)) {
                            System.out.println("A série já está na lista de favoritos!");
                        } else {
                            user.getFavoritos().add(s);
                            System.out.println("Série adicionada aos favoritos.");
                        }
                        return;
                    case 2:
                        if (user.getAssistidas().contains(s)) {
                            System.out.println("A série já está na lista de assistidas!");
                        } else {
                            user.getAssistidas().add(s);
                            System.out.println("Série adicionada à lista de assistidas.");
                        }
                        return;
                    case 3:
                        if (user.getDesejaAssistir().contains(s)) {
                            System.out.println("A série já está na lista de deseja assistir!");
                        } else {
                            user.getDesejaAssistir().add(s);
                            System.out.println("Série adicionada à lista de deseja assistir.");
                        }
                        return;
                    case 0:
                        return;
                    default:
                        System.out.println("Opção inválida, tente novamente.");
                }
            } catch (Exception e) {
                System.out.println("Opção inválida, tente novamente.");
            }
        }
    }

    public void menuLista(Scanner sc, String tipo) {
        List<Serie> lista = obterLista(tipo);
        if (lista.isEmpty()) {
            System.out.println("Lista vazia.");
            return;
        }
        exibirLista(lista, sc);
        while (true) {
            System.out.println("1. Remover série");
            System.out.println("2. Ordenar lista");
            System.out.println("0. Voltar");
            System.out.print("Escolha: ");
            String input = sc.nextLine();
            try {
                int op = Integer.parseInt(input);
                switch (op) {
                    case 1:
                        System.out.print("Índice da série para remover: ");
                        String idxInput = sc.nextLine();
                        try {
                            int idx = Integer.parseInt(idxInput) - 1;
                            if (idx >= 0 && idx < lista.size()) {
                                lista.remove(idx);
                                System.out.println("Removido.");
                            } else {
                                System.out.println("Opção inválida, tente novamente.");
                            }
                        } catch (Exception e) {
                            System.out.println("Opção inválida, tente novamente.");
                        }
                        break;
                    case 2:
                        ordenarLista(sc, lista);
                        break;
                    case 0:
                        return;
                    default:
                        System.out.println("Opção inválida, tente novamente.");
                }
            } catch (Exception e) {
                System.out.println("Opção inválida, tente novamente.");
            }
        }
    }

    private List<Serie> obterLista(String tipo) {
        switch (tipo) {
            case "favoritos": return user.getFavoritos();
            case "assistidas": return user.getAssistidas();
            case "deseja": return user.getDesejaAssistir();
            default: return new ArrayList<>();
        }
    }

    // Alterado: exibe Nome, Nota, Ano de Estreia e Estado ao lado do nome em todas as listas
    private void exibirLista(List<Serie> lista, Scanner sc) {
        for (int i = 0; i < lista.size(); i++) {
            Serie s = lista.get(i);
            String anoEstreia = "";
            if (s.getDataEstreia() != null && !s.getDataEstreia().isEmpty()) {
                String[] partes = s.getDataEstreia().split("-");
                if (partes.length > 0) anoEstreia = partes[0];
            }
            System.out.printf(
                "%d. %s (Nota: %.1f", i + 1, s.getNome(), s.getNota()
            );
            if (!anoEstreia.isEmpty()) System.out.printf(" | Ano: %s", anoEstreia);
            if (s.getEstado() != null && !s.getEstado().isEmpty()) System.out.printf(" | Estado: %s", s.getEstado());
            System.out.println(")");
        }
        while (true) {
            System.out.print("Ver detalhes de qual série (0 para pular)? ");
            String input = sc.nextLine();
            try {
                int idx = Integer.parseInt(input) - 1;
                if (idx == -1) {
                    break;
                }
                if (idx >= 0 && idx < lista.size()) {
                    System.out.println(lista.get(idx));
                    break;
                } else {
                    System.out.println("Opção inválida, tente novamente.");
                }
            } catch (Exception e) {
                System.out.println("Opção inválida, tente novamente.");
            }
        }
    }

    private void ordenarLista(Scanner sc, List<Serie> lista) {
        while (true) {
            System.out.println("Ordenar por:");
            System.out.println("1. Nome (A-Z)");
            System.out.println("2. Nota geral");
            System.out.println("3. Estado");
            System.out.println("4. Data de estreia");
            System.out.print("Escolha: ");
            String input = sc.nextLine();
            try {
                int op = Integer.parseInt(input);
                switch (op) {
                    case 1:
                        lista.sort(Comparator.comparing(Serie::getNome));
                        System.out.println("Lista ordenada.");
                        return;
                    case 2:
                        lista.sort(Comparator.comparing(Serie::getNota).reversed());
                        System.out.println("Lista ordenada.");
                        return;
                    case 3:
                        lista.sort(Comparator.comparing(Serie::getEstado));
                        System.out.println("Lista ordenada.");
                        return;
                    case 4:
                        lista.sort(Comparator.comparing(Serie::getDataEstreia));
                        System.out.println("Lista ordenada.");
                        return;
                    default:
                        System.out.println("Opção inválida, tente novamente.");
                }
            } catch (Exception e) {
                System.out.println("Opção inválida, tente novamente.");
            }
        }
    }
}