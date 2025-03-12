import java.util.Scanner;

public class Calculadora {

    public static double calcularPrecoTotal(int quantidade, double precoUnitario) {
        return quantidade * precoUnitario;
    }
 
    public static double calcularTroco(double valorPago, double valorTotal) {
        return valorPago - valorTotal;
    }

    public static void exibirMenu() {
        System.out.println("Escolha uma opção:");
        System.out.println("[1] - Calcular Preço Total");
        System.out.println("[2] - Calcular Troco");
        System.out.println("[3] - Sair");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            exibirMenu();
            System.out.print("Digite a opção desejada: ");
            int opcao = scanner.nextInt();

            if (opcao == 1) {
                System.out.print("Digite a quantidade de plantas: ");
                int quantidade = scanner.nextInt();
                System.out.print("Digite o preço unitário da planta: ");
                double precoUnitario = scanner.nextDouble();
                double precoTotal = calcularPrecoTotal(quantidade, precoUnitario);
                System.out.printf("O preço total da venda é: R$%.2f%n%n", precoTotal);
            } else if (opcao == 2) {
                System.out.print("Digite o valor pago pelo cliente: ");
                double valorPago = scanner.nextDouble();
                System.out.print("Digite o valor total da compra: ");
                double valorTotal = scanner.nextDouble();
                double troco = calcularTroco(valorPago, valorTotal);
                System.out.printf("O troco a ser dado ao cliente é: R$%.2f%n%n", troco);
            } else if (opcao == 3) {
                System.out.println("Saindo...");
                break;
            } else {
                System.out.println("Opção inválida. Tente novamente.\n");
            }
        }
        scanner.close();
    }
}