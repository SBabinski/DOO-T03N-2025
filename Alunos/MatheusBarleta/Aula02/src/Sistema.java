package Alunos.MatheusBarleta.Aula02.src;

import java.util.Scanner;

public class Sistema {
    public static void main(String[]args){
        Scanner sc = new Scanner(System.in);

        int opcao = 0;
        double total = 0;
        int quant = 0;
        double val = 0;
        double receb = 0;
        double troco = 0;

        do{
            System.out.println("[1] Calcular Preço Total");
            System.out.println("[2] Calcular Troco");
            System.out.println("[3] Sair");
            opcao = sc.nextInt();

            switch (opcao) {
                case 1:
                    System.out.println("Quantas plantas serão vendidas?");
                    quant = sc.nextInt();
                    System.out.println("Qual é o valor de cada planta?");
                    val = sc.nextDouble();
                    total = quant * val;
                    System.out.println(total);
                break;

                case 2:
                    System.out.println("Qual foi o valor recebido?");
                    receb = sc.nextDouble();
                    troco = receb - total;
                    System.out.println("O troco será de: R$" + troco);
                break;

                case 3:
                    System.out.println("Você saiu");
                break;
            }
        }while(opcao != 3);

        sc.close();
    }
}
