package fag;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Venda {
    private int quantidade;
    private double precoUnitario;
    private double desconto;
    private double total;

    public Venda(int quantidade, double precoUnitario, double desconto, double total) {
        this.quantidade = quantidade;
        this.precoUnitario = precoUnitario;
        this.desconto = desconto;
        this.total = total;
    }

    @Override
    public String toString() {
        return "Quantidade: " + quantidade + ", Preço Unitário: R$" + precoUnitario + ", Desconto: R$" + desconto + ", Total: R$" + total;
    }
}

public class Calculadora {
    private static final double DESCONTO_ESPECIAL = 0.05;
    private static List<Venda> historicoVendas = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int opcao;
        do {
            System.out.println("\nMenu:");
            System.out.println("1 - Registrar Venda");
            System.out.println("2 - Exibir Histórico de Vendas");
            System.out.println("3 - Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    registrarVenda();
                    break;
                case 2:
                    exibirHistoricoVendas();
                    break;
                case 3:
                    System.out.println("Saindo do sistema...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 3);
    }

    private static void registrarVenda() {
        System.out.print("Digite a quantidade de plantas: ");
        int quantidade = scanner.nextInt();
        System.out.print("Digite o preço unitário da planta: ");
        double precoUnitario = scanner.nextDouble();

        double total = quantidade * precoUnitario;
        double desconto = (quantidade > 10) ? total * DESCONTO_ESPECIAL : 0;
        double valorFinal = total - desconto;

        historicoVendas.add(new Venda(quantidade, precoUnitario, desconto, valorFinal));
        System.out.printf("Total da compra: R$%.2f (Desconto aplicado: R$%.2f)\n", valorFinal, desconto);
    }

    private static void exibirHistoricoVendas() {
        if (historicoVendas.isEmpty()) {
            System.out.println("Nenhuma venda registrada até o momento.");
        } else {
            System.out.println("\nHistórico de Vendas:");
            for (Venda venda : historicoVendas) {
                System.out.println(venda);
            }
        }
    }
}
