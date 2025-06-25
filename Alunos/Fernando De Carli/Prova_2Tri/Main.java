import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Usuario usuario = Persistencia.carregarUsuario();

        if (usuario == null) {
            System.out.print("Digite seu nome ou apelido: ");
            String nome = sc.nextLine();
            usuario = new Usuario(nome);
        }

        while (true) {
            System.out.println("\nOlá, " + usuario.nome + "! Escolha uma opção:");
            System.out.println("1 - Buscar série");
            System.out.println("2 - Ver listas");
            System.out.println("3 - Ordenar listas");
            System.out.println("4 - Salvar e sair");
            System.out.println("5 - Remover série de uma lista");
            System.out.print(">> ");
            String opcao = sc.nextLine();

            if (opcao.equals("1")) {
                System.out.print("Nome da série: ");
                String nome = sc.nextLine();
                Serie serie = GerenciadorSeries.buscarSerie(nome);

                if (serie != null) {
                    System.out.println("\n" + serie.mostrarDados());
                    System.out.println("Adicionar à: [1] Favoritos [2] Assistidas [3] Desejadas [Enter] Ignorar");
                    String escolha = sc.nextLine();

                    if (escolha.equals("1")) usuario.favoritas.add(serie);
                    else if (escolha.equals("2")) usuario.assistidas.add(serie);
                    else if (escolha.equals("3")) usuario.desejadas.add(serie);
                } else {
                    System.out.println("Série não encontrada.");
                }

            } else if (opcao.equals("2")) {
                System.out.println("\n--- FAVORITAS ---");
                mostrarLista(usuario.favoritas);

                System.out.println("\n--- ASSISTIDAS ---");
                mostrarLista(usuario.assistidas);

                System.out.println("\n--- DESEJADAS ---");
                mostrarLista(usuario.desejadas);

            } else if (opcao.equals("3")) {
                System.out.println("Qual lista deseja ordenar? [1] Favoritas [2] Assistidas [3] Desejadas");
                String tipo = sc.nextLine();

                List<Serie> lista = null;
                if (tipo.equals("1")) lista = usuario.favoritas;
                else if (tipo.equals("2")) lista = usuario.assistidas;
                else if (tipo.equals("3")) lista = usuario.desejadas;

                if (lista != null) {
                    System.out.println("Ordenar por: [1] Nome [2] Nota [3] Status [4] Estreia");
                    String criterio = sc.nextLine();

                    if (criterio.equals("1")) lista.sort(Comparator.comparing(s -> s.nome));
                    else if (criterio.equals("2")) lista.sort(Comparator.comparingDouble(s -> -s.nota));
                    else if (criterio.equals("3")) lista.sort(Comparator.comparing(s -> s.status));
                    else if (criterio.equals("4")) lista.sort(Comparator.comparing(s -> s.estreia));

                    System.out.println("Lista ordenada.");
                }

            } else if (opcao.equals("4")) {
                Persistencia.salvarUsuario(usuario);
                System.out.println("Dados salvos. Até mais!");
                break;
            } else if (opcao.equals("5")) {
                System.out.println("De qual lista deseja remover?");
                System.out.println("[1] Favoritas [2] Assistidas [3] Desejadas");
                String tipo = sc.nextLine();

                List<Serie> lista = null;
                if (tipo.equals("1")) lista = usuario.favoritas;
                else if (tipo.equals("2")) lista = usuario.assistidas;
                else if (tipo.equals("3")) lista = usuario.desejadas;

                if (lista != null && lista.size() > 0) {
                    System.out.println("Escolha o número da série para remover:");
                    for (int i = 0; i < lista.size(); i++) {
                        System.out.println((i + 1) + " - " + lista.get(i).nome);
                    }

                    try {
                        int escolha = Integer.parseInt(sc.nextLine());
                        if (escolha >= 1 && escolha <= lista.size()) {
                            Serie removida = lista.remove(escolha - 1);
                            System.out.println("Série '" + removida.nome + "' removida da lista.");
                        } else {
                            System.out.println("Número inválido.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Digite um número válido.");
                    }
                } else {
                    System.out.println("Lista vazia ou inválida.");
                }
            }else {
                System.out.println("Opção inválida.");
            }

        }
    }

    public static void mostrarLista(List<Serie> lista) {
        if (lista.size() == 0) {
            System.out.println("Lista vazia.");
        } else {
            for (Serie s : lista) {
                System.out.println(s.mostrarDados());
            }
        }
    }
}