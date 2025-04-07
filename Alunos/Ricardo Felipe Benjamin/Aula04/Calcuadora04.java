import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CalcuadoraMelhor {

    public static void calcularPreco() {
        int quantidade;
        int dia;
        int mes;
        double valorUnitario;
        double desconto = 0;


        do { 
        System.out.println("Digite o Dia: ");
        dia = scan.nextInt();
            if (dia <= 0 || dia > 30) {
                System.out.println("Dia invalido, digite novamente: ");     
            }
        } while (dia <= 0 || dia > 30);

        do { 
            System.out.println("Digite o Mes: (Informe o númerodo mes) ");
            mes = scan.nextInt();
                if (mes <= 0 || mes > 12) {
                    System.out.println("Mes invalido, digite novamente: ");    
                }
        } while(mes <= 0 || mes > 12);

        do {
            System.out.println("Informe Quantidade De Plantas: ");
            quantidade = scan.nextInt();
            scan.nextLine();
                if (quantidade <= 0) {
                    System.out.println("Numero invalido ");
                }
        } while (quantidade <= 0 );

        do { 
            System.out.println("Informe Valor Unitario R$: ");
            valorUnitario = scan.nextDouble();
            scan.nextLine();
                if (valorUnitario <= 0) {
                    System.out.println("VEREFIQUE O PREÇO: ");
                }
        } while(valorUnitario <= 0);

            valorTotal = quantidade * valorUnitario;
            System.out.println("Valor Total R$ " + valorTotal + "\n");
        
            if (quantidade >= 10) {

            desconto = valorTotal * 0.05;
            valorTotal -= desconto;

            valorAcumulado += valorTotal;

            System.out.println("Desconto aplicado!");
            System.out.println("Valor Total Da Compra com desconto R$ " + valorTotal + "\n" );

            } else {
            System.out.println("Valor Total Da Compra R$ " + valorTotal + "\n");
            }

            Venda novaVenda = new Venda(quantidade, valorUnitario, valorTotal, desconto, dia , mes);
            vendas.add(novaVenda);
            System.out.println("Venda adicionada ao sistema...\n");
        
    }
//-----------------------------------------------------------------------------------------------------------------------------------------
    public static void calcularTroco() {

        System.out.println("Quantos Reais o Cliente Deu ? ");
        double valorRecebido = scan.nextDouble();
        scan.nextLine();

         if (valorRecebido >= valorAcumulado ) {
             double troco = valorRecebido - valorAcumulado;
             System.out.println("Troco Do Cliente  R$: " + troco + "\n");

              valorAcumulado = 0.0;
              System.out.println("Venda resetada \n");
         } else {
             System.out.println("Esta Faltando Dinheiro, verifique quantos o cliente entregou: ");
         }               
    }

    public static void vendasMesDia() {

        System.out.println("Digite o dia: ");
        int dia = scan.nextInt();

        System.out.println("Digite o mes: ");
        int mes = scan.nextInt();

            for (Venda venda : vendas) {  
                if (venda.getDia() == dia && venda.getMes() == mes) {
                    System.out.println(venda);
                } else {
                    System.out.println("Nenhuma venda encontrada! \n");
                }
            }
    } 

    public static void vendasMesTotal() {
    int mesTotal = 0;

        System.out.println("Digite o mes: ");
        int mes = scan.nextInt();

            for (Venda venda : vendas) {
                if (venda.getMes() == mes) {
                     mesTotal += venda.getValorTotal();
                } else {
                    System.out.println("Nenhuma venda encontrada! ");
                }
            }
        System.out.println("Vendas do total do mes " + mes + " = R$" + mesTotal); 

    }
    //--------------------------------------------------------------------------------------------------------------------
   

    public static class Venda {

        private int quantidade;
        private double valorUnitario;
        private double valorTotal;
        private double desconto;
        private int dia;
        private int mes;


        public Venda(int quantidade, double valorUnitario, double valorTotal, double desconto, int dia, int mes) {
            this.quantidade = quantidade;
            this.valorUnitario = valorUnitario;
            this.valorTotal = valorTotal;
            this.desconto = desconto;
            this.dia = dia;
            this.mes = mes;
        }

        public int getQuantidade() {
            return quantidade;
        }
    
        public void setQuantidade(int quantidade) {
            this.quantidade = quantidade;
        }
    
        public double getValorUnitario() {
            return valorUnitario;
        }
    
        public void setValorUnitario(double valorUnitario) {
            this.valorUnitario = valorUnitario;
        }
    
        public double getValorTotal() {
            return valorTotal;
        }
    
        public void setValorTotal(double valorTotal) {
            this.valorTotal = valorTotal;
        }
    
        public double getDesconto() {
            return desconto;
        }
    
        public void setDesconto(double desconto) {
            this.desconto = desconto;
        }
    
        public int getDia() {
            return dia;
        }
    
        public void setDia(int dia) {
            this.dia = dia;
        }
    
        public int getMes() {
            return mes;
        }
    
        public void setMes(int mes) {
            this.mes = mes;
        }

        public String toString() {
            return "Venda [ quantidade Plantas:" + quantidade +
             " , valor Unitario Planta: R$ " + valorUnitario + 
             " , valor Total Compra: R$ " + valorTotal + 
             " , desconto Aplicado: R$ " + desconto + 
             " , dia: " + dia +
             " , mes: " + mes +" ]";
        }
    }


//---------------------------------------------------------------------------------------------------------------    
    public static double valorAcumulado = 0.0;
    public static double valorTotal = 0.0;
    public static List<Venda> vendas = new ArrayList<>();    
    public static Scanner scan = new Scanner(System.in);
    public static void main(String[] args) {    
    int option;

       do { 
        System.out.println("-------Bem vindo(a)-------");
        System.out.println("--------------------------");
        System.out.println("Escolha uma opção:                 ");
        System.out.println("[1] Calcular Preço Total:          ");
        System.out.println("[2] Calcular troco:                ");
        System.out.println("[3] Lista de Vendas:               ");
        System.out.println("[4] Pesquisa de venda pelo dia/mes:");
        System.out.println("[5] Venda total mes                ");
        System.out.println("[0] Sair                           ");
        System.out.println("--------------------------         ");
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
                    
                case 4:
                    vendasMesDia();
                break;

                case 5:
                    vendasMesTotal();
                break;
 
                case 0:
                    System.out.println("Saindo... Até Logo...");
                break;

                default:
                    System.out.println("Opção Invalida, Tente Novamente: \n");
            } 

        } while(option != 0);
    }


}