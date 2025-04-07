import java.util.Scanner;

public class Loja{
    public static void main(String[]args){
        Scanner sc = new Scanner(System.in);
        double total=0;
        int i = 0;

        do{
            System.out.println("\n[1] Calcular Preço Total");
            System.out.println("[2] Calcular Troco");
            System.out.println("[3] Sair");
            int opcao = sc.nextInt();

            int quant;
            double val;
            
            double receb;
            double troco;

            if (opcao == 1) {
                System.out.println("Quantas plantas serão vendidas?");
                quant = sc.nextInt();
                System.out.println("Qual é o valor de cada planta?");
                val = sc.nextDouble();
                total = quant * val;

                System.out.println("Deu R$"+ total);
            } else if(opcao == 2){
                System.out.println("Qual foi o valor recebido?");
                receb = sc.nextDouble();
                troco = receb - total;
                System.out.printf("O troco será de: R$" + troco);
            }else if (opcao==3) {
                i=3;
                System.out.println("Você saiu");
            }else{
                
                System.out.println("O valor digitado não é válido");
            }
        }while(i != 3);

        sc.close();
    }
}