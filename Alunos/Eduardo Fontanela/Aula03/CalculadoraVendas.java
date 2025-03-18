import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Venda {
    int quantidade;
    double precoUnitario;
    double valorTotal;
    double desconto;

    public Venda(int quantidade, double precoUnitario, double valorTotal, double desconto) {
        this.quantidade = quantidade;
        this.precoUnitario = precoUnitario;
        this.valorTotal = valorTotal;
        this.desconto = desconto;
    }

    @Override
    public String toString() {
        return String.format("Quantidade: %d | Preço Unitário: R$ %.2f | Valor Total: R$ %.2f | Desconto: R$ %.2f",
                             quantidade, precoUnitario, valorTotal, desconto);
    }
}

public class CalculadoraVendas {
    private static final double DESCONTO_PERCENTUAL = 0.05;
    private static List<Venda> registroVendas = new ArrayList<>();
   
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("Calculadora de Vendas");
            System.out.println("[1] - Calcular Preço Total");
            System.out.println("[2] - Calcular Troco");
            System.out.println("[3] - Exibir Registro de Vendas");
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
                    exibirRegistroVendas();
                    break;
                case 4:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Tente novamente.");
            }
        } while (opcao != 4);

        scanner.close();
    }

    public static void calcularPrecoTotal(Scanner scanner) {
        System.out.print("Digite a quantidade da planta: ");
        int quantidade = scanner.nextInt();
        System.out.print("Digite o preço unitário da planta: ");
        double precoUnitario = scanner.nextDouble();

        double valorTotal = quantidade * precoUnitario;
        double desconto = 0;
       
        if (quantidade > 10) {
            desconto = valorTotal * DESCONTO_PERCENTUAL;
            valorTotal -= desconto;
        }
       
        System.out.printf("Preço total: R$ %.2f (Desconto aplicado: R$ %.2f)\n", valorTotal, desconto);
       
        registroVendas.add(new Venda(quantidade, precoUnitario, valorTotal, desconto));
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
}