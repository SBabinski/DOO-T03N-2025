import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Venda {
    int quantidade;
    int dia;
    int mes;

    Venda(int quantidade, int dia, int mes) {
        this.quantidade = quantidade;
        this.dia = dia;
        this.mes = mes;
    }
}

public class CalculadoraVendas {
    private static List<Venda> vendas = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int opcao;

        do {
            System.out.println("\n--- Sistema de Vendas da Dona Gabrielinha ---");
            System.out.println("[1] Registrar venda");
            System.out.println("[2] Consultar vendas por dia");
            System.out.println("[3] Consultar vendas por mês");
            System.out.println("[4] Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    registrarVenda();
                    break;
                case 2:
                    consultarPorDia();
                    break;
                case 3:
                    consultarPorMes();
                    break;
                case 4:
                    System.out.println("Encerrando o sistema...");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }

        } while (opcao != 4);
    }

    private static void registrarVenda() {
        System.out.print("Quantidade de plantas vendidas: ");
        int quantidade = scanner.nextInt();

        System.out.print("Dia da venda: ");
        int dia = scanner.nextInt();

        System.out.print("Mês da venda (1 a 12): ");
        int mes = scanner.nextInt();

        vendas.add(new Venda(quantidade, dia, mes));
        System.out.println("Venda registrada com sucesso!");
    }

    private static void consultarPorDia() {
        System.out.print("Digite o dia que deseja consultar: ");
        int dia = scanner.nextInt();

        System.out.print("Digite o mês da consulta: ");
        int mes = scanner.nextInt();

        int total = 0;
        for (Venda venda : vendas) {
            if (venda.dia == dia && venda.mes == mes) {
                total += venda.quantidade;
            }
        }

        System.out.printf("Total de plantas vendidas em %02d/%02d: %d%n", dia, mes, total);
    }

    private static void consultarPorMes() {
        System.out.print("Digite o mês que deseja consultar: ");
        int mes = scanner.nextInt();

        int total = 0;
        for (Venda venda : vendas) {
            if (venda.mes == mes) {
                total += venda.quantidade;
            }
        }

        System.out.printf("Total de plantas vendidas no mês %d: %d%n", mes, total);
    }
}
