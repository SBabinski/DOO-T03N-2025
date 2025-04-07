import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static Scanner scanner = new Scanner(System.in);
    public static List<Plantas> plantas = new ArrayList<>();

    public static void main(String[] args) {
        menu();


    }

    static void menu() {
        int escolha = 0;
        while (escolha != 4) {
            System.out.println("[1] - Calcular Preço Total \n" + "[2] - Calcular Troco \n" + "[3] - Vendas  \n" + "[4] - Sair");
            escolha = scanner.nextInt();
            switch (escolha) {
                case 1:
                    total();
                    break;
                case 2:
                    troco();
                    break;
                case 3:
                    vendas();
                    break;
                case 4:
                    System.out.println("Ate mais!!");
                    break;
                default:
                    System.out.println("Opção inválida. Por favor, escolha uma opção válida.  \n ");
                    break;
            }
        }
    }


    public static void total() {

        int quantidade = 0;
        float temp = 0;
        float valorTotal = 0;

        System.out.println("Calculadora de preços \n" + "Digite a Quantidade de plantas que você comprou:");
        quantidade = scanner.nextInt();
        System.out.println("Digite o valor unitario de cada planta:");
        float valor = scanner.nextFloat();

        float valorBruto = valor * quantidade;
        valorTotal = valorBruto;


        if (quantidade > 10) {
            temp = (valorBruto * 5) / 100;
            valorTotal = valorBruto - temp;
            System.out.println("Você comprou mais de 10 unidades então ganhara 5% de desconto o valor ficou R$: " + valorTotal);
        } else {
            System.out.println("O valor total da compra foi de R$: " + valorBruto);
        }

        Plantas planta = new Plantas(quantidade, valorTotal, temp);
        plantas.add(planta);

    }

    public static void troco() {
        System.out.println("Calculadora de trocos \n " + "Digite o valor recebido do cliente:");
        float Dcliente = scanner.nextFloat();
        System.out.println("Digite o valor da compra do cliente:");
        float valorCompra = scanner.nextFloat();

        float troco = Dcliente - valorCompra;

        System.out.println("O troco serÁ de R$: " + troco);

    }

    public static void vendas() {
        int cont = 1;

        if (plantas.size() == 0) {
            System.out.println("Não a vendas ainda!!");
        } else {
            System.out.println("Bem vindo a area de vendas!");
            for (Plantas p : plantas) {
                System.out.println("Compra numero " + "[ " + cont + " ]" + p);
                cont++;
            }
        }
    }

}