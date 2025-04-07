package Aula02;

import java.util.Scanner;

public class Calculadora {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Selecione uma opção: ");
            System.out.println("[1] - Calcular Preço Total");
            System.out.println("[2] - Calcular Troco");
            System.out.println("[3] - Sair");
            int opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    System.out.println("Informe o preço da planta: ");
                    float preco = scanner.nextFloat();
    
                    System.out.println("Informe a quantidade desse planta: ");
                    int quantidade = scanner.nextInt();
    
                    System.out.printf("O preço total é: %.2f.\n", calcularValorProduto(preco, quantidade));
                    break;
    
                case 2:
                    System.out.println("Informe o valor pago pelo cliente: ");
                    float valorPago = scanner.nextFloat();
    
                    System.out.println("Informe o valor da compra: ");
                    float valorCompra = scanner.nextFloat();
    
                    System.out.printf("O troco do cliente é: %.2f.\n", calcularTroco(valorPago, valorCompra));
                    break;
    
                case 3:
                    System.out.println("Encerrando calculadora...");
                    return;
            }
        }
    }

    private static float calcularValorProduto(float preco, int quantidade) {
        return preco * quantidade;
    }

    private static float calcularTroco(float valorPago, float valorCompra) {
        return valorPago - valorCompra;
    }
}