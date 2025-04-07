import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class FloriculturaAt2{

    public static void main(String[] args) {
        Scanner Scan = new Scanner(System.in);
        double totalSoma = 0;
        int[][] vendas = new int[12][31]; 

        System.out.println("\n BEM-VINDOS \r\n " + " Floricultura Gabrielinha's \n");

        int opcao = 0;

        while (opcao != 5) {
            System.out.println("MENU:");
            System.out.println("    [1] - Calcular Preço Total");
            System.out.println("    [2] - Calcular Troco");
            System.out.println("    [3] - Histórico");
            System.out.println("    [4] - Pesquisar por venda");
            System.out.println("    [5] - Sair");

            opcao = Scan.nextInt();

            if (opcao == 1) {
                System.out.println("Informe o dia da compra: \n");
                int dia = Scan.nextInt();
        
                System.out.println("Informe o mês da compra (nº): \n");
                int mes = Scan.nextInt();

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

                if (mes >= 1 && mes <= 12 && dia >= 1 && dia <= 31) {
                    vendas[mes - 1][dia - 1] += quant;

                    System.out.println("Venda registrada com sucesso! \n");

                } else {

                    System.out.println("Data inválida! \n");

                }

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
                System.out.println("Informe o dia da venda:");
                int dia = Scan.nextInt();

                System.out.println("Informe o mês da venda:");
                int mes = Scan.nextInt();

                if (mes >= 1 && mes <= 12 && dia >= 1 && dia <= 31) {
                    System.out.println("Vendas do dia " + dia + " do mês " + mes + ": " + vendas[mes - 1][dia - 1]);
                } else {
                    System.out.println("Data inválida!");
                }

            }else if (opcao == 5) {
                System.out.println("Saindo do Sistema... Aguarde...");
            } else {
                System.out.println("Opção inválida!");
            }
        }
    }
}
