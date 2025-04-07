import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Venda {
    int quantidade;
    double precoUnitario;
    double valorTotal;
    double descontoAplicado;

    Venda(int quantidade, double precoUnitario, double valorTotal, double descontoAplicado) {
        this.quantidade = quantidade;
        this.precoUnitario = precoUnitario;
        this.valorTotal = valorTotal;
        this.descontoAplicado = descontoAplicado;
    }
}

public class CalculadoraMelhorada {
    private static List<Venda> registroDeVendas = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("\n--- Calculadora Dona Gabrielinha ---");
            System.out.println("[1] - Calcular preço total (com desconto)");
            System.out.println("[2] - Ver registro de vendas");
            System.out.println("[3] - Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    calcularPrecoTotalComDesconto(scanner);
                    break;
                case 2:
                    exibirRegistroDeVendas();
                    break;
                case 3:
                    System.out.println("Saindo... Obrigado por usar o sistema!");
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        } while (opcao != 3);

        scanner.close();
    }

    private static void calcularPrecoTotalComDesconto(Scanner scanner) {
        System.out.print("Digite a quantidade de plantas: ");
        int quantidade = scanner.nextInt();

        System.out.print("Digite o preço unitário da planta: ");
        double precoUnitario = scanner.nextDouble();

        double valorTotal = quantidade * precoUnitario;
        double desconto = 0;

        if (quantidade > 10) {
            desconto = valorTotal * 0.05;
            valorTotal -= desconto;
            System.out.printf("Desconto especial aplicado: R$ %.2f%n", desconto);
        }

        System.out.printf("Valor total a pagar: R$ %.2f%n", valorTotal);

        registroDeVendas.add(new Venda(quantidade, precoUnitario, valorTotal, desconto));
    }

    private static void exibirRegistroDeVendas() {
        if (registroDeVendas.isEmpty()) {
            System.out.println("Nenhuma venda registrada ainda.");
            return;
        }

        System.out.println("\n--- Registro de Vendas ---");
        for (int i = 0; i < registroDeVendas.size(); i++) {
            Venda venda = registroDeVendas.get(i);
            System.out.printf(
                "Venda #%d: Quantidade: %d | Preço Unitário: R$ %.2f | Valor Total: R$ %.2f | Desconto: R$ %.2f%n",
                (i + 1), venda.quantidade, venda.precoUnitario, venda.valorTotal, venda.descontoAplicado
            );
        }
    }
}
