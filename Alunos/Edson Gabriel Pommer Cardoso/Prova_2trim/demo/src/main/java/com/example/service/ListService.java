//Exibe, remove, move e ordena séries em listas específicas
//Recebe o objeto DadosUsuario e manipula suas listas


package com.example.service;

import com.example.model.DadosUsuario;
import com.example.model.Serie;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class ListService {
    private final Scanner scanner;

    public ListService(Scanner scanner) {
        this.scanner = scanner;
    }

    public void exibir(DadosUsuario dados) {
        while (true) {
            try {
                System.out.println("Qual lista deseja exibir?");
                System.out.println("[1] - Séries Assistidas");
                System.out.println("[2] - Séries Favoritas");
                System.out.println("[3] - Séries que Deseja Assistir");
                System.out.println("[0] - Voltar ao menu inicial");
                int escolha = Integer.parseInt(scanner.nextLine());
                if (escolha == 0) return;

                List<Serie> lista = switch (escolha) {
                    case 1 -> dados.getAssistidos();
                    case 2 -> dados.getFavoritos();
                    case 3 -> dados.getDesejo();
                    default -> null;
                };

                if (lista == null) {
                    System.out.println("Opção inválida.");
                    continue;
                }
                if (lista.isEmpty()) {
                    System.out.println("Lista vazia.");
                } else {
                    for (Serie serie : lista) {
                        System.out.println(serie);
                    }
                }
                System.out.println("Pressione ENTER para voltar ao menu.");
                scanner.nextLine();
                return;
            } catch (NumberFormatException e) {
                System.out.println("Digite um número válido.");
            } catch (Exception e) {
                System.out.println("Erro ao exibir lista: " + e.getMessage());
            }
        }
    }

    public void remover(DadosUsuario dados) {
        while (true) {
            try {
                System.out.println("Qual lista deseja remover?");
                System.out.println("[1] - Séries Assistidas");
                System.out.println("[2] - Séries Favoritas");
                System.out.println("[3] - Séries que Deseja Assistir");
                System.out.println("[0] - Voltar ao menu inicial");
                int escolha = Integer.parseInt(scanner.nextLine());
                if (escolha == 0) return;

                List<Serie> lista = switch (escolha) {
                    case 1 -> dados.getAssistidos();
                    case 2 -> dados.getFavoritos();
                    case 3 -> dados.getDesejo();
                    default -> null;
                };

                if (lista == null) {
                    System.out.println("Opção inválida.");
                    continue;
                }
                if (lista.isEmpty()) {
                    System.out.println("Lista vazia.");
                    continue;
                }

                System.out.println("Séries na lista:");
                for (Serie serie : lista) {
                    System.out.println(serie.getNome());
                }

                System.out.println("Digite o nome da série a ser removida ou 0 para voltar:");
                String nomeSerie = scanner.nextLine();
                if (nomeSerie.equals("0")) return;

                boolean removido = lista.removeIf(s -> s.getNome().equalsIgnoreCase(nomeSerie));
                System.out.println(removido ? "Série removida." : "Série não encontrada.");
                System.out.println("Pressione ENTER para voltar ao menu.");
                scanner.nextLine();
                return;
            } catch (Exception e) {
                System.out.println("Erro ao remover série: " + e.getMessage());
            }
        }
    }

    public void mover(DadosUsuario dados) {
        while (true) {
            try {
                System.out.println("De qual lista deseja mover uma série?");
                System.out.println("[1] - Séries Assistidas");
                System.out.println("[2] - Séries Favoritas");
                System.out.println("[3] - Séries que Deseja Assistir");
                System.out.println("[0] - Voltar ao menu inicial");
                int origem = Integer.parseInt(scanner.nextLine());
                if (origem == 0) return;

                List<Serie> listaOrigem = switch (origem) {
                    case 1 -> dados.getAssistidos();
                    case 2 -> dados.getFavoritos();
                    case 3 -> dados.getDesejo();
                    default -> null;
                };

                if (listaOrigem == null || listaOrigem.isEmpty()) {
                    System.out.println("Lista inválida ou vazia.");
                    continue;
                }

                System.out.println("Séries na lista de origem:");
                for (Serie serie : listaOrigem) {
                    System.out.println(serie.getNome());
                }

                System.out.println("Digite o nome da série que deseja mover ou 0 para voltar:");
                String nomeSerie = scanner.nextLine();
                if (nomeSerie.equals("0")) return;

                Serie serieParaMover = listaOrigem.stream()
                    .filter(s -> s.getNome().equalsIgnoreCase(nomeSerie))
                    .findFirst().orElse(null);

                if (serieParaMover == null) {
                    System.out.println("Série não encontrada na lista de origem.");
                    continue;
                }

                System.out.println("Para qual lista deseja mover?");
                System.out.println("[1] - Séries Assistidas");
                System.out.println("[2] - Séries Favoritas");
                System.out.println("[3] - Séries que Deseja Assistir");
                System.out.println("[0] - Voltar ao menu inicial");
                int destino = Integer.parseInt(scanner.nextLine());
                if (destino == 0) return;
                if (destino == origem) {
                    System.out.println("A lista de destino deve ser diferente da origem.");
                    continue;
                }

                List<Serie> listaDestino = switch (destino) {
                    case 1 -> dados.getAssistidos();
                    case 2 -> dados.getFavoritos();
                    case 3 -> dados.getDesejo();
                    default -> null;
                };

                if (listaDestino == null) {
                    System.out.println("Lista de destino inválida.");
                    continue;
                }
                if (listaDestino.contains(serieParaMover)) {
                    System.out.println("Série já está na lista de destino.");
                    continue;
                }

                listaOrigem.remove(serieParaMover);
                listaDestino.add(serieParaMover);
                System.out.println("Série movida com sucesso!");
                System.out.println("Pressione ENTER para voltar ao menu.");
                scanner.nextLine();
                return;
            } catch (NumberFormatException e) {
                System.out.println("Digite um número válido.");
            } catch (Exception e) {
                System.out.println("Erro ao mover série: " + e.getMessage());
            }
        }
    }

    public void ordenar(DadosUsuario dados) {
        while (true) {
            try {
                System.out.println("Qual lista deseja ordenar?");
                System.out.println("[1] - Séries Assistidas");
                System.out.println("[2] - Séries Favoritas");
                System.out.println("[3] - Séries que Deseja Assistir");
                System.out.println("[0] - Voltar ao menu inicial");
                int escolha = Integer.parseInt(scanner.nextLine());
                if (escolha == 0) return;

                List<Serie> lista = switch (escolha) {
                    case 1 -> dados.getAssistidos();
                    case 2 -> dados.getFavoritos();
                    case 3 -> dados.getDesejo();
                    default -> null;
                };

                if (lista == null || lista.isEmpty()) {
                    System.out.println("Lista inválida ou vazia.");
                    continue;
                }

                System.out.println("Ordenar por:");
                System.out.println("[1] - Nome (A-Z)");
                System.out.println("[2] - Nota geral");
                System.out.println("[3] - Estado da série");
                System.out.println("[4] - Data de estreia");
                System.out.println("[0] - Voltar ao menu inicial");
                int criterio = Integer.parseInt(scanner.nextLine());
                if (criterio == 0) return;

                switch (criterio) {
                    case 1 ->
                        lista.sort(Comparator.comparing(Serie::getNome, String.CASE_INSENSITIVE_ORDER));
                    case 2 ->
                        lista.sort(Comparator.comparingDouble(Serie::getNota).reversed());
                    case 3 ->
                        lista.sort(Comparator.comparing(Serie::getEstado, String.CASE_INSENSITIVE_ORDER));
                    case 4 ->
                        lista.sort(Comparator.comparing(Serie::getDataEstreia, String.CASE_INSENSITIVE_ORDER));
                    default -> {
                        System.out.println("Critério inválido.");
                        continue;
                    }
                }

                System.out.println("Lista ordenada!");
                for (Serie serie : lista) {
                    System.out.println(serie);
                }
                System.out.println("Pressione ENTER para voltar ao menu.");
                scanner.nextLine();
                return;
            } catch (NumberFormatException e) {
                System.out.println("Digite um número válido.");
            } catch (Exception e) {
                System.out.println("Erro ao ordenar/exibir lista: " + e.getMessage());
            }
        }
    }
}
