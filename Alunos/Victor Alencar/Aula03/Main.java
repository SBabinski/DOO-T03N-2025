package sce;
import java.util.Scanner;
import java.util.ArrayList;

class Venda {
    int quantidade;
    double precoUnitario;
    double precoTotal;
    double desconto;
    double valorPago;
    double valorComDesconto;
    double troco;

    
    public Venda(int quantidade, double precoUnitario, double precoTotal, double desconto, double valorPago, double valorComDesconto, double troco) {
        this.quantidade = quantidade;
        this.precoUnitario = precoUnitario;
        this.precoTotal = precoTotal;
        this.desconto = desconto;
        this.valorPago = valorPago;
        this.valorComDesconto = valorComDesconto;
        this.troco = troco;
    }

   
    public void exibirDetalhesVenda() {
        System.out.println("Quantidade de Plantas: " + quantidade);
        System.out.println("Preço Unitário: R$ " + precoUnitario);
        System.out.println("Preço Total: R$ " + precoTotal);
        System.out.println("Desconto Aplicado: R$ " + desconto);
        System.out.println("Valor com Desconto: R$ " + valorComDesconto);
        System.out.println("Valor Pago: R$ " + valorPago);
        System.out.println("Troco: R$ " + troco);
    }
}

class Calculadora {

   
    public double calcularPrecoTotal(int quantidade, double precoUnitario) {
        return quantidade * precoUnitario;
    }

  
    public double calcularDesconto(int quantidade, double precoTotal) {
        if (quantidade > 10) {
            return precoTotal * 0.05; 
        }
        return 0;
    }

   
    public double calcularTroco(double valorPago, double valorComDesconto) {
        return valorPago - valorComDesconto;
    }
}

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Calculadora calculadora = new Calculadora();
        ArrayList<Venda> registrosVendas = new ArrayList<>();
        int opcao;
        
        do {
            // Exibir menu
            System.out.println("Escolha uma opção:");
            System.out.println("[1] - Calcular Preço Total");
            System.out.println("[2] - Calcular Troco");
            System.out.println("[3] - Registrar Venda");
            System.out.println("[4] - Exibir Registros de Vendas");
            System.out.println("[5] - Sair");
            
           
            opcao = scanner.nextInt();
            
            switch (opcao) {
                case 1:
                   
                    System.out.print("Informe a quantidade de plantas: ");
                    int quantidade = scanner.nextInt();
                    System.out.print("Informe o preço unitário da planta: ");
                    double precoUnitario = scanner.nextDouble();
                    
                    double precoTotal = calculadora.calcularPrecoTotal(quantidade, precoUnitario);
                    double desconto = calculadora.calcularDesconto(quantidade, precoTotal);
                    double precoComDesconto = precoTotal - desconto;
                    
                    if (quantidade > 10) {
                        System.out.println("Preço Total (com desconto de 5%): R$ " + precoComDesconto);
                    } else {
                        System.out.println("Preço Total: R$ " + precoTotal);
                    }
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
                   
                    System.out.print("Informe a quantidade de plantas: ");
                    quantidade = scanner.nextInt();
                    System.out.print("Informe o preço unitário da planta: ");
                    precoUnitario = scanner.nextDouble();
                    
                    precoTotal = calculadora.calcularPrecoTotal(quantidade, precoUnitario);
                    desconto = calculadora.calcularDesconto(quantidade, precoTotal);
                    double valorComDesconto = precoTotal - desconto;
                    
                    System.out.print("Informe o valor pago pelo cliente: ");
                    double valorPagoCliente = scanner.nextDouble();
                    
                    double trocoVenda = calculadora.calcularTroco(valorPagoCliente, valorComDesconto);
                    
                   
                    Venda venda = new Venda(quantidade, precoUnitario, precoTotal, desconto, valorPagoCliente, valorComDesconto, trocoVenda);
                    registrosVendas.add(venda);
                    
                    System.out.println("Venda registrada com sucesso!");
                    break;
                
                case 4:
                   
                    if (registrosVendas.isEmpty()) {
                        System.out.println("Nenhuma venda registrada.");
                    } else {
                        System.out.println("Registros de Vendas:");
                        for (Venda v : registrosVendas) {
                            v.exibirDetalhesVenda();
                            System.out.println("-------------------------------");
                        }
                    }
                    break;
                
                case 5:
                   
                    System.out.println("Saindo...");
                    break;
                
                default:
                    System.out.println("Opção inválida! Tente novamente.");
                    break;
            }
        } while (opcao != 5);
        
        scanner.close();
    }
}
