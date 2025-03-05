import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        boolean sair = false;

        while(sair != true) {
            System.out.println("Qual operação deseja fazer?\n [1] Calcular compra \n [2] Calcular troco \n [3]Sair");
            int opção = sc.nextInt();
            switch (opção) {
                case 1:
                    System.out.println("Diga a quantidade do item!");
                    int QtdItem = sc.nextInt();
                    System.out.println("Insira o valor do item!");
                    float ValorItem = sc.nextFloat();
                    float valorTotal = ValorItem * QtdItem;
                    System.out.println("O valor que o cliente deve pagar será:\n" + valorTotal);
                    break;
                case 2:
                    System.out.println("Informe o valor da compra");
                    float ValorCompra = sc.nextFloat();
                    System.out.println("Informe o valor que será pago pelo cliente");
                    float ValorPagamento = sc.nextFloat();

                    float ValorTroco = ValorPagamento - ValorCompra;

                    if(ValorTroco < 0){
                        System.out.println("Faltando\n" + ValorTroco);
                    }else {
                        System.out.println("O valor que deve dar de troco é:\n" + ValorTroco);
                    }
                    break;
                case 3:
                    System.out.println("Saindo...");
                    sair = true;
                    break;
            }
        }
        sc.close();
    }

}