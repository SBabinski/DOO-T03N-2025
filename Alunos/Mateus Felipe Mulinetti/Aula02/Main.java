import java.util.Scanner;

public class Main {
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        menu();


    }

    static void menu() {
        int escolha = 0;
        while (escolha != 3) {
            System.out.println("[1] - Calcular Preço Total \n" +
                    "[2] - Calcular Troco \n" +
                    "[3] - Sair  \n");
            escolha = scanner.nextInt();
            switch (escolha) {
                case 1:
                    total();
                    break;
                case 2:
                    troco();
                    break;
                case 3:
                    System.out.println("Ate mais!!");
                    break;
                default:
                    System.out.println("Opção inválida. Por favor, escolha uma opção válida.  \n ");
                    break;
            }
        }
    }


    public static void total() {
        System.out.println("Calculadora de preços \n" +
                "Digite a Quantidade de plantas que você comprou:");
        float quantidade = scanner.nextFloat();
        System.out.println("Digite o valor unitario de cada planta:");
        float valor = scanner.nextFloat();

        float valorTotal = valor * quantidade;

        System.out.println("O valor total da compra foi de R$: " + valorTotal);

    }

    public static void troco() {
        System.out.println("Calculadora de trocos \n " +
                "Digite o valor recebido do cliente:");
        float Dcliente = scanner.nextFloat();
        System.out.println("Digite o valor da compra do cliente:");
        float valorCompra = scanner.nextFloat();

        float troco = Dcliente - valorCompra;

        System.out.println("O troco serÁ de R$: " + troco);

    }
}