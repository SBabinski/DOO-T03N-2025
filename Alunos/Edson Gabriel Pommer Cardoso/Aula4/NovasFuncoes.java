package Aula4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class NovasFuncoes {

    private static List<Venda> registroVendas = new ArrayList<>();
    private static Map<String, Integer> vendasPorData = new HashMap<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int option;

        do {
            System.out.println("[1] - Calcular Preço Total");
            System.out.println("[2] - Calcular Troco");
            System.out.println("[3] - Visualizar Registro de Vendas");
            System.out.println("[4] - Registrar Vendas por Data");
            System.out.println("[5] - Buscar Vendas por Data");
            System.out.println("[6] - Sair");
            System.out.print("Escolha uma opção: ");
            option = scanner.nextInt();

            switch (option) {
                case 1:
                    calcularPrecoTotal(scanner);
                    break;
                case 2:
                    calcularTroco(scanner);
                    break;
                case 3:
                    visualizarRegistroVendas();
                    break;
                case 4:
                    registrarVendasPorData(scanner);
                    break;
                case 5:
                    buscarVendasPorData(scanner);
                    break;
                case 6:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (option != 6);

        scanner.close();
    }

    private static void calcularPrecoTotal(Scanner scanner) {
        System.out.print("Digite a quantidade da planta: ");
        int quantidade = scanner.nextInt();
        System.out.print("Digite o preço unitário da planta: ");
        double precoUnitario = scanner.nextDouble();
        
        double precoTotal = quantidade * precoUnitario;
        if (quantidade > 10) {
            double desconto = precoTotal * 0.05; // 
            precoTotal -= desconto;
            System.out.println("Desconto aplicado: " + desconto);
        }
        
        System.out.println("O preço total é: " + precoTotal);
        registrarVenda(quantidade, precoTotal);
    }

    private static void calcularTroco(Scanner scanner) {
        System.out.print("Digite o valor recebido do cliente: ");
        double valorRecebido = scanner.nextDouble();
        System.out.print("Digite o valor total da compra: ");
        double valorTotal = scanner.nextDouble();
        
        double troco = valorRecebido - valorTotal;
        System.out.println("O troco a ser dado é: " + troco);
    }

    private static void registrarVenda(int quantidade, double valorVenda) {
        Venda novaVenda = new Venda(quantidade, valorVenda);
        registroVendas.add(novaVenda);
        System.out.println("Venda registrada com sucesso!");
    }

    private static void visualizarRegistroVendas() {
        System.out.println("Registro de Vendas:");
        for (Venda venda : registroVendas) {
            System.out.println(venda);
        }
    }

    private static void registrarVendasPorData(Scanner scanner) {
        System.out.print("Digite a data (dd/mm): ");
        String data = scanner.next();
        System.out.print("Digite a quantidade de vendas: ");
        int quantidade = scanner.nextInt();

        vendasPorData.put(data, vendasPorData.getOrDefault(data, 0) + quantidade);
        System.out.println("Vendas registradas para a data " + data + " com sucesso!");
    }

    private static void buscarVendasPorData(Scanner scanner) {
        System.out.print("Digite a data (dd/mm) para buscar: ");
        String data = scanner.next();
        
        int totalVendas = vendasPorData.getOrDefault(data, 0);
        System.out.println("Total de vendas para a data " + data + ": " + totalVendas);
    }
}

class Venda {
    private int quantidade;
    private double valorVenda;

    public Venda(int quantidade, double valorVenda) {
        this.quantidade = quantidade;
        this.valorVenda = valorVenda;
    }

    public String toString() {
        return "Quantidade: " + quantidade + ", Valor da Venda: " + valorVenda;
    }
}