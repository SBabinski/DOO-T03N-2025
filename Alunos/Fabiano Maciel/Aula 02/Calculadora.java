import java.util.Scanner;

public class Calculadora {

    // Função para calcular o preço total
    public static double calcularPrecoTotal(int quantidade, double precoUnitario) {
        return quantidade * precoUnitario;
    }

    // Função para calcular o troco
    public static double calcularTroco(double valorPago, double valorTotal) {
        return valorPago - valorTotal;
    }

    // Função para exibir o menu
    public static void exibirMenu() {
        System.out.println("\nMenu:");
        System.out.println("[1] - Calcular Preço Total");
        System.out.println("[2] - Calcular Troco");
        System.out.println("[3] - Sair");
    }

    
    // Função principal
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcao;

        // Loop para exibir o menu e processar as escolhas
        while (true) {
            exibirMenu();
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            
            if (opcao == 1) {
                System.out.print("Informe a quantidade da planta: ");
                int quantidade = scanner.nextInt();
                System.out.print("Informe o preço unitário da planta: ");
                double precoUnitario = scanner.nextDouble();
                double precoTotal = calcularPrecoTotal(quantidade, precoUnitario);
                System.out.printf("O preço total da venda é: R$%.2f\n", precoTotal);
            } 
            else if (opcao == 2) {
                System.out.print("Informe o valor pago pelo cliente: ");
                double valorPago = scanner.nextDouble();
                System.out.print("Informe o valor total da compra: ");
                double valorTotal = scanner.nextDouble();
                double troco = calcularTroco(valorPago, valorTotal);
                System.out.printf("O troco a ser dado ao cliente é: R$%.2f\n", troco);
            } 
            else if (opcao == 3) {
                System.out.println("Saindo do programa...");
                break;
            } 
            else {
                System.out.println("Opção inválida! Tente novamente.");
            }
        }

        scanner.close();
    }
}