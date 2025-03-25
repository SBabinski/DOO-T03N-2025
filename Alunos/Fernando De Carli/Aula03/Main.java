import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static Scanner sc = new Scanner(System.in);
    private static List<historico> registroDeVendas = new ArrayList<>();

    public static void main(String[] args) {
        int op = 0;

        do {
            menu();
            op = sc.nextInt();
            switch (op) {
                case 1:
                    descontoEspecial();
                    break;
                case 2:
                    registroDeVendas();
                    break;
                case 3:
                    System.out.println("Encerrando...");
                    break;
            }
        } while (op != 3);
    }
    public static void menu(){
        System.out.println("------BEM-VINDO------\n");
        System.out.println("[1] - Desconto especial compras a cima de 10 quantidade\n");
        System.out.println("[2] - Registro de vendas recentes\n");
        System.out.println("[3] - Sair\n");
    }
    public static void descontoEspecial(){
        System.out.println("Quantas plantas esta comprando? \n");
        int quant = sc.nextInt();
        System.out.println("Qual valor unitario: \n");
        double valorUnitario = sc.nextDouble();

        historico Historico = new historico(quant,valorUnitario);
        registroDeVendas.add(Historico);

        System.out.println("\nVenda realizada com sucesso:");
        Historico.imprimir();
    }
    public static void registroDeVendas(){
        if(registroDeVendas.isEmpty()){
            System.out.println("Nenhuma venda feita!!");
        }else {
            System.out.println("Registro de vendas ");
            for(historico h : registroDeVendas){
                h.imprimir();
            }
        }
    }
}