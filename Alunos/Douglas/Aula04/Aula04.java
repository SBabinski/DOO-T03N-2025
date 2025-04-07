import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author dougbruno
 */
public class Aula04 {

    static Scanner scanner = new Scanner(System.in);
    static List<Venda> registrosDeVendas = new ArrayList<>();

    public static void main(String[] args) {
        menu();
    }

    public static void menu() {
        System.out.println("-- Menu --\n"
                + "1 - Registrar Venda\n"
                + "2 - Calcular Troco\n"
                + "3 - Exibir Registros de Vendas\n"
                + "4 - Buscar Vendas por Dia\n"
                + "5 - Buscar Vendas por Mês\n"
                + "6 - Sair\n");

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
                buscarVendasPorDia();
                break;
            case 5:
                buscarVendasPorMes();
                break;
            case 6:
                System.exit(0);
            default:
                System.out.println("Selecione uma opção válida!");
        }

        menu();
    }

    public static void calcularPreco() {
        System.out.print("Digite o dia da venda: ");
        int dia = scanner.nextInt();
        System.out.print("Digite o mês da venda: ");
        int mes = scanner.nextInt();
        System.out.print("Digite a quantidade da planta vendida: ");
        int quantidade = scanner.nextInt();
        System.out.print("Digite o valor de uma planta: ");
        float valor = scanner.nextFloat();
        float valorTotal = quantidade * valor;

        float desconto = 0;
        if (quantidade > 10) {
            desconto = (valorTotal * 5) / 100;
            valorTotal -= desconto;
        }

        System.out.println("O valor total será: R$" + String.format("%.2f", valorTotal));

        registrarVenda(dia, mes, quantidade, valor, valorTotal, desconto);
    }

    public static void calcularTroco() {
        System.out.print("Digite o valor recebido: ");
        float valorRecebido = scanner.nextFloat();
        System.out.print("Digite o valor total: ");
        float valorTotalCompra = scanner.nextFloat();
        System.out.println("O valor do troco será: R$" + String.format("%.2f", (valorRecebido - valorTotalCompra)));
    }

    public static void registrarVenda(int dia, int mes, int quantidade, float valor, float valorTotal, float desconto) {
        Venda venda = new Venda(dia, mes, quantidade, valor, valorTotal, desconto);
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

    public static void buscarVendasPorDia() {
        System.out.print("Digite o dia desejado: ");
        int dia = scanner.nextInt();
        System.out.print("Digite o mês desejado: ");
        int mes = scanner.nextInt();

        int totalVendas = 0;
        float totalValor = 0;

        System.out.println("-- Vendas do Dia " + dia + "/" + mes + " --");
        for (Venda venda : registrosDeVendas) {
            if (venda.dia == dia && venda.mes == mes) {
                System.out.println(venda);
                totalVendas += venda.quantidade;
                totalValor += venda.valorTotal;
            }
        }

        if (totalVendas > 0) {
            System.out.println("Total de plantas vendidas: " + totalVendas);
            System.out.println("Total arrecadado: R$" + String.format("%.2f", totalValor));
        } else {
            System.out.println("Nenhuma venda nessa data.");
        }
    }

    public static void buscarVendasPorMes() {
        System.out.print("Digite o mês desejado: ");
        int mes = scanner.nextInt();

        int totalVendas = 0;
        float totalValor = 0;

        System.out.println("-- Vendas do Mês " + mes + " --");
        for (Venda venda : registrosDeVendas) {
            if (venda.mes == mes) {
                System.out.println(venda);
                totalVendas += venda.quantidade;
                totalValor += venda.valorTotal;
            }
        }

        if (totalVendas > 0) {
            System.out.println("Total de plantas vendidas no mês: " + totalVendas);
            System.out.println("Total arrecadado no mês: R$" + String.format("%.2f", totalValor));
        } else {
            System.out.println("Nenhuma venda nesse mês.");
        }
    }

    static class Venda {
        int dia;
        int mes;
        int quantidade;
        float valorUnitario;
        float valorTotal;
        float desconto;

        public Venda(int dia, int mes, int quantidade, float valorUnitario, float valorTotal, float desconto) {
            this.dia = dia;
            this.mes = mes;
            this.quantidade = quantidade;
            this.valorUnitario = valorUnitario;
            this.valorTotal = valorTotal;
            this.desconto = desconto;
        }

        @Override
        public String toString() {
            return "Venda [Data: " + dia + "/" + mes + ", Quantidade: " + quantidade + ", Valor Unitário: R$" + String.format("%.2f", valorUnitario)
                    + ", Desconto: R$" + String.format("%.2f", desconto) + ", Valor Total: R$" + String.format("%.2f", valorTotal) + "]";
        }
    }
}