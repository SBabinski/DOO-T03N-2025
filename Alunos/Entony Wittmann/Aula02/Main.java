import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        menu();
    }
    public static void menu() {
        System.out.println("Olá! Seja bem vindo!");
        System.out.println("1 - Calcular o preço total.\n2 - Calcular o troco.\n3 - Sair.");
        int op = sc.nextInt();
        switch (op) {
            case 1: calcularPrecoTotal();
                break;
            case 2: calcularTroco();
                break;
            case 3:
                break;

                default: menu();
                break;
        }
    }

    public static void calcularPrecoTotal() {
        System.out.println("Digite a quantidade de plantas que o cliente comprou:");
        float quantidade = sc.nextFloat();
        System.out.println("Digite o valor da planta?");
        float valor = sc.nextFloat();
        float precoTotal = 0;
        precoTotal = valor * quantidade;
        System.out.println("O valor total é de " + precoTotal);
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
}