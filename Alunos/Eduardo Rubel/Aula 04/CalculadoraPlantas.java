import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CalculadoraPlantasSimples {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Venda> vendas = new ArrayList<>();
        int opcao;

        do {
            System.out.println("1 - Registrar venda");
            System.out.println("2 - Ver vendas por dia");
            System.out.println("3 - Ver vendas por mes");
            System.out.println("4 - Sair");
            System.out.print("Escolha: ");
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1 -> registrarVenda(scanner, vendas);
                case 2 -> mostrarVendasDia(scanner, vendas);
                case 3 -> mostrarVendasMes(scanner, vendas);
                case 4 -> System.out.println("Ate logo!");
                default -> System.out.println("Opcao invalida!");
            }
        } while (opcao != 4);
    }

    static class Venda {
        int dia;
        int mes;
        int quantidade;
        double valor;

        public Venda(int dia, int mes, int quantidade, double valor) {
            this.dia = dia;
            this.mes = mes;
            this.quantidade = quantidade;
            this.valor = valor;
        }
    }

    private static void registrarVenda(Scanner scanner, List<Venda> vendas) {
        System.out.print("Dia: ");
        int dia = scanner.nextInt();
        
        System.out.print("Mes: ");
        int mes = scanner.nextInt();
        
        System.out.print("Quantidade: ");
        int qtd = scanner.nextInt();
        
        System.out.print("Valor unitario: ");
        double valorUnit = scanner.nextDouble();

        // Calcula desconto de 5% para mais de 10 plantas
        double valorTotal = qtd * valorUnit;
        if (qtd > 10) {
            valorTotal *= 0.95; // Aplica 5% de desconto
        }

        vendas.add(new Venda(dia, mes, qtd, valorTotal));
        System.out.printf("Venda registrada! Total: R$ %.2f\n", valorTotal);
    }

    private static void mostrarVendasDia(Scanner scanner, List<Venda> vendas) {
        System.out.print("Dia: ");
        int dia = scanner.nextInt();
        
        System.out.print("Mes: ");
        int mes = scanner.nextInt();

        int totalQtd = 0;
        double totalValor = 0;
        int contador = 0;

        for (Venda v : vendas) {
            if (v.dia == dia && v.mes == mes) {
                totalQtd += v.quantidade;
                totalValor += v.valor;
                contador++;
            }
        }

        System.out.printf("\nVendas no dia %d/%d:\n", dia, mes);
        System.out.println("Total de vendas: " + contador);
        System.out.println("Plantas vendidas: " + totalQtd);
        System.out.printf("Valor total: R$ %.2f\n", totalValor);
    }

    private static void mostrarVendasMes(Scanner scanner, List<Venda> vendas) {
        System.out.print("Mes: ");
        int mes = scanner.nextInt();

        int totalQtd = 0;
        double totalValor = 0;
        int contador = 0;

        for (Venda v : vendas) {
            if (v.mes == mes) {
                totalQtd += v.quantidade;
                totalValor += v.valor;
                contador++;
            }
        }

        System.out.printf("\nVendas no mes %d:\n", mes);
        System.out.println("Total de vendas: " + contador);
        System.out.println("Plantas vendidas: " + totalQtd);
        System.out.printf("Valor total: R$ %.2f\n", totalValor);
    }
}