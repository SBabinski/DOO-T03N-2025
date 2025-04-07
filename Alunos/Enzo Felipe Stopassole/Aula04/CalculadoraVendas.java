import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

class Venda {
    int quantidade;
    double precoUnitario;
    double valorTotal;
    double desconto;
    int dia;
    int mes;

    public Venda(int quantidade, double precoUnitario, double valorTotal, double desconto, int dia, int mes) {
        this.quantidade = quantidade;
        this.precoUnitario = precoUnitario;
        this.valorTotal = valorTotal;
        this.desconto = desconto;
        this.dia = dia;
        this.mes = mes;
    }

    @Override
    public String toString() {
        return String.format("Dia %d | Mês %d | Quantidade: %d | Preço Unitário: R$ %.2f | Valor Total: R$ %.2f | Desconto: R$ %.2f",
                             dia, mes, quantidade, precoUnitario, valorTotal, desconto);
    }
}

public class CalculadoraVendas {
    private static final double DESCONTO_PERCENTUAL = 0.05;
    private static List<Venda> registroVendas = new ArrayList<>();
    private static Map<String, Integer> vendasPorDia = new HashMap<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("\n--- Calculadora de Vendas ---");
            System.out.println("[1] - Calcular Total");
            System.out.println("[2] - Calcular Troco");
            System.out.println("[3] - Exibir Registro de Vendas");
            System.out.println("[4] - Buscar Vendas por Mês e Dia");
            System.out.println("[5] - Sair");
            System.out.print("Escolha o que quer: ");
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    calcularPrecoTotal(scanner);
                    break;
                case 2:
                    calcularTroco(scanner);
                    break;
                case 3:
                    exibirRegistroVendas();
                    break;
                case 4:
                    buscarVendasPorMesEDia(scanner);
                    break;
                case 5:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Tente novamente.");
            }
        } while (opcao != 5);

        scanner.close();
    }

    public static void calcularPrecoTotal(Scanner scanner) {
        System.out.print("Digite a quantidade da planta: ");
        int quantidade = scanner.nextInt();
        System.out.print("Digite o preço unitário da planta: ");
        double precoUnitario = scanner.nextDouble();
        System.out.print("Digite o dia da venda: ");
        int dia = scanner.nextInt();
        System.out.print("Digite o mês da venda: ");
        int mes = scanner.nextInt();

        double valorTotal = quantidade * precoUnitario;
        double desconto = 0;

        if (quantidade > 10) {
            desconto = valorTotal * DESCONTO_PERCENTUAL;
            valorTotal -= desconto;
        }

        System.out.printf("Preço total: R$ %.2f (Desconto aplicado: R$ %.2f)\n", valorTotal, desconto);

        // Registrar venda no sistema
        Venda venda = new Venda(quantidade, precoUnitario, valorTotal, desconto, dia, mes);
        registroVendas.add(venda);
        
        // Atualizar as vendas por dia no mês
        String chave = mes + "-" + dia;  // formato "mês-dia"
        vendasPorDia.put(chave, vendasPorDia.getOrDefault(chave, 0) + quantidade);
    }

    public static void calcularTroco(Scanner scanner) {
        System.out.print("Digite o valor recebido do cliente: ");
        double valorRecebido = scanner.nextDouble();
        System.out.print("Digite o valor total da compra: ");
        double valorTotal = scanner.nextDouble();

        if (valorRecebido < valorTotal) {
            System.out.println("Erro: O valor recebido é menor que o valor total da compra.");
        } else {
            double troco = valorRecebido - valorTotal;
            System.out.printf("Troco a ser dado ao cliente: R$ %.2f\n", troco);
        }
    }

    public static void exibirRegistroVendas() {
        System.out.println("\n--- Registro de Vendas ---");
        if (registroVendas.isEmpty()) {
            System.out.println("Nenhuma venda registrada ainda.");
        } else {
            for (Venda venda : registroVendas) {
                System.out.println(venda);
            }
        }
    }

    public static void buscarVendasPorMesEDia(Scanner scanner) {
        System.out.print("Digite o mês (1-12): ");
        int mes = scanner.nextInt();
        System.out.print("Digite o dia (1-31) ou 0 para consultar todos os dias: ");
        int dia = scanner.nextInt();

        if (dia == 0) {
            // Consultar todas as vendas do mês
            int totalVendasMes = 0;
            for (String chave : vendasPorDia.keySet()) {
                if (chave.startsWith(mes + "-")) {
                    totalVendasMes += vendasPorDia.get(chave);
                }
            }
            System.out.printf("Total de vendas no mês %d: %d\n", mes, totalVendasMes);
        } else {
            // Consultar as vendas do mês e dia específicos
            String chave = mes + "-" + dia;
            int vendasDia = vendasPorDia.getOrDefault(chave, 0);
            System.out.printf("Total de vendas no dia %d/%d: %d\n", dia, mes, vendasDia);
        }
    }
}
