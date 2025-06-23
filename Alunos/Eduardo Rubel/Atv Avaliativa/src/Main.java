import java.util.*;

public class Main {
    public static void main(String[] args) {
        Persistencia.inicializarSistema();

        Scanner sc = new Scanner(System.in);
        boolean sistemaRodando = true;

        while (sistemaRodando) {
            System.out.println("\n=== MENU PRINCIPAL ===");
            System.out.println("1 - Entrar com usuário existente");
            System.out.println("2 - Criar novo usuário");
            System.out.println("3 - Sair do aplicativo");
            System.out.print("Escolha: ");
            String op = sc.nextLine();

            Usuario usuario = null;

            switch (op) {
                case "1" -> {
                    List<String> usuarios = Persistencia.listarUsuarios();
                    if (usuarios.isEmpty()) {
                        System.out.println("Nenhum usuário encontrado.");
                        continue;
                    }

                    System.out.println("\n=== Usuários existentes ===");
                    for (int i = 0; i < usuarios.size(); i++) {
                        System.out.println((i + 1) + " - " + usuarios.get(i));
                    }
                    System.out.print("Escolha o número do usuário: ");
                    int escolha = Integer.parseInt(sc.nextLine());

                    if (escolha > 0 && escolha <= usuarios.size()) {
                        String nome = usuarios.get(escolha - 1);
                        usuario = Persistencia.carregarUsuario(nome);
                        if (usuario == null) {
                            usuario = new Usuario(nome);
                        }
                    } else {
                        System.out.println("Opção inválida.");
                        continue;
                    }
                }

                case "2" -> {
                    System.out.print("Digite o nome do novo usuário: ");
                    String nome = sc.nextLine();
                    usuario = new Usuario(nome);
                }

                case "3" -> {
                    System.out.println("Saindo do aplicativo...");
                    sistemaRodando = false;
                    continue;
                }

                default -> {
                    System.out.println("Opção inválida.");
                    continue;
                }
            }

            menuUsuario(usuario, sc);
        }
        sc.close();
    }

    public static void menuUsuario(Usuario usuario, Scanner sc) {
        boolean rodando = true;
        while (rodando) {
            System.out.println("\n=== Menu de " + usuario.getNome() + " ===");
            System.out.println("1 - Buscar série");
            System.out.println("2 - Ver listas");
            System.out.println("3 - Remover série das listas");
            System.out.println("4 - Salvar e sair para o menu principal");
            System.out.print("Escolha: ");
            String op = sc.nextLine();

            try {
                switch (op) {
                    case "1":
                        System.out.print("Digite o nome da série: ");
                        String busca = sc.nextLine();
                        List<Serie> resultados = ServicoAPI.buscarSeries(busca);

                        if (resultados.isEmpty()) {
                            System.out.println("Nenhuma série encontrada.");
                        } else {
                            int i = 1;
                            for (Serie s : resultados) {
                                System.out.println("\n=== [" + i + "] ===");
                                System.out.println(s);
                                i++;
                            }
                            System.out.print("\nEscolha o número para adicionar (ou 0 pra voltar): ");
                            int escolha = Integer.parseInt(sc.nextLine());
                            if (escolha > 0 && escolha <= resultados.size()) {
                                Serie serie = resultados.get(escolha - 1);

                                System.out.println("Adicionar em:");
                                System.out.println("1 - Favoritos");
                                System.out.println("2 - Assistidas");
                                System.out.println("3 - Para assistir");
                                System.out.print("Escolha: ");
                                String lista = sc.nextLine();

                                switch (lista) {
                                    case "1" -> usuario.adicionar(usuario.getFavoritos(), serie);
                                    case "2" -> usuario.adicionar(usuario.getAssistidas(), serie);
                                    case "3" -> usuario.adicionar(usuario.getParaAssistir(), serie);
                                    default -> System.out.println("Opção inválida.");
                                }
                            }
                        }
                        break;

                    case "2":
                        menuVerListas(usuario, sc);
                        break;

                    case "3":
                        menuRemover(usuario, sc);
                        break;

                    case "4":
                        Persistencia.salvar(usuario);
                        rodando = false;
                        break;

                    default:
                        System.out.println("Opção inválida.");
                }
            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }
    }

    public static void menuVerListas(Usuario usuario, Scanner sc) {
        System.out.println("\nVer qual lista?");
        System.out.println("1 - Favoritos");
        System.out.println("2 - Assistidas");
        System.out.println("3 - Para assistir");
        System.out.print("Escolha: ");
        String lista = sc.nextLine();

        List<Serie> selecionada = switch (lista) {
            case "1" -> usuario.getFavoritos();
            case "2" -> usuario.getAssistidas();
            case "3" -> usuario.getParaAssistir();
            default -> null;
        };

        if (selecionada != null) {
            if (selecionada.isEmpty()) {
                System.out.println("Lista vazia.");
            } else {
                menuOrdenar(selecionada, sc);

                int i = 1;
                for (Serie s : selecionada) {
                    System.out.println("\n=== [" + i + "] ===");
                    System.out.println(s);
                    i++;
                }
            }
        } else {
            System.out.println("Opção inválida.");
        }
    }

    public static void menuRemover(Usuario usuario, Scanner sc) {
        System.out.println("\nRemover de qual lista?");
        System.out.println("1 - Favoritos");
        System.out.println("2 - Assistidas");
        System.out.println("3 - Para assistir");
        System.out.print("Escolha: ");
        String lista = sc.nextLine();

        List<Serie> selecionada = switch (lista) {
            case "1" -> usuario.getFavoritos();
            case "2" -> usuario.getAssistidas();
            case "3" -> usuario.getParaAssistir();
            default -> null;
        };

        if (selecionada != null) {
            if (selecionada.isEmpty()) {
                System.out.println("Lista vazia.");
                return;
            }

            for (int i = 0; i < selecionada.size(); i++) {
                System.out.println("[" + (i + 1) + "] " + selecionada.get(i).getNome());
            }
            System.out.print("Escolha o número para remover (ou 0 pra voltar): ");
            int escolha = Integer.parseInt(sc.nextLine());

            if (escolha > 0 && escolha <= selecionada.size()) {
                Serie removida = selecionada.remove(escolha - 1);
                System.out.println("Removido: " + removida.getNome());
            } else if (escolha == 0) {
                System.out.println("Voltando.");
            } else {
                System.out.println("Opção inválida.");
            }
        } else {
            System.out.println("Opção inválida.");
        }
    }

    public static void menuOrdenar(List<Serie> lista, Scanner sc) {
        if (lista.isEmpty()) return;

        System.out.println("\nDeseja ordenar a lista?");
        System.out.println("1 - Por nome (A-Z)");
        System.out.println("2 - Por status");
        System.out.println("3 - Por data de estreia");
        System.out.println("4 - Sem ordenação");
        System.out.print("Escolha: ");
        String op = sc.nextLine();

        switch (op) {
            case "1" -> lista.sort(Comparator.comparing(Serie::getNome));
            case "2" -> lista.sort(Comparator.comparing(Serie::getStatus));
            case "3" -> lista.sort(Comparator.comparing(Serie::getDataEstreia));
            case "4" -> {} // Sem ordenação
            default -> System.out.println("Opção inválida.");
        }
    }
}