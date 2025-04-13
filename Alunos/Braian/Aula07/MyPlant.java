package Alunos.Braian.Aula07;

import java.util.Date;
import java.util.Scanner;

public class MyPlant {
  public static Scanner scanner = new Scanner(System.in);

  public static void main(String[] args) {
    menu();
  }

  public static void menu() {
    int opcao = 0;
    while (opcao != 4) {
      exibirMenu();
      opcao = scanner.nextInt();
      scanner.nextLine();
      switch (opcao) {
        case 1 -> criarPedido();
        case 2 -> apresentarGerente();
        case 3 -> apresentarEndereco();
        case 4 -> System.out.println("Saindo...");
        default -> System.out.println("Opção inválida!");
      }
    }
  }

  public static void exibirMenu() {
    System.out.println("\n===== MENU MY PLANT =====");
    System.out.println("1 - Criar pedido");
    System.out.println("2 - Apresentar gerente");
    System.out.println("3 - Apresentar endereço formatado");
    System.out.println("0 - Sair");
    System.out.print("Escolha uma opção: ");
  }

  public static void criarPedido() {
    Item item1 = new Item(1, "Orquídea", "leve", 25.0);
    Item item2 = new Item(2, "Cacto", "resistente", 40.0);
    Item[] itens = new Item[]{item1, item2};

    Pedido pedido1 = new Pedido(
      1,
      new Date(),
      new Date(System.currentTimeMillis() + 86400000L),
      new Date(System.currentTimeMillis() + 259200000L),
      "Carlos",
      "João",
      "Loja 1",
      itens
    );

    System.out.println("\n Pedido Criado:");
    pedido1.apresentarPedido();
    pedido1.gerarDescricaoVenda();
  }

  public static void apresentarGerente() {
    Endereco endereco = new Endereco("PR", "Cascavel", "Centro", 123, "Sala 5");
    Gerente gerente = new Gerente("Ana Paula", 35, "Loja 2", 5000.00, endereco);

    System.out.println("\nDados do Gerente:");
    gerente.apresentarSe();
    System.out.printf("Média Salarial: R$ %.2f\n", gerente.calcularMedia());
    System.out.printf("Bônus: R$ %.2f\n", gerente.calcularBonus());
  }

  public static void apresentarEndereco() {
    Endereco endereco = new Endereco("PR", "Cascavel", "Centro", 321, "Próximo à praça central");
    System.out.println("\nEndereço:");
    endereco.apresentarLogradouro();
  }
}
