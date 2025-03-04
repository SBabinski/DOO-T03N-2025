package fag;

import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        System.out.println("[1] - Calcular Preço Total\n" + //
                            "[2] - Calcular Troco    \n" + //
                            "[3] - Sair  ");
        Scanner scanner = new Scanner(System.in);
        int opcao = scanner.nextInt();
        scanner.nextLine();

        switch (opcao){
            case 1:
                System.out.print("Informe a quantidade vendida:");
                int qtd_venda = scanner.nextInt();
                System.out.print("Informe o valor do item vendido:");
                float vlr_item = scanner.nextFloat();
                System.out.println("Total da compra: R$" + calculaPrecoTotal(qtd_venda, vlr_item));
                break;
            case 2:
                System.out.print("Informe o valor pago pelo cliente:");
                float vlr_pago = scanner.nextFloat();
                System.out.print("Informe o valor total da compra:");
                float vlr_total = scanner.nextFloat();
                System.out.println("Troco: R$" + calculaTroco(vlr_pago, vlr_total));
                break;
            case 3:
                System.out.println("Finalizando sistema...");
                break;
            default:
                System.out.println("Opção inválida");
                break;
        }
        scanner.close();
    }
    
    private static float calculaPrecoTotal(Integer quantidade_vendida, Float valor_item){
        return Math.round((quantidade_vendida * valor_item) * 100)/100;
    }
    private static float calculaTroco(float valor_pago, float valor_total){
        return valor_pago - valor_total;
    }
}
