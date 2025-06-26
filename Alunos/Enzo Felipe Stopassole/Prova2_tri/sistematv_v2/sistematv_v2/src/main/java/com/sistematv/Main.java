package com.sistematv;

import java.util.List;
import java.util.Scanner;

import com.sistematv.api.TvMazeAPI;
import com.sistematv.model.Serie;
import com.sistematv.model.Usuario;
import com.sistematv.storage.PersistenciaJson;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Usuario usuario;

        try {
            usuario = PersistenciaJson.carregar();
            if (usuario == null) {
                System.out.print("Digite seu nome: ");
                String nomeUsuario = scanner.nextLine();
                usuario = new Usuario(nomeUsuario);
            } else {
                System.out.println("Bem-vindo de volta, " + usuario.getNome() + "!");
            }

            TvMazeAPI api = new TvMazeAPI();
            boolean rodando = true;

            while (rodando) {
                System.out.println("\n1. Buscar série\n2. Ver listas\n3. Salvar e sair");
                int opc;
                while (true) {
                    System.out.print("Escolha uma opção: ");
                    String input = scanner.nextLine();
                    try {
                        opc = Integer.parseInt(input);
                        break;
                    } catch (NumberFormatException e) {
                        System.out.println("Por favor, digite um número válido.");
                    }
                }

                switch (opc) {
                    case 1 -> {
                        System.out.print("Nome da série: ");
                        String nome = scanner.nextLine();
                        System.out.print("Ordenar por: 1. Nome | 2. Nota | 3. Status | 4. Data de estreia | Outro. Nenhum");
                        int ordem;

                        while (true) {
                            System.out.print("Escolha uma opção: ");
                            String input = scanner.nextLine();
                            try {
                                ordem = Integer.parseInt(input);
                                break;
                            } catch (NumberFormatException e) {
                                System.out.println("Por favor, digite um número válido.");
                            }
                        }

                        List<Serie> resultados = api.buscarSeries(nome, ordem);
                        for (int i = 0; i < resultados.size(); i++) {
                            System.out.println("(" + i + ") " + resultados.get(i).getNome());
                        }
                        System.out.print("Escolha uma (número): ");
                        int idx = scanner.nextInt();
                        scanner.nextLine();
                        Serie serie = resultados.get(idx);
                        System.out.println("\n" + serie + "\n");
                        System.out.println("1. Favorito | 2. Assistida | 3. Deseja assistir | Outro. Voltar");
                        int esc;
                        while (true) {
                            try {
                                esc = Integer.parseInt(scanner.nextLine());
                                scanner.nextLine();
                                break;
                            } catch (NumberFormatException ex) {
                                System.out.print("Por favor, digite um número válido: ");
                            }
                        }
                        if (esc == 1) {
                            usuario.adicionar(usuario.getFavoritos(), serie);

                        } else if (esc == 2) {
                            usuario.adicionar(usuario.getAssistidas(), serie);
                        } else if (esc == 3) {
                            usuario.adicionar(usuario.getDesejoAssistir(), serie);
                        }
                    }
                    case 2 -> {
                        System.out.print("1. Favoritas\n2. Assistidas\n3. Desejo Assistir\n");
                        int escolha = scanner.nextInt();
                        scanner.nextLine();
                        List<Serie> lista = switch (escolha) {
                            case 1 ->
                                usuario.getFavoritos();
                            case 2 ->
                                usuario.getAssistidas();
                            case 3 ->
                                usuario.getDesejoAssistir();
                            default ->
                                null;
                        };
                        if (lista != null) {
                            lista.forEach(System.out::println);
                        }

                        if (true) {
                            System.out.println("\nDeseja remover algum registro? (s/n)");
                            if (scanner.nextLine().equalsIgnoreCase("s")) {
                                System.out.print("Digite o id do registro para remocao: ");
                                int id = Integer.parseInt(scanner.nextLine());
                                boolean removido = lista.removeIf(serie -> serie.getId() == id);
                                while (!removido) {
                                    System.out.println("ID não encontrado. Por favor, digite um ID válido para remoção ou digite 'v' para voltar: ");
                                    String entrada = scanner.nextLine();
                                    if (entrada.equalsIgnoreCase("v")) {
                                        System.out.println("Voltando ao menu anterior.");
                                        break;
                                    }
                                    try {
                                        int idAux = Integer.parseInt(entrada);
                                        removido = lista.removeIf(serie -> serie.getId() == idAux);
                                    } catch (NumberFormatException ex) {
                                        System.out.println("Por favor, digite um número de ID válido ou 'v' para voltar.");
                                    }
                                }
                                System.out.println("Removido.");
                            }
                        }
                    }
                    case 3 -> {
                        PersistenciaJson.salvar(usuario);
                        rodando = false;
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}
