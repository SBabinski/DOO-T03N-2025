
import java.util.Scanner;

public class FloriculturaAt {

    public static void main(String[] args) {
        Scanner Scan = new Scanner(System.in);
        double totalSoma = 0;

        System.out.println("\n BEM-VINDOS \r\n " + " Floricultura Gabrielinha's \n");

        int opcao = 0;

        while (opcao != 4) {
            System.out.println("MENU:");
            System.out.println("    [1] - Calcular Preço Total");
            System.out.println("    [2] - Calcular Troco");
            System.out.println("    [3] - Configurações de Histórico");
            System.out.println("    [4] - Sair do sistema");

            opcao = Scan.nextInt();

            if (opcao == 1) {
                System.out.println("Quantos itens:\n");
                int quant = Scan.nextInt();

                System.out.println("Valor do item:\n");
                double valor = Scan.nextDouble();

                if (quant > 10) {
                    double desconto = valor * 0.10;
                    System.out.println("Desconto de 10%: " + desconto);
                    valor = valor - desconto;
                }

                totalSoma = quant * valor;
                System.out.println("O valor da compra é de: " + totalSoma);

            } else if (opcao == 2) {

                System.out.println("Valor recebido: \n");
                int receb = Scan.nextInt();

                System.out.println("Qual o valor total da compra:");
                int total = Scan.nextInt();

                double troco = receb - total;
                System.out.println("Devolver ao cliente: " + troco);

            } else if (opcao == 3) {
                FloricAdd floricAdd = new FloricAdd();  
                floricAdd.menu();    

            } else if (opcao == 4) {
                System.out.println("Saindo do Sistema... Aguarde...");
            } else {
                System.out.println("Opção inválida!");
            }
        }
    }
}
