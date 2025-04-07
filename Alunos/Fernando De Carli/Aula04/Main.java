import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.*;

public class Main {
    public static Scanner sc = new Scanner(System.in);
    public static List<historico> registroDeVendas = new ArrayList<>();
    public static Map<String, Integer> vendasPorData = new HashMap<>();

    public static void main(String[] args) {
        int op = 0;

        do {
            menu();
            op = sc.nextInt();
            switch (op) {
                case 1:
                    CalcularPreco();
                    break;
                case 2:
                    CalcularTroco();
                    break;
                case 3:
                    descontoEspecial();
                    break;
                case 4:
                    registroDeVendas();
                    break;
                case 5:
                    registrarVendaPorDia();
                    break;
                case 6:
                    consultarVendasPorDia();
                    break;
                case 7:
                    consultarVendasPorMes();
                    break;
                case 8:
                    System.out.println("Encerrando...");
                    break;
                default:
                    System.out.println("Opção invalida!!");
            }
        } while (op != 8);
    }
    public static void menu(){
        System.out.println("------BEM-VINDO------");
        System.out.println("\n[1] - Calcular preço total");
        System.out.println("\n[2] - Calcular troco");
        System.out.println("\n[3] - Desconto especial compras a cima de 10 quantidade");
        System.out.println("\n[4] - Registro de vendas recentes");
        System.out.println("\n[5] - Realizar vendas");
        System.out.println("\n[6] - Visualizar vendas por dia");
        System.out.println("\n[7] - Visualizar vendas por mes");
        System.out.println("\n[8] - Sair");
        System.out.println("---------------------");
    }

    public static void CalcularPreco (){
        Scanner sc = new Scanner(System.in);
        System.out.println("Quantas plantas comprou?");
        int planta = sc.nextInt();

        System.out.println("Qual valor unitario dela:");
        double valorUnitario = sc.nextDouble();

        double valorTotal =+ planta * valorUnitario;
        System.out.println("Preço total da compra foi : "+valorTotal);
        System.out.println("---------------------");
    }
    public static void CalcularTroco(){
        Scanner sc = new Scanner(System.in);

        System.out.println("Valor recebido pelo cliente;");
        double valorRecebido = sc.nextDouble();

        System.out.println("Valor total da compra:");
        double valorTotal = sc.nextDouble();

        double Troco = valorTotal - valorRecebido;

        if (valorRecebido < valorTotal) {
            System.out.println("Valor insuficiente");
        }
        else {
            System.out.println("Valor do troco: "+Troco);
        }
        System.out.println("---------------------");
    }
    public static void descontoEspecial(){
        System.out.println("Quantas plantas esta comprando? \n");
        int quant = sc.nextInt();
        System.out.println("Qual valor unitario: \n");
        double valorUnitario = sc.nextDouble();

        historico Historico = new historico(quant,valorUnitario);
        registroDeVendas.add(Historico);

        System.out.println("\nVenda realizada com sucesso:");
        Historico.imprimir();
        System.out.println("---------------------");
    }
    public static void registroDeVendas(){
        if(registroDeVendas.isEmpty()){
            System.out.println("Nenhuma venda feita!!");
        }else {
            System.out.println("Registro de vendas ");
            for(historico h : registroDeVendas){
                h.imprimir();
            }
        }
    }
    public static void registrarVendaPorDia(){
        System.out.println("Digite o dia da venda :");
        int day = sc.nextInt();
        System.out.println("Digite o mês da venda :");
        int month = sc.nextInt();
        System.out.println("Digite a quantidade de vendas:");
        int quant = sc.nextInt();

        String data = day + "/" + month;
        vendasPorData.put(data, vendasPorData.getOrDefault(data, 0) + quant);

        System.out.println("Venda registrada com sucesso!!\n");
        System.out.println("---------------------");
    }
    public static void consultarVendasPorDia(){
        System.out.println("Digite o dia da consulta:");
        int day = sc.nextInt();
        System.out.println("Digite o mês da consulta:");
        int month = sc.nextInt();

        String data = day + "/" + month;
        int total = vendasPorData.getOrDefault(data, 0);

        System.out.println("Total de vendas no dia " + data + ": " + total);
    }
    public static void consultarVendasPorMes(){
        System.out.println("Digite o mês de consulta:");
        int month = sc.nextInt();

        int totalVendas = 0;
        for (Map.Entry<String, Integer> entry : vendasPorData.entrySet()) {
            if (entry.getKey().endsWith("/" + month)) {
                totalVendas += entry.getValue();
            }
        }
        System.out.println("Total de vendas no mês " + month + ": " + totalVendas);
    }
}