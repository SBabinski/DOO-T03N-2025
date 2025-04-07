import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

class CalculadoraLojaPlantas {
    static List<String> RegistroVendas = new ArrayList<>();
    static Map<String, Integer> VendasDiarias = new HashMap<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcao;
        do {
            System.out.println("\n---> Calculadora da Dona Gabrielinha <---");
            System.out.println("[1] - Calcular Preço Total");
            System.out.println("[2] - Calcular Troco");
            System.out.println("[3] - Ver Registro de Vendas");
            System.out.println("[4] - Ver Vendas por Dia/Mês");
            System.out.println("[5] - Sair");
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
                    BuscarVendasPorDiaMes(scanner);
                    break;
                case 5:
                    System.out.println("Tchau Obrigado!! ");
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        } while(opcao != 5);

        scanner.close();
    }

    public static void CalcularPrecoTotal(Scanner scanner) {
        System.out.print("Digite a quantidade de plantas: ");
        int quantidade = scanner.nextInt();
        System.out.print("Digite o preço unitário da planta: ");
        double precoUnitario = scanner.nextDouble();

        double total = quantidade * precoUnitario;
        double desconto = (quantidade > 10) ? total * 0.05 : 0;
        total -= desconto;

        System.out.printf("Preço total: R$ %.2f (Desconto aplicado: R$ %.2f)%n", total, desconto);

        System.out.print("Digite o dia da venda (formato dd/mm): ");
        String data = scanner.next();

        RegistroVendas.add("Data: " + data + " - Quantidade: " + quantidade + ", Valor: R$ " + String.format("%.2f", total));

        VendasDiarias.put(data, VendasDiarias.getOrDefault(data, 0) + quantidade);
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

    public static void BuscarVendasPorDiaMes(Scanner scanner) {
        System.out.print("Digite o dia e mês desejado (formato dd/mm): ");
        String data = scanner.next();

        if (VendasDiarias.containsKey(data)) {
            System.out.println("Total de vendas no dia " + data + ": " + VendasDiarias.get(data));
        } else {
            System.out.println("Nenhuma venda registrada nessa data.");
        }
    }
}
