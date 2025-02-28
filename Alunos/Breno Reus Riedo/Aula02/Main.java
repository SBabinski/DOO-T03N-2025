import java.util.Scanner;

public class Main {

    private static final Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        menu();
    }


    private static void menu(){
        while (true) {
            printMenu(); //Função para evitar bloco de codigo enorme do println
            int userChoice = input.nextInt();

            switch (userChoice) {
                case 1:
                    calcPrecoTotal();
                    break;
                case 2:
                    calcTroco();
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Não é uma opção válida!");
                    break;
            }
        }
    }


    private static void calcPrecoTotal() {
        int quantidade = 0;
        float precoProduto = 0;

        System.out.println("Coloque quantidade de um(a) planta/produto vendido(a): ");
        quantidade = input.nextInt();

        System.out.println("Coloque o preço unitário: ");
        precoProduto = input.nextFloat();

        System.out.println("O preço total foi: R$ " + precoProduto * quantidade);
    }

    private static void calcTroco() {
        float valorRecebido = 0;
        float valorTotal = 0;

        System.out.println("Coloque o valor recebido: ");
        valorRecebido = input.nextFloat();

        System.out.println("Coloque o valor total: ");
        valorTotal = input.nextFloat();

        System.out.println("O valor do troco foi: R$ " + (valorRecebido - valorTotal));

    }

    //Função para evitar bloco de codigo enorme do println
    private static void printMenu() {
        System.out.println
                (
                        """
                        [1] - Calcular Preço Total
                        [2] - Calcular Troco
                        [3] - Sair\
                        """
                );
    }
}
