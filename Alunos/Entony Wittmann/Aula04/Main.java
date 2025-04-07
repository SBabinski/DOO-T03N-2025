import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;

public class Main {
    static Scanner sc = new Scanner(System.in);
    static float quantidade = 0;
    static float valor = 0;
    static double precoTotal = 0;
    static double valorSemDesconto = 0;
    static double valorDesconto = 0;
    private static Map<String, Integer> mapaDatas = new HashMap<>();

    public static void main(String[] args) {
        menu();
    }
    public static void menu() {
        System.out.println("Olá! Seja bem vindo!");
        System.out.println("1 - Calcular o preço total.\n2 - Calcular o troco.\n3 - Realizar uma venda.\n4 - Ver vendas\n5 - Ver venda especifica.\n6 - Sair.");
        int op = sc.nextInt();
        switch (op) {
            case 1: calcularPrecoTotal();
                break;
            case 2: calcularTroco();
                break;
            case 3: salvarPorData();
                break;
            case 4: datasDeVenda();
                break;
            case 5: dataEspecificaDeVenda();
                break;
            case 6:
                break;

            default: menu();
                break;
        }
    }

    public static void calcularPrecoTotal() {
        System.out.println("Digite a quantidade de plantas que o cliente comprou:");
        quantidade = sc.nextFloat();
        System.out.println("Digite o valor da planta?");
        valor = sc.nextFloat();
        precoTotal = valor * quantidade;
        valorSemDesconto = valor * quantidade;
        if (quantidade >= 10) {
            precoTotal = precoTotal - (precoTotal * 0.05);
            valorDesconto = (precoTotal * 0.05);
        }
        System.out.println("O valor total é de " + precoTotal);
        menu();
    }

    public static void calcularTroco() {
        System.out.println("Digite o valor dado pelo cliente?");
        float valor = sc.nextFloat();
        System.out.println("Digite o valor da compra?");
        float compra = sc.nextFloat();
        float valorTroco = 0;
        if (valor < compra) {
            System.out.println("O valor do cliente é menor que o valor total da compra, repita o processo.");
            menu();
        }
        valorTroco = valor - compra;
        System.out.println("O valor do troco é de " + valorTroco);
        menu();
    }

    public static void salvarPorData() {
        try {
            System.out.println("Informe a data da venda:");
            int data = sc.nextInt();
            System.out.println("Informe o mês da venda:");
            int mes = sc.nextInt();
            System.out.println("Informe a quantidade de plantas:");
            int plantas = sc.nextInt();

            String chave = data + "/" + mes;

            mapaDatas.put(chave, mapaDatas.getOrDefault(chave, 0) + plantas);
        }
            catch (InputMismatchException e) {
                System.out.println("Digite novamente!");
                sc.next();
        }
        menu();
    }

    public static void datasDeVenda() {
        if (mapaDatas.isEmpty()) {
            System.out.println("Realize uma venda! Nenhuma venda registrada!");
            menu();
        }
        System.out.println("Lista de vendas Data - mes: Quantidade:");
        for (Map.Entry<String, Integer> entry : mapaDatas.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue() + " vendas");
        }
        menu();
    }

    public static void dataEspecificaDeVenda() {
        try {
            System.out.println("Informe a data:");
            int data = sc.nextInt();
            System.out.println("Informe o mes:");
            int mes = sc.nextInt();
            String chave = data + "/" + mes;
            int vendas = mapaDatas.getOrDefault(chave, 0);

            System.out.println("Vendas na data:" + data + "/" + mes + ": " + vendas);
        } catch (InputMismatchException e) {
            System.out.println("Digite novamente!");
            sc.next();
        }
        menu();
    }
}