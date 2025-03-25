import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CalcuadoraMelhor{

    public static void calcularPreco(){

        double desconto = 0;

        System.out.println("Informe Quantidade De Plantas: ");
        int quantidade = scan.nextInt();
        scan.nextLine();

        System.out.println("Informe Valor Unitario: ");
        double valorUnitario = scan.nextDouble();
        scan.nextLine();

        double valorTotal = quantidade * valorUnitario;
        System.out.println("Valor Total R$" + valorTotal);

        if (quantidade >= 10) {

            desconto = valorTotal * 0.05;
            valorTotal -= desconto;
            System.out.println("Desconto aplicado");
            System.out.println("Valor Total Da Compra com desconto R$ " + valorTotal );

        }else{
            System.out.println("Valor Total Da Compra R$ " + valorTotal );
        }

        Venda novaVenda = new Venda(quantidade, valorUnitario, valorTotal, desconto);
        vendas.add(novaVenda);
        System.out.println("Venda adicionada ao sistema...");
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


    //--------------------------------------------------------------------------------------------------------------------
    public static List<Venda> vendas = new ArrayList<>();

    public static class Venda{

        private int quantidade;
        private double valorUnitario;
        private double valorTotal;
        private double desconto;


        public Venda(int quantidade, double valorUnitario, double valorTotal, double desconto) {
            this.quantidade = quantidade;
            this.valorUnitario = valorUnitario;
            this.valorTotal = valorTotal;
            this.desconto = desconto;
        }


        public String toString() {
            return "Venda [quantidade_Plantas = R$" + quantidade + ", valor_Unitario_Planta = R$" + valorUnitario + ", valor_Total_Compra = R$" + valorTotal + ",desconto_Aplicado = R$" + desconto + "]";
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
        System.out.println("[3] Lista de Vendas");
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

                case 3:
                    for (Venda todaVenda : vendas) {
                        System.out.println(todaVenda);
                    }
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