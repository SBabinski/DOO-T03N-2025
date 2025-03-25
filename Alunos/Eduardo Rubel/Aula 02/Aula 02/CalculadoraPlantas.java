import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CalculadoraPlantas {
    static class Venda {
        int quantidade;
        double valorVenda;
        double desconto;

        Venda(int quantidade, double valorVenda, double desconto) {
            this.quantidade = quantidade;
            this.valorVenda = valorVenda;
            this.desconto = desconto;
        }

        public String toString() {
            return "Quantidade: " + quantidade +
                   ", Valor Total: R$ " + String.format("%.2f", valorVenda) +
                   ", Desconto Aplicado: R$ " + String.format("%.2f", desconto);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Venda> vendas = new ArrayList<>();
        int escolha;

        do {
            System.out.println("\n--- Calculadora de Plantas da Gabrielinha ---");
            System.out.println("1 - Calcular Preco com Desconto");
            System.out.println("2 - Calcular Troco");
            System.out.println("3 - Registrar Venda");
            System.out.println("4 - Mostrar Vendas Registradas");
            System.out.println("5 - Sair");
            System.out.print("Escolha uma opcao: ");
            escolha = scanner.nextInt();

            switch (escolha) {
                case 1 -> calcularPreco(scanner);
                case 2 -> calcularTroco(scanner);
                case 3 -> registrarVenda(scanner, vendas);
                case 4 -> mostrarVendas(vendas);
                case 5 -> System.out.println("Encerrando o sistema. Ate logo!");
                default -> System.out.println("Opcao invalida! Por favor, tente novamente.");
            }
        } while (escolha != 5);

        scanner.close();
    }

    private static void calcularPreco(Scanner scanner) {
        System.out.print("Digite a quantidade de plantas: ");
        int quantidade = scanner.nextInt();
        System.out.print("Digite o preco unitario: ");
        double precoUnitario = scanner.nextDouble();

        double desconto = (quantidade > 10) ? 0.05 * (quantidade * precoUnitario) : 0;
        double total = (quantidade * precoUnitario) - desconto;

        if (desconto > 0) {
            System.out.printf("Desconto de R$ %.2f aplicado!%n", desconto);
        }
        System.out.printf("Preco total: R$ %.2f%n", total);
    }
    private static void calcularTroco(Scanner scanner) {
        System.out.print("Digite o valor recebido: ");
        double valorRecebido = scanner.nextDouble();
        System.out.print("Digite o valor total da compra: ");
        double valorCompra = scanner.nextDouble();

        double troco = valorRecebido - valorCompra;

        if (troco < 0) {
            System.out.println("Valor insuficiente!");
        } else {
            System.out.printf("Troco: R$ %.2f%n", troco);
        }
    }

    private static void registrarVenda(Scanner scanner, List<Venda> vendas) {
        System.out.print("Digite a quantidade de plantas: ");
        int quantidade = scanner.nextInt();
        System.out.print("Digite o preco un: ");
        double precoUnitario = scanner.nextDouble();

        double desconto = (quantidade > 10) ? 0.05 * (quantidade * precoUnitario) : 0;
        double totalVenda = (quantidade * precoUnitario) - desconto;

        vendas.add(new Venda(quantidade, totalVenda, desconto));
        System.out.printf("Venda registrada! Valor final: R$ %.2f%n", totalVenda);
    }

    private static void mostrarVendas(List<Venda> vendas) {
        System.out.println("\n--- Registro de Vendas ---");
        if (vendas.isEmpty()) {
            System.out.println("Nenhuma venda registrada.");
        } else {
            for (Venda venda : vendas) {
                System.out.println(venda);
            }
        }
    }
}
