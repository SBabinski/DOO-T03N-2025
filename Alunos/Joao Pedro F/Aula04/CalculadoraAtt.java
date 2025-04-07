import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Aula2 {
    // Lista para armazenar o registro de vendas
    private static List<String> registroVendas = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            // Menu de opções
            System.out.println("\nBem-vindo à loja de plantas da Dona Gabrielinha");
            System.out.println("[1] - Calcular Preço Total");
            System.out.println("[2] - Calcular Troco");
            System.out.println("[3] - Mostrar Registro de Vendas");
            System.out.println("[4] - Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    calcularPrecoTotal(scanner);
                    break;
                case 2:
                    calcularTroco(scanner);
                    break;
                case 3:
                    mostrarRegistroVendas();
                    break;
                case 4:
                    System.out.println("Obrigada por usar a calculadora! Até logo!");
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        } while (opcao != 4);
        scanner.close();
    }

    // Método para calcular o preço total da venda
    private static void calcularPrecoTotal(Scanner scanner) {
        System.out.print("Digite a quantidade de plantas vendidas: ");
        int quantidade = scanner.nextInt();
        System.out.print("Digite o preço unitário da planta: ");
        double precoUnitario = scanner.nextDouble();

        // Cálculo do preço total e desconto
        double precoTotal = quantidade * precoUnitario;
        double desconto = (quantidade > 10) ? precoTotal * 0.05 : 0;
        precoTotal -= desconto;

        // Registro da venda
        String venda = "Quantidade: " + quantidade + ", Preço Total: R$ " + String.format("%.2f", precoTotal) + ", Desconto: R$ " + String.format("%.2f", desconto);
        registroVendas.add(venda);

        // Exibição do preço total
        System.out.printf("O preço total da venda é: R$ %.2f (Desconto aplicado: R$ %.2f) %n", precoTotal, desconto);
    }

    // Método para calcular o troco
    private static void calcularTroco(Scanner scanner) {
        System.out.print("Digite o valor pago pelo cliente: ");
        double valorPago = scanner.nextDouble();
        System.out.print("Digite o valor total da compra: ");
        double valorTotal = scanner.nextDouble();

        // Verificação se o valor pago é suficiente
        if (valorPago < valorTotal) {
            System.out.println("O valor pago é insuficiente!");
        } else {
            double troco = valorPago - valorTotal;
            System.out.printf("O troco a ser dado é: R$ %.2f%n", troco);
        }
    }

    // Método para mostrar o registro de vendas
    private static void mostrarRegistroVendas() {
        if (registroVendas.isEmpty()) {
            System.out.println("Nenhuma venda registrada até o momento.");
        } else {
            System.out.println("\nRegistro de Vendas:");
            for (String venda : registroVendas) {
                System.out.println(venda);
            }
        }
    }
}

