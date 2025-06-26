package model;

import service.TvMazeService;
import persistence.Persistencia;

import java.util.Scanner;

public class Sistema {
    private Usuario usuario;
    private final Scanner scanner = new Scanner(System.in);

    public void iniciar() {
        System.out.println("Digite seu nome ou apelido:");
        String nome = scanner.nextLine();

        usuario = Persistencia.carregarUsuario(nome);
        if (usuario == null) {
            usuario = new Usuario(nome);
        }

        boolean executando = true;
        while (executando) {
            System.out.println("\n=== MENU PRINCIPAL ===");
            System.out.println("1. Buscar serie");
            System.out.println("2. Ver listas");
            System.out.println("3. Remover serie de lista");
            System.out.println("4. Salvar e sair");

            String opcao = scanner.nextLine();
            switch (opcao) {
                case "1" -> buscarSerie();
                case "2" -> usuario.mostrarListas(scanner);
                case "3" -> usuario.removerSerie(scanner);
                case "4" -> {
                    Persistencia.salvarUsuario(usuario);
                    executando = false;
                }
                default -> System.out.println("Opcao invalida.");
            }
        }
    }

    private void buscarSerie() {
        System.out.println("Digite o nome da serie:");
        String nome = scanner.nextLine();
        Serie serie = TvMazeService.buscarSerie(nome);

        if (serie != null) {
            System.out.println("\n=== Detalhes da Serie ===");
            System.out.println(serie);
            usuario.menuSerie(scanner, serie);
        } else {
            System.out.println("❌ Serie nao encontrada.");
        }
    }
}