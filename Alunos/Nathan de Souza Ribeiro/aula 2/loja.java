import java.util.Scanner;

public class loja {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("\n=== Calculadora de Vendas - Dona Gabrielinha ===");
            System.out.println("[1] - Calcular Preço Total");
            System.out.println("[2] - Calcular Troco");
            System.out.println("[3] - Sair");
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
                    System.out.println("Pinxando fora... Obrigado por usar a calculadora! Volte sempre.");
                    break;
                default:
                    System.out.println("Não deu boa! Tente denovo.");
            }
        } while (opcao != 3);

        scanner.close();
    }

    public static void calcularPrecoTotal(Scanner scanner) {
        System.out.print("Digite a quantidade de plantas: ");
        int quantidade = scanner.nextInt();
        System.out.print("Digite o preço de cada planta: ");
        double precoUnitario = scanner.nextDouble();

        double precoTotal = quantidade * precoUnitario;
        System.out.printf("Preço total da compra: R$ %.2f\n", precoTotal);
    }

    public static void calcularTroco(Scanner scanner) {
        System.out.print("Digite o valor que o cliente pagou: ");
        double valorRecebido = scanner.nextDouble();
        System.out.print("Digite o valor da compra: ");
        double valorTotal = scanner.nextDouble();

        if (valorRecebido < valorTotal) {
            System.out.println("Erro: o valor é insuficiente!");
        } else {
            double troco = valorRecebido - valorTotal;
            System.out.printf("Troco a ser devolvido: R$ %.2f\n", troco);
        }
    }
}
