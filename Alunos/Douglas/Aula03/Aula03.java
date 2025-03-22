package aula03;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author dougbruno
 */
public class Aula03 {

    static Scanner scanner = new Scanner(System.in);

    static List<Venda> registrosDeVendas = new ArrayList<>();
    
    public static void main(String[] args) {
        menu();
    }

    public static void menu() {
        System.out.println("-- Menu --\n"
                + "1 - Calcular Preço Total\n"
                + "2 - Calcular Troco\n"
                + "3 - Exibir Registros de Vendas\n"
                + "4 - Sair\n");

        System.out.print("Escolha uma opção: ");
        int opcao = scanner.nextInt();

        switch (opcao) {
            case 1:
                calcularPreco();
                break;
            case 2:
                calcularTroco();
                break;
            case 3:
                exibirRegistrosDeVendas();
                break;
            case 4:
                System.exit(0);
            default:
                System.out.println("Selecione uma opção válida!");
                menu();
        }

        menu();
    }

    public static void calcularPreco() {
        System.out.print("Digite a quantidade da planta vendida: ");
        int quantidade = scanner.nextInt();
        System.out.print("Digite o valor de uma planta: ");
        float valor = scanner.nextFloat();
        float valorTotal = quantidade * valor;

        float desconto = 0;
        if (quantidade > 10) {
            desconto = (valorTotal * 5) / 100;
            valorTotal = valorTotal - desconto;
        }

        System.out.println("O valor total será: R$" + String.format("%.2f", valorTotal));
        
        registrarVenda(quantidade, valor, valorTotal, desconto);
    }

    public static void calcularTroco() {
        System.out.print("Digite o valor recebido: ");
        float valorRecebido = scanner.nextFloat();
        System.out.print("Digite o valor total: ");
        float valorTotalCompra = scanner.nextFloat();
        System.out.println("O valor do troco será: R$" + String.format("%.2f", (valorRecebido - valorTotalCompra)));
    }

    public static void registrarVenda(int quantidade, float valor, float valorTotal, float desconto) {
        Venda venda = new Venda(quantidade, valor, valorTotal, desconto);
        registrosDeVendas.add(venda); 
    }

    public static void exibirRegistrosDeVendas() {
        if (registrosDeVendas.isEmpty()) {
            System.out.println("Nenhuma venda realizada.");
        } else {
            System.out.println("-- Registros de Vendas --");
            for (Venda venda : registrosDeVendas) {
                System.out.println(venda);
            }
        }
    }
    
    static class Venda {
        int quantidade;
        float valorUnitario;
        float valorTotal;
        float desconto;

        public Venda(int quantidade, float valorUnitario, float valorTotal, float desconto) {
            this.quantidade = quantidade;
            this.valorUnitario = valorUnitario;
            this.valorTotal = valorTotal;
            this.desconto = desconto;
        }

        @Override
        public String toString() {
            return "Venda [Quantidade: " + quantidade + ", Valor Unitário: R$" + String.format("%.2f", valorUnitario) 
                    + ", Desconto: R$" + String.format("%.2f", desconto) + ", Valor Total: R$" + String.format("%.2f", valorTotal) + "]";
        }
    }
}