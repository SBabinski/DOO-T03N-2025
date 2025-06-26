package model;

import java.util.*;

public class Usuario {
    private String nome;
    private List<Serie> favoritos;
    private List<Serie> assistidas;
    private List<Serie> desejadas;

    public Usuario() {
        this.favoritos = new ArrayList<>();
        this.assistidas = new ArrayList<>();
        this.desejadas = new ArrayList<>();
    }

    public Usuario(String nome) {
        this();
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public List<Serie> getFavoritos() {
        return favoritos;
    }

    public List<Serie> getAssistidas() {
        return assistidas;
    }

    public List<Serie> getDesejadas() {
        return desejadas;
    }

    public void adicionarFavorito(Serie serie) {
        if (!favoritos.contains(serie)) {
            favoritos.add(serie);
            System.out.println("✅ Serie adicionada aos favoritos.");
        } else {
            System.out.println("⚠️ Serie ja está nos favoritos.");
        }
    }

    public void removerFavorito(Serie serie) {
        if (favoritos.remove(serie)) {
            System.out.println("✅ Serie removida dos favoritos.");
        } else {
            System.out.println("⚠️ Serie nao esta nos favoritos.");
        }
    }

    public void adicionarAssistida(Serie serie) {
        if (!assistidas.contains(serie)) {
            assistidas.add(serie);
            System.out.println("✅ Serie adicionada nas assistidas.");
        } else {
            System.out.println("⚠️ Serie ja esta nas assistidas.");
        }
    }

    public void removerAssistida(Serie serie) {
        if (assistidas.remove(serie)) {
            System.out.println("✅ Serie removida das assistidas.");
        } else {
            System.out.println("⚠️ Serie nao esta nas assistidas.");
        }
    }

    public void adicionarDesejada(Serie serie) {
        if (!desejadas.contains(serie)) {
            desejadas.add(serie);
            System.out.println("✅ Serie adicionada nas desejadas.");
        } else {
            System.out.println("⚠️ Serie ja esta nas desejadas.");
        }
    }

    public void removerDesejada(Serie serie) {
        if (desejadas.remove(serie)) {
            System.out.println("✅ Serie removida das desejadas.");
        } else {
            System.out.println("⚠️ Serie nao esta nas desejadas.");
        }
    }

    public void mostrarListas(Scanner scanner) {
        System.out.println("\nEscolha a lista para ver:");
        System.out.println("1 - Favoritos");
        System.out.println("2 - Assistidas");
        System.out.println("3 - Desejadas");
        String opcao = scanner.nextLine();

        List<Serie> listaEscolhida = null;

        if (opcao.equals("1")) {
            listaEscolhida = favoritos;
        } else if (opcao.equals("2")) {
            listaEscolhida = assistidas;
        } else if (opcao.equals("3")) {
            listaEscolhida = desejadas;
        } else {
            System.out.println("⚠️ Opcao invalida.");
            return;
        }

        if (listaEscolhida.isEmpty()) {
            System.out.println("📭 Lista vazia.");
            return;
        }

        ordenarLista(scanner, listaEscolhida);

        System.out.println("\n--- Lista ---");
        for (int i = 0; i < listaEscolhida.size(); i++) {
            System.out.println((i + 1) + ". " + listaEscolhida.get(i).getNome());
        }

        System.out.println("\nDigite o numero da serie para ver detalhes, ou 0 para voltar:");
        String input = scanner.nextLine();
        try {
            int escolha = Integer.parseInt(input);
            if (escolha == 0) return;
            if (escolha > 0 && escolha <= listaEscolhida.size()) {
                Serie serie = listaEscolhida.get(escolha - 1);
                System.out.println("\n" + serie);
                menuSerie(scanner, serie);
            } else {
                System.out.println("⚠️ Numero invalido.");
            }
        } catch (NumberFormatException e) {
            System.out.println("⚠️ Entrada invalida.");
        }
    }

    private void ordenarLista(Scanner scanner, List<Serie> lista) {
        System.out.println("\nEscolha a ordenacao:");
        System.out.println("1 - Ordem alfabetica (nome)");
        System.out.println("2 - Nota geral");
        System.out.println("3 - Estado da serie");
        System.out.println("4 - Data de estreia");
        String opcao = scanner.nextLine();

        switch (opcao) {
            case "1":
                lista.sort(Comparator.comparing(Serie::getNome, String.CASE_INSENSITIVE_ORDER));
                break;
            case "2":
                lista.sort(Comparator.comparingDouble(Serie::getNota).reversed());
                break;
            case "3":
                lista.sort(Comparator.comparing(Serie::getStatus, String.CASE_INSENSITIVE_ORDER));
                break;
            case "4":
                lista.sort(Comparator.comparing(Serie::getDataEstreia, Comparator.nullsLast(String::compareTo)));
                break;
            default:
                System.out.println("⚠️ Opcao invalida. Mantendo ordem original.");
        }
    }

    public void menuSerie(Scanner scanner, Serie serie) {
        boolean executando = true;
        while (executando) {
            System.out.println("\n🎬 Acoes para \"" + serie.getNome() + "\":");
            System.out.println("1 - Adicionar aos favoritos");
            System.out.println("2 - Remover dos favoritos");
            System.out.println("3 - Adicionar as assistidas");
            System.out.println("4 - Remover das assistidas");
            System.out.println("5 - Adicionar as desejadas");
            System.out.println("6 - Remover das desejadas");
            System.out.println("7 - Voltar");

            String opcao = scanner.nextLine();
            switch (opcao) {
                case "1":
                    adicionarFavorito(serie);
                    break;
                case "2":
                    removerFavorito(serie);
                    break;
                case "3":
                    adicionarAssistida(serie);
                    break;
                case "4":
                    removerAssistida(serie);
                    break;
                case "5":
                    adicionarDesejada(serie);
                    break;
                case "6":
                    removerDesejada(serie);
                    break;
                case "7":
                    executando = false;
                    break;
                default:
                    System.out.println("⚠️ Opcao invalida.");
            }
        }
    }

    public void removerSerie(Scanner scanner) {
        System.out.println("\nEscolha a lista para remover uma serie:");
        System.out.println("1 - Favoritos");
        System.out.println("2 - Assistidas");
        System.out.println("3 - Desejadas");
        String opcao = scanner.nextLine();

        List<Serie> lista = null;

        if (opcao.equals("1")) {
            lista = favoritos;
        } else if (opcao.equals("2")) {
            lista = assistidas;
        } else if (opcao.equals("3")) {
            lista = desejadas;
        } else {
            System.out.println("⚠️ Opcao invalida.");
            return;
        }

        if (lista.isEmpty()) {
            System.out.println("📭 Lista esta vazia.");
            return;
        }

        for (int i = 0; i < lista.size(); i++) {
            System.out.println((i + 1) + ". " + lista.get(i).getNome());
        }

        System.out.println("Digite o numero da serie que deseja remover:");
        try {
            int escolha = Integer.parseInt(scanner.nextLine());
            if (escolha > 0 && escolha <= lista.size()) {
                Serie removida = lista.remove(escolha - 1);
                System.out.println("✅ Serie \"" + removida.getNome() + "\" removida.");
            } else {
                System.out.println("⚠️ Numero invalido.");
            }
        } catch (NumberFormatException e) {
            System.out.println("⚠️ Entrada invalida.");
        }
    }
}
