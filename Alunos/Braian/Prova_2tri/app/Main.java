package com.braian.seriestracker.app;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import com.braian.seriestracker.api.TvMazeAPI;
import com.braian.seriestracker.model.Serie;
import com.braian.seriestracker.model.Usuario;
import com.braian.seriestracker.repository.UsuarioRepository;
import com.braian.seriestracker.service.GerenciadorDeSeries;

public class Main {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            TvMazeAPI api = new TvMazeAPI();
            UsuarioRepository repository = new UsuarioRepository();
            GerenciadorDeSeries gerenciador = new GerenciadorDeSeries();

            Usuario usuario = repository.carregar();
            if (usuario == null) {
                System.out.print("Digite seu nome ou apelido: ");
                String nome = scanner.nextLine();
                usuario = new Usuario(nome);
            }

            int opcao = -1;

            while (opcao != 9) {
                System.out.println("\nOlá, " + usuario.getNome());
                System.out.println("0 - Procurar uma série");
                System.out.println("1 - Favoritar ou desfavoritar série");
                System.out.println("2 - Adicionar série na lista de assistidas");
                System.out.println("3 - Adicionar série na lista de desejadas (assistirei)");
                System.out.println("4 - Exibir listas");
                System.out.println("5 - Ordenar listas por ordem alfabética");
                System.out.println("6 - Ordenar listas por status");
                System.out.println("7 - Ordenar listas por nota");
                System.out.println("8 - Ordenar listas por data de estreia");
                System.out.println("9 - Sair");
                System.out.println("10 - Salvar listas");
                System.out.println("11 - Carregar listas");

                opcao = lerInt(scanner, "Escolha a opção: ", 0, 11); // Adicionado min e max para validação

                switch (opcao) {
                    case 0 -> buscarSerie(scanner, api);
                    case 1 -> favoritarOuDesfavoritar(scanner, usuario);
                    case 2 -> adicionarSerieLista(scanner, api, usuario, "assistidas"); // Passa o objeto usuario
                    case 3 -> adicionarSerieLista(scanner, api, usuario, "desejadas"); // Passa o objeto usuario
                    case 4 -> exibirListas(gerenciador, usuario);
                    case 5 -> ordenarListasPorNome(gerenciador, usuario);
                    case 6 -> ordenarListasPorStatus(gerenciador, usuario);
                    case 7 -> ordenarListasPorNota(gerenciador, usuario);
                    case 8 -> ordenarListasPorData(gerenciador, usuario);
                    case 9 -> {
                        repository.salvar(usuario);
                        System.out.println("Dados salvos. Saindo...");
                    }
                    case 10 -> {
                        repository.salvar(usuario);
                        System.out.println("Listas salvas com sucesso.");
                    }
                    case 11 -> {
                        Usuario carregado = repository.carregar();
                        if (carregado != null) {
                            usuario = carregado;
                            System.out.println("Listas carregadas com sucesso.");
                        } else {
                            System.out.println("Nenhum dado salvo encontrado.");
                        }
                    }
                    default -> System.out.println("Opção inválida.");
                }
            }
        }
    }

    private static void buscarSerie(Scanner scanner, TvMazeAPI api) {
        System.out.print("Digite o nome da série para buscar: ");
        String nomeBusca = scanner.nextLine();
        // Sanitiza a entrada do usuário para remover caracteres especiais
        String nomeBuscaSanitizada = nomeBusca.replaceAll("[^a-zA-Z0-9 ]", "");
        try {
            List<Serie> resultados = api.buscarSerie(nomeBuscaSanitizada);
            if (resultados.isEmpty()) {
                System.out.println("Nenhuma série encontrada.");
            } else {
                System.out.println("Séries encontradas:");
                for (int i = 0; i < resultados.size(); i++) {
                    System.out.println("[" + i + "] " + resultados.get(i).getTitulo());
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao buscar série: " + e.getMessage());
        }
    }

    private static void favoritarOuDesfavoritar(Scanner scanner, Usuario usuario) {
        System.out.print("Digite o nome da série para favoritar/desfavoritar: ");
        String nome = scanner.nextLine();
        String nomeSanitizado = nome.replaceAll("[^a-zA-Z0-9 ]", "");

        // Busca a série na lista de favoritos do usuário
        Serie serieExistente = usuario.getFavoritos().stream()
                .filter(s -> s.getTitulo().equalsIgnoreCase(nomeSanitizado))
                .findFirst()
                .orElse(null);

        if (serieExistente != null) {
            usuario.removeFavorito(serieExistente);
            System.out.println("Série removida dos favoritos.");
        } else {
            // Tentando buscar a série para adicionar aos favoritos
            try {
                List<Serie> resultados = new TvMazeAPI().buscarSerie(nomeSanitizado);
                if (!resultados.isEmpty()) {
                    Serie serieParaFavoritar = resultados.get(0);
                    usuario.addFavorito(serieParaFavoritar);
                    System.out.println("Série adicionada aos favoritos: " + serieParaFavoritar.getTitulo());
                } else {
                    System.out.println("Série não encontrada para favoritar.");
                }
            } catch (IOException e) {
                System.out.println("Erro ao buscar série para favoritar: " + e.getMessage());
            }
        }
    }

    private static void adicionarSerieLista(Scanner scanner, TvMazeAPI api, Usuario usuario, String nomeLista) {
        System.out.print("Digite o nome da série para adicionar na lista de " + nomeLista + ": ");
        String nome = scanner.nextLine();
        String nomeSanitizado = nome.replaceAll("[^a-zA-Z0-9 ]", "");
        try {
            List<Serie> resultados = api.buscarSerie(nomeSanitizado);
            if (!resultados.isEmpty()) {
                Serie serie = resultados.get(0);
                // Adiciona a série usando os métodos encapsulados do Usuario
                if (nomeLista.equals("assistidas")) {
                    usuario.addAssistida(serie);
                } else if (nomeLista.equals("desejadas")) {
                    usuario.addDesejada(serie);
                }
                System.out.println("Série adicionada na lista de " + nomeLista + ": " + serie.getTitulo());
            } else {
                System.out.println("Série não encontrada.");
            }
        } catch (IOException e) {
            System.out.println("Erro ao buscar série: " + e.getMessage());
        }
    }

    private static void exibirListas(GerenciadorDeSeries gerenciador, Usuario usuario) {
        System.out.println("\n--- Favoritos ---");
        gerenciador.exibir(usuario.getFavoritos());

        System.out.println("\n--- Assistidas ---");
        gerenciador.exibir(usuario.getAssistidas());

        System.out.println("\n--- Desejadas ---");
        gerenciador.exibir(usuario.getDesejadas());
    }

    private static void ordenarListasPorNome(GerenciadorDeSeries gerenciador, Usuario usuario) {
        gerenciador.ordenarPorNome(usuario.getModifiableFavoritos());
        gerenciador.ordenarPorNome(usuario.getModifiableAssistidas());
        gerenciador.ordenarPorNome(usuario.getModifiableDesejadas());
        System.out.println("Listas ordenadas por ordem alfabética.");
    }

    private static void ordenarListasPorStatus(GerenciadorDeSeries gerenciador, Usuario usuario) {
        gerenciador.ordenarPorStatus(usuario.getModifiableFavoritos());
        gerenciador.ordenarPorStatus(usuario.getModifiableAssistidas());
        gerenciador.ordenarPorStatus(usuario.getModifiableDesejadas());
        System.out.println("Listas ordenadas por status.");
    }

    private static void ordenarListasPorNota(GerenciadorDeSeries gerenciador, Usuario usuario) {
        gerenciador.ordenarPorNota(usuario.getModifiableFavoritos());
        gerenciador.ordenarPorNota(usuario.getModifiableAssistidas());
        gerenciador.ordenarPorNota(usuario.getModifiableDesejadas());
        System.out.println("Listas ordenadas por nota.");
    }

    private static void ordenarListasPorData(GerenciadorDeSeries gerenciador, Usuario usuario) {
        gerenciador.ordenarPorDataEstreia(usuario.getModifiableFavoritos());
        gerenciador.ordenarPorDataEstreia(usuario.getModifiableAssistidas());
        gerenciador.ordenarPorDataEstreia(usuario.getModifiableDesejadas());
        System.out.println("Listas ordenadas por data de estreia.");
    }

    private static int lerInt(Scanner scanner, String mensagem, int min, int max) {
        while (true) {
            System.out.print(mensagem);
            String input = scanner.nextLine();
            if (input.matches("\\d+")) {
                int numero = Integer.parseInt(input);
                if (numero >= min && numero <= max) {
                    return numero;
                } else {
                    System.out.println("Por favor, digite um número entre " + min + " e " + max + ".");
                }
            } else {
                System.out.println("Por favor, digite um número válido.");
            }
        }
    }
}

