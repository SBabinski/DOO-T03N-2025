import java.util.Scanner;

// Classe que gerencia o gerenciamento de séries

public class GerenciamentoSeries {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Usuario usuario = null;

        try { // Tenta carregar o usuário do arquivo JSON
            System.out.println("Gerenciador de Séries!");
            usuario = JsonManager.carregarUsuario();

            if (usuario == null) {
                System.out.print("Digite seu nome/apelido: ");
                String nome = scanner.nextLine();
                usuario = new Usuario(nome);
            } else {
                System.out.println("Bem-vindo de volta, " + usuario.getNome() + "!");
            }

            int opcao = -1;

            while (opcao != 0) { 
                System.out.println("\n--- MENU ---");
                System.out.println("1. Buscar série");
                System.out.println("2. Ver listas");
                System.out.println("3. Adicionar série à lista");
                System.out.println("4. Remover série da lista");
                System.out.println("5. Ordenar listas");
                System.out.println("0. Sair\n");
                System.out.print("Escolha: \n");

                try { // Lê a opção do usuário
                    opcao = Integer.parseInt(scanner.nextLine());

                    switch (opcao) {
                        case 1 -> {
                            System.out.print("Digite o nome da série: ");
                            String nomeSerie = scanner.nextLine();
                            Serie serie = SerieService.buscarSerie(nomeSerie);
                            if (serie != null) {
                                System.out.println("Série encontrada:");
                                System.out.println(serie);
                            } else {
                                System.out.println("Série não encontrada.");
                            }
                        }

                        case 2 -> usuario.exibirTodasAsListas(); // Exibe todas as listas do usuário

                        case 3 -> { // Adiciona uma série a uma das listas do usuário
                            System.out.print("Digite o nome da série: ");
                            String nomeAdd = scanner.nextLine();
                            Serie nova = SerieService.buscarSerie(nomeAdd); 
                            if (nova != null) {
                                System.out.println("Adicionar em: 1.Favoritas  2.Assistidas  3.Para assistir");
                                int lista = Integer.parseInt(scanner.nextLine());
                                usuario.adicionarSerieNaLista(lista, nova);
                            } else {
                                System.out.println("Série não encontrada na API.");
                            }
                        }

                        case 4 -> { // Remove uma série de uma das listas do usuário
                            System.out.print("Digite o nome da série a remover: ");
                            String nomeRemover = scanner.nextLine();
                            Serie remover = SerieService.buscarSerie(nomeRemover);
                            if (remover != null) {
                                System.out.println("Remover de: 1.Favoritas  2.Assistidas  3.Para assistir");
                                int listaR = Integer.parseInt(scanner.nextLine());
                                usuario.removerSerieDaLista(listaR, remover);
                            } else {
                                System.out.println("Série não encontrada na API.");
                            }
                        }

                        case 5 -> usuario.ordenarListas(scanner); // Ordena as listas de séries do usuário

                        case 0 -> { // Sai do programa e salva os dados do usuário
                            System.out.println("Saindo e salvando informações...");
                            JsonManager.salvarUsuario(usuario);
                        }

                        default -> System.out.println("Opção inválida.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Digite um número válido.");
                } catch (Exception e) {
                    System.out.println("Erro inesperado: " + e.getMessage());
                }
            } // Fim do loop de opções

        } catch (Exception e) {
            System.out.println("Erro ao carregar ou salvar usuário: " + e.getMessage());
        } finally {
            scanner.close();
        } // Fecha o scanner para evitar vazamento de recursos
        System.out.println("Obrigado por usar o Gerenciador de Séries!");
    }
}