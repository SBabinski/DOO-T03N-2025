import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);
    static float quantidade = 0;
    static float valor = 0;
    static double precoTotal = 0;
    static double valorSemDesconto = 0;
    static double valorDesconto = 0;
    static float quantidadeFinal = 0;
    static float valorFinal = 0;
    static double valorDescontoFinal = 0;

    public static void main(String[] args) {
        menu();
    }
    public static void menu() {
        System.out.println("Olá! Seja bem vindo!");
        System.out.println("1 - Calcular o preço total.\n2 - Calcular o troco.\n3 - Ver estatisticas de venda.\n4 - Sair.");
        int op = sc.nextInt();
        switch (op) {
            case 1: calcularPrecoTotal();
                break;
            case 2: calcularTroco();
                break;
            case 3: mostrarVendas();
                break;
            case 4: 
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
        salvarVenda();
        menu();
    }

    public static void calcularTroco() {
        System.out.println("Digite o valor dado pelo cliente?");
        float valor = sc.nextFloat();
        System.out.println("Digite o valor da compra?");
        float compra = sc.nextFloat();
        float valorTroco = 0;
        valorTroco = valor - compra;
        if (valor < compra) {
            System.out.println("O valor do cliente é menor que o valor total da compra, repita o processo.");
            menu();
        }
        System.out.println("O valor do troco é de " + valorTroco);
        menu();
    }

    public static void salvarVenda() {
        quantidadeFinal = quantidadeFinal + quantidade;
        valorSemDesconto = valorSemDesconto + valorSemDesconto;
        valorDescontoFinal = valorDesconto + valorDesconto;
    }

    public static void mostrarVendas() {
        System.out.println("Plantas vendidas:" + quantidadeFinal);
        System.out.println("Valor total sem desconto:" + valorSemDesconto);
        System.out.println("Valor final dos descontos:" + valorDescontoFinal);
    }
}