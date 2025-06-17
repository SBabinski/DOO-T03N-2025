package com.example;

import java.util.*;

public class SistemaSeries {
    private Scanner scanner = new Scanner(System.in);
    private Usuario usuario;

    public void iniciar() {
        carregarUsuario();
        menuPrincipal();
    }

    private void carregarUsuario() {
        usuario = Persistencia.carregar();
        if (usuario == null) {
            System.out.print("Digite seu apelido: ");
            String apelido = scanner.nextLine();
            usuario = new Usuario(apelido);
        }
    }

    private void menuPrincipal() {
        while (true) {
            System.out.println("\n--- Sistema de Séries ---");
            System.out.println("1. Procurar série");
            System.out.println("2. Minhas listas");
            System.out.println("3. Sair");
            System.out.print("Escolha: ");
            
            try {
                int opcao = Integer.parseInt(scanner.nextLine());
                
                switch (opcao) {
                    case 1 -> procurarSerie();
                    case 2 -> exibirListas();
                    case 3 -> {
                        Persistencia.salvar(usuario);
                        return;
                    }
                    default -> System.out.println("Opção inválida!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida! Digite um número.");
            }
        }
    }

    private void procurarSerie() {
        System.out.print("\nDigite o nome da série: ");
        String nome = scanner.nextLine();
        
        List<Serie> resultados = TVMazeAPI.buscarSeries(nome);
        
        if (resultados.isEmpty()) {
            System.out.println("Nenhuma série encontrada.");
            return;
        }
        
        System.out.println("\nResultados:");
        for (int i = 0; i < resultados.size(); i++) {
            System.out.println((i + 1) + ". " + resultados.get(i).getName());
        }
        
        System.out.print("\nSelecione uma série (0 para voltar): ");
        try {
            int escolha = Integer.parseInt(scanner.nextLine());
            if (escolha > 0 && escolha <= resultados.size()) {
                Serie selecionada = resultados.get(escolha - 1);
                exibirDetalhesSerie(selecionada);
            }
        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida!");
        }
    }

    private void exibirDetalhesSerie(Serie serie) {
        System.out.println("\n" + serie);
        
        System.out.println("\nAções:");
        System.out.println("1. Adicionar aos Favoritos");
        System.out.println("2. Adicionar às Assistidas");
        System.out.println("3. Adicionar à Lista 'Quero Assistir'");
        System.out.println("4. Voltar");
        System.out.print("Escolha: ");
        
        try {
            int opcao = Integer.parseInt(scanner.nextLine());
            switch (opcao) {
                case 1 -> usuario.adicionarFavorita(serie);
                case 2 -> usuario.adicionarAssistida(serie);
                case 3 -> usuario.adicionarParaAssistir(serie);
            }
        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida!");
        }
    }

    private void exibirListas() {
        System.out.println("\n--- Minhas Listas ---");
        System.out.println("1. Favoritas");
        System.out.println("2. Assistidas");
        System.out.println("3. Quero Assistir");
        System.out.println("4. Voltar");
        System.out.print("Escolha: ");
        
        try {
            int opcao = Integer.parseInt(scanner.nextLine());
            List<Serie> listaSelecionada = null;
            String nomeLista = "";
            
            switch (opcao) {
                case 1:
                    listaSelecionada = usuario.getFavoritas();
                    nomeLista = "Favoritas";
                    break;
                case 2:
                    listaSelecionada = usuario.getAssistidas();
                    nomeLista = "Assistidas";
                    break;
                case 3:
                    listaSelecionada = usuario.getParaAssistir();
                    nomeLista = "Quero Assistir";
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Opção inválida!");
                    return;
            }
            
            if (listaSelecionada.isEmpty()) {
                System.out.println("\nLista vazia!");
                return;
            }
            
            menuOrdenacao(listaSelecionada, nomeLista);
        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida!");
        }
    }

    private void menuOrdenacao(List<Serie> lista, String nomeLista) {
        while (true) {
            System.out.println("\n--- " + nomeLista + " ---");
            System.out.println("1. Ordenar por Nome");
            System.out.println("2. Ordenar por Nota");
            System.out.println("3. Ordenar por Status");
            System.out.println("4. Ordenar por Data de Estreia");
            System.out.println("5. Remover item");
            System.out.println("6. Voltar");
            System.out.print("Escolha: ");
            
            try {
                int opcao = Integer.parseInt(scanner.nextLine());
                List<Serie> listaOrdenada = new ArrayList<>(lista);
                
                switch (opcao) {
                    case 1:
                        Collections.sort(listaOrdenada);
                        break;
                    case 2:
                        listaOrdenada.sort(Comparator.comparingDouble(Serie::getRating).reversed());
                        break;
                    case 3:
                        listaOrdenada.sort(Comparator.comparing(Serie::getStatus));
                        break;
                    case 4:
                        listaOrdenada.sort(Comparator.comparing(
                            s -> s.getPremiered() != null ? s.getPremiered() : "9999-99-99"));
                        break;
                    case 5:
                        removerDaLista(lista, nomeLista);
                        return;
                    case 6:
                        return;
                    default:
                        System.out.println("Opção inválida!");
                        continue;
                }
                
                exibirListaOrdenada(listaOrdenada, nomeLista);
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida!");
            }
        }
    }

    private void exibirListaOrdenada(List<Serie> lista, String nomeLista) {
        System.out.println("\n--- " + nomeLista + " Ordenadas ---");
        for (int i = 0; i < lista.size(); i++) {
            System.out.println((i + 1) + ". " + lista.get(i).getName());
        }
    }

    private void removerDaLista(List<Serie> lista, String nomeLista) {
        exibirListaOrdenada(lista, nomeLista);
        System.out.print("\nSelecione o item para remover (0 para cancelar): ");
        
        try {
            int escolha = Integer.parseInt(scanner.nextLine());
            if (escolha > 0 && escolha <= lista.size()) {
                Serie serie = lista.get(escolha - 1);
                
                if (nomeLista.equals("Favoritas")) usuario.removerFavorita(serie);
                else if (nomeLista.equals("Assistidas")) usuario.removerAssistida(serie);
                else if (nomeLista.equals("Quero Assistir")) usuario.removerParaAssistir(serie);
                
                System.out.println("Série removida!");
            }
        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida!");
        }
    }
}