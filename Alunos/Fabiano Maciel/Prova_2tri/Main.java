package Prova_2tri;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Bem-vindo ao Acompanhadorr de Séries!");
        System.out.print("Digite seu nome ou apelido :  ");
        String nome = sc.nextLine();

        User user = JsonUtils.carregarUsuario(nome);
        SerieManager manager = new SerieManager(user);

        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n===== Menu Principal =====");
            System.out.println("1. Buscar séries");
            System.out.println("2. Favoritos");
            System.out.println("3. Já assistidas");
            System.out.println("4. Deseja assistir");
            System.out.println("5. Salvar dados");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            try {
                opcao = Integer.parseInt(sc.nextLine());
                switch (opcao) {
                    case 1:
                        manager.menuBusca(sc);
                        break;
                    case 2:
                        manager.menuLista(sc, "favoritos");
                        break;
                    case 3:
                        manager.menuLista(sc, "assistidas");
                        break;
                    case 4:
                        manager.menuLista(sc, "deseja");
                        break;
                    case 5:
                        JsonUtils.salvarUsuario(user);
                        System.out.println("Dados salvos!");
                        break;
                    case 0:
                        JsonUtils.salvarUsuario(user);
                        System.out.println("Saindo... Dados salvos!");
                        break;
                    default:
                        System.out.println("Opção inválida.");
                }
            } catch (Exception e) {
                System.out.println("Erro: " + "Opção inválida.");
            }
        }
        sc.close();
    }
}