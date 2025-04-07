package Aula04;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


class venda {
    int quantidade;
    double precoUnitario;
    double precoTotal;
    double desconto;
    double valorPagar;
    int dia;
    int mes;

    public venda(int quantidade, double precoUnitario, double precoTotal, double desconto, double valorPagar, int dia, int mes) {
        this.quantidade = quantidade;
        this.precoUnitario = precoUnitario;
        this.precoTotal = precoTotal;
        this.desconto = desconto;
        this.valorPagar = valorPagar;
        this.dia = dia;
        this.mes = mes;
    }

    @Override
    public String toString() {
        return "Venda: " + quantidade + " plantas, Preço Unitário: " + precoUnitario + 
               ", Preço Total: " + precoTotal + ", Desconto: " + desconto + 
               ", Valor a Pagar: " + valorPagar +
               ", Data: " + dia + "/" + mes;
    }
}

public class LojaFloresAtualizacao {

   
    static ArrayList<venda> vendas = new ArrayList<>();
    
    
    static Map<String, Integer> vendasDiarias = new HashMap<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            
            System.out.println("=== Calculadora de Vendas de Dona Gabrielinha ===");
            System.out.println("[1] - Calcular Valor a Pagar (registrar venda com data)");
            System.out.println("[2] - Calcular Troco");
            System.out.println("[3] - Exibir Registro de Vendas");
            System.out.println("[4] - Consultar total de vendas por dia e mês");
            System.out.println("[5] - Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    
                    System.out.print("Digite a quantidade de plantas vendidas: ");
                    int quantidade = scanner.nextInt();
                    System.out.print("Digite o preço unitário da planta: ");
                    double precoUnitario = scanner.nextDouble();
                    
                    double precoTotal = quantidade * precoUnitario;
                    double desconto = 0.0;
                    
                    
                    if (quantidade > 10) {
                        desconto = precoTotal * 0.05;
                    }
                    double valorPagar = precoTotal - desconto;
                    
                    
                    System.out.print("Digite o dia da venda (1-31): ");
                    int dia = scanner.nextInt();
                    System.out.print("Digite o mês da venda (1-12): ");
                    int mes = scanner.nextInt();
                    
                    System.out.println("Preço Total: " + precoTotal);
                    if (desconto > 0) {
                        System.out.println("Desconto Aplicado: " + desconto);
                    }
                    System.out.println("Valor a Pagar: " + valorPagar);
                    
                    
                    venda venda = new venda(quantidade, precoUnitario, precoTotal, desconto, valorPagar, dia, mes);
                    vendas.add(venda);
                    
                    
                    String chaveData = dia + "/" + mes;
                    vendasDiarias.put(chaveData, vendasDiarias.getOrDefault(chaveData, 0) + 1);
                    
                    break;
                case 2:
                    
                    if (vendas.isEmpty()) {
                        System.out.println("Nenhuma venda registrada. Calcule o valor a pagar primeiro.");
                    } else {
                        
                        venda ultimaVenda = vendas.get(vendas.size() - 1);
                        System.out.print("Digite o valor recebido do cliente: ");
                        double valorRecebido = scanner.nextDouble();
                        double troco = valorRecebido - ultimaVenda.valorPagar;
                        System.out.println("Valor a Pagar: " + ultimaVenda.valorPagar);
                        System.out.println("Troco: " + troco);
                    }
                    break;
                case 3:
                    
                    if (vendas.isEmpty()) {
                        System.out.println("Nenhuma venda registrada.");
                    } else {
                        System.out.println("=== Registro de Vendas ===");
                        for (venda s : vendas) {
                            System.out.println(s);
                        }
                    }
                    break;
                case 4:
                    
                    System.out.print("Digite o dia para consulta (1-31): ");
                    int diaConsulta = scanner.nextInt();
                    System.out.print("Digite o mês para consulta (1-12): ");
                    int mesConsulta = scanner.nextInt();
                    String chaveConsulta = diaConsulta + "/" + mesConsulta;
                    
                    int totalVendas = vendasDiarias.getOrDefault(chaveConsulta, 0);
                    System.out.println("Total de vendas em " + chaveConsulta + ": " + totalVendas);
                    break;
                case 5:
                    
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
            System.out.println(); 
        } while (opcao != 5);

        scanner.close();
    }
}
