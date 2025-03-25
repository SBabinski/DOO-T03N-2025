package Aula02;

import java.util.Scanner;



public class LojaFlores {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcao;
        
        do {
            System.out.println("=== Calculadora de Flores ===");
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
                    System.out.println("saiu com sucesso");
                    break;
                default:
                    System.out.println("Erro, opção inválida");
            }
        } while (opcao != 3);
        
        scanner.close();
    
    
        
    }
    
    public static void calcularPrecoTotal(Scanner scanner){
        System.out.println("Quantas plantas você deseja comprar?");
        int quantidade = scanner.nextInt();
        System.out.println("Qual o preço de cada planta?");
        double preco = scanner.nextDouble();
        double precoTotal = quantidade * preco;
        System.out.println("O preço total é: R$  " + precoTotal);
    }
    public static void calcularTroco(Scanner scanner){
    System.out.println("Valor que o cliente pagou :");
    double valorPago = scanner.nextDouble();
    System.out.println("Valor total da compra :");
    double valorTotal = scanner.nextDouble();
    
    if (valorPago >= valorTotal){
        double troco = valorPago - valorTotal;
        System.out.println("O troco é: R$  " + troco);
    
    }
    else{
        System.out.println("O valor pago é insuficiente");
    
    
    }
    }
    }
    

