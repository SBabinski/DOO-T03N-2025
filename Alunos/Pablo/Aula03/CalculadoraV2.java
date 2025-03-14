package Aula03;

import java.util.Scanner;

public class CalculadoraV2 {
    private static int qtdPlantasVendidas = 0;
    private static double valorTotalVendas = 0;
    private static double valorTotalDescontos = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {

            System.out.println("--------------------MENU--------------------");
            System.out.println("Selecione uma opção: ");
            System.out.println("[1] - Calcular preço total");
            System.out.println("[2] - Calcular troco");
            System.out.println("[3] - Exibir registro de vendas");
            System.out.println("[0] - Sair\n");

            int opcao = scanner.nextInt();
            switch (opcao) {
                case 0:
                    System.out.println("Encerrando calculadora...");
                    return;

                case 1:
                    System.out.println("Informe o preço da planta: ");
                    double preco = scanner.nextDouble();

                    System.out.println("Informe a quantidade desse planta: ");
                    int quantidade = scanner.nextInt();

                    double valorProduto = calcularValorProduto(preco, quantidade);
                    System.out.printf("O preço total é: %.2f.\n\n", valorProduto);
                    salvarCompraRegistro(valorProduto, quantidade);

                    break;

                case 2:
                    System.out.println("Informe o valor pago pelo cliente: ");
                    double valorPago = scanner.nextDouble();

                    System.out.println("Informe o valor da compra: ");
                    double valorCompra = scanner.nextDouble();

                    System.out.printf("O troco do cliente é: %.2f.\n\n", calcularValorTroco(valorPago, valorCompra));

                    break;

                case 3:
                    System.out.println("-------------REGISTRO DE VENDAS-------------");
                    System.out.printf("Total em vendas:\t\tR$ %.2f\n", valorTotalVendas);
                    System.out.printf("Total em descontos:\t\tR$ %.2f\n", valorTotalDescontos);
                    System.out.printf("Total de plantas vendidas:\t%s\n\n", qtdPlantasVendidas);
                    break;
            }
        }
    }

    private static double calcularValorProduto(double preco, int quantidade) {
        double valorProduto;
        
        if (quantidade > 10) {
            valorProduto = preco * quantidade * 0.95;
            valorTotalDescontos += preco * quantidade * 0.05;
        } else {
            valorProduto = preco * quantidade;
        }

        return valorProduto;
    }

    private static double calcularValorTroco(double valorPago, double valorCompra) {
        return valorPago - valorCompra;
    }

    private static void salvarCompraRegistro(double valorProduto, int quantidadePlantas) {
        valorTotalVendas += valorProduto;
        qtdPlantasVendidas += quantidadePlantas;
    }
}
