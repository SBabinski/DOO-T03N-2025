package scr;

import java.util.Scanner;

class Calculadora {

    
    public double calcularPrecoTotal(int quantidade, double precoUnitario) {
        return quantidade * precoUnitario;
    }

    
    public double calcularTroco(double valorPago, double valorTotal) {
        return valorPago - valorTotal;
    }
}

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Calculadora calculadora = new Calculadora();
        int opcao;
        
        do {
           
            System.out.println("Escolha uma opção:");
            System.out.println("[1] - Calcular Preço Total");
            System.out.println("[2] - Calcular Troco");
            System.out.println("[3] - Sair");
            
           
            opcao = scanner.nextInt();
            
            switch (opcao) {
                case 1:
                  
                    System.out.print("Informe a quantidade de plantas: ");
                    int quantidade = scanner.nextInt();
                    System.out.print("Informe o preço unitário da planta: ");
                    double precoUnitario = scanner.nextDouble();
                    
                    double precoTotal = calculadora.calcularPrecoTotal(quantidade, precoUnitario);
                    System.out.println("Preço Total: R$ " + precoTotal);
                    break;
                
                case 2:
                   
                    System.out.print("Informe o valor pago pelo cliente: ");
                    double valorPago = scanner.nextDouble();
                    System.out.print("Informe o valor total da compra: ");
                    double valorTotal = scanner.nextDouble();
                    
                    double troco = calculadora.calcularTroco(valorPago, valorTotal);
                    System.out.println("Troco: R$ " + troco);
                    break;
                
                case 3:
                  
                    System.out.println("Saindo...");
                    break;
                
                default:
                    System.out.println("Opção inválida! Tente novamente.");
                    break;
            }
        } while (opcao != 3);
        
        scanner.close();
    }
}
