import java.util.Scanner;

public class Principal{


    public static void calcularPreco(){

        System.out.println("Informe Quantidade De Plantas: ");
        int quantidade = scan.nextInt();
        scan.nextLine();

        System.out.println("Informe Valor Unitario: ");
        double valorUnitario = scan.nextDouble();
        scan.nextLine();

        double valorTotal = quantidade * valorUnitario;
        System.out.println("Valor Total Da Compra R$ " + valorTotal );

    }


    public static void calcularTroco(){

        System.out.println("Quantos Reais o Cliente Deu? ");
        double valorRecebido = scan.nextDouble();
        scan.nextLine();

        System.out.println("Valor Total Da Compra R$: ");
        double valorCompra = scan.nextDouble();

         if (valorRecebido >= valorCompra) {
             double troco = valorRecebido - valorCompra;
             System.out.println("Troco Do Cliente  R$:" + troco);

         }else{
            System.out.println("Esta Faltando Dinheiro: ");
         }           


    }
//---------------------------------------------------------------------------------------------------------------    
public static Scanner scan = new Scanner(System.in);
    public static void main(String[] args) {
    int option;

       do{ 
        System.out.println("-----------------------");
        System.out.println("Escolha uma opção: ");
        System.out.println("[1] Calcular Preço Total: ");
        System.out.println("[2] Calcular troco: ");
        System.out.println("[0] Sair");
        System.out.println("------------------------");
        option  = scan.nextInt();

        scan.nextLine();

            switch(option){
                case 1:
                    calcularPreco();
                break;

                case 2:
                    calcularTroco();
                break;

                case 0:
                    System.out.println("Saindo... Até Logo...");
                break;
                default:
                    System.out.println("Opção Invalida, Tente Novamente: ");
            } 

        }while(option != 0);
    }


}