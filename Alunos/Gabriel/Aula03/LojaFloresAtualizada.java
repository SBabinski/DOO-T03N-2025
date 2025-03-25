package Aula03;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Venda {
    int quantidade;
    double precoTotal;
    double desconto;
    
    public Venda(int quantidade, double precoTotal, double desconto) {
        this.quantidade = quantidade;
        this.precoTotal = precoTotal;
        this.desconto = desconto;
    }
    
    @Override
    public String toString() {
        return "Quantidade: " + quantidade + " | Preço Total: R$ " + String.format("%.2f", precoTotal) + " | Desconto: R$ " + String.format("%.2f", desconto);
    }
}

public class LojaFloresAtualizada {
    static List<Venda> registroVendas = new ArrayList<>();
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcao;
        
        do {
            System.out.println("\n==== Calculadora de Vendas ====");
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
                    System.out.println("Saindo... Obrigado por usar a calculadora!");
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        } while (opcao != 4);
        
        scanner.close();
    }
    
    public static void calcularPrecoTotal(Scanner scanner) {
        System.out.print("Digite a quantidade da planta: ");
        int quantidade = scanner.nextInt();
        
        System.out.print("Digite o preço unitário da planta: ");
        double precoUnitario = scanner.nextDouble();
        
        double total = quantidade * precoUnitario;
        double desconto = 0;
        
        if (quantidade > 10) {
            desconto = total * 0.05;
            total -= desconto;
            System.out.println("Desconto de 5% aplicado!");
        }
        
        registroVendas.add(new Venda(quantidade, total, desconto));
        
        System.out.printf("Preço total após desconto: R$ %.2f\n", total);
    }
    
    public static void calcularTroco(Scanner scanner) {
        System.out.print("Digite o valor recebido do cliente: ");
        double valorRecebido = scanner.nextDouble();
        
        System.out.print("Digite o valor total da compra: ");
        double valorTotal = scanner.nextDouble();
        
        if (valorRecebido < valorTotal) {
            System.out.println("Valor insuficiente! O cliente precisa pagar mais.");
        } else {
            double troco = valorRecebido - valorTotal;
            System.out.printf("Troco a ser dado: R$ %.2f\n", troco);
        }
    }
    
    public static void exibirRegistroVendas() {
        if (registroVendas.isEmpty()) {
            System.out.println("Nenhuma venda registrada até o momento.");
        } else {
            System.out.println("\n==== Registro de Vendas ====");
            for (Venda venda : registroVendas) {
                System.out.println(venda);
            }
        }
    }
}

