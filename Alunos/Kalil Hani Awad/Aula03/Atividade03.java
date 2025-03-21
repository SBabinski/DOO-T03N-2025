import java.util.ArrayList;
import java.util.Scanner;

class Atividade_03 {
    public static Scanner scanner = new Scanner(System.in);
    public static ArrayList<Resultado_>Total_compras = new ArrayList<>();
    public static float totalCompra = 0;

    public static void main(String[] args) {
        menu();
    }
    public static void menu() {
        boolean turn = true;
        while (turn) {
            System.out.println("\nBem-vindo ao Programa de Conta");
            System.out.println("1 - Calcular");
            System.out.println("2 - Troco");
            System.out.println("3 - Sair");
            System.out.println("4 - Ver Registro");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    calcular();
                    break;
                case 2:
                    troco();
                    break;
                case 3:
                    turn = false;
                    sair();
                    break;
                case 4:
                    registro();
                    break;
                default:
                    System.out.println("Opção Inválida. Tente novamente.");
                    break;
            }
        }
    }
    public static void calcular() {
        float Desconto = 0;
        System.out.print("Digite o valor do item: ");
        float precoItem = scanner.nextFloat();

        System.out.print("Digite a quantidade de itens: ");
        int quantidade = scanner.nextInt();
        totalCompra = precoItem * quantidade;
        if(quantidade > 10){
            Desconto = totalCompra*0.05f;
            totalCompra -= Desconto;

        }
        Resultado_ Venda = new Resultado_(totalCompra, quantidade, Desconto);

        System.out.printf("O valor total da compra é: R$ %.2f\n", totalCompra);
        Total_compras.add(Venda);

    }

    public static void troco() {
        if (totalCompra == 0) {
            System.out.println("Nenhuma compra foi registrada.");
            return;
        }

        System.out.print("Digite o valor do pagamento: ");
        float pagamento = scanner.nextFloat();

        float troco = pagamento - totalCompra;

        if (troco > 0) {
            System.out.printf("O troco é: R$ %.2f\n", troco);

        } else if (troco < 0) {
            System.out.println("Falta dinheiro:"+(-1*troco));

        } else {
            System.out.println("Pagamento exato! Sem troco.");

        }
    }

    public static void sair() {
        System.out.println("Fim do Programa. Obrigado!");
    }
    public static void registro(){
        for(Resultado_ venda : Total_compras){
            System.out.println(venda);
        }
    }
}