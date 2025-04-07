import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class CalculadoraLojaPlantas {
    static List<String> RegistroVendas = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcao;
        do {
            System.out.println("\n---> Calculadora da Dona Gabrielinha <---");
            System.out.println("[1] - Calcular Preço Total");
            System.out.println("[2] - Calcular Troco");
            System.out.println("[3] - Ver Registro de Vendas");
            System.out.println("[4] - Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            switch (opcao) {
                case 1:
                    CalcularPrecoTotal(scanner);
                    break;
                case 2:
                    CalcularTroco(scanner);
                    break;
                case 3:
                    ExibirRegistroVendas();
                    break;
                case 4:
                    System.out.println("Tchau Obrigado!! ");
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        } while(opcao != 4);

        scanner.close();
    }

    public static void CalcularPrecoTotal(Scanner scanner) {
        System.out.print("Digite a quantidade de plantas: ");
        int quantidade = scanner.nextInt();
        System.out.print("Digite o preço unitário da planta: ");
        double precoUnitario = scanner.nextDouble();

        double total = quantidade * precoUnitario;
        double desconto = 0;

        if (quantidade > 10) {
            desconto = total * 0.05;
            total -= desconto;
        }

        System.out.printf("Preço total: R$ %.2f (Desconto aplicado: R$ %.2f)%n", total, desconto);

        // Calculo de vendas
        RegistroVendas.add("Quantidade: " + quantidade + ", Valor: R$ " + String.format("%.2f", total) + ", Desconto: R$ " + String.format("%.2f", desconto));
    }

    public static void CalcularTroco(Scanner scanner) {
        System.out.print("Digite o valor pago pelo cliente: ");
        double valorPago = scanner.nextDouble();
        System.out.print("Digite o valor total da compra: ");
        double valorCompra = scanner.nextDouble();
        if (valorPago < valorCompra) {
            System.out.println("Valor insuficiente! O cliente precisa pagar mais.");
        } else {
            double troco = valorPago - valorCompra;
            System.out.printf("Troco: R$ %.2f%n", troco);
        }
    }

    public static void ExibirRegistroVendas() {
        if (RegistroVendas.isEmpty()) {
            System.out.println("Nenhuma venda registrada até o momento.");
        } else {
            System.out.println("\n-- Registro de Vendas --");
            for (String venda : RegistroVendas) {
                System.out.println(venda);
            }
        }
    }
}
