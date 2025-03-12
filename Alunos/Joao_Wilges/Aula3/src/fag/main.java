package fag;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import objetos.Venda;

public class main {
    public static void main(String[] args) {
        boolean flag = true;
        Scanner scanner = new Scanner(System.in);
        Integer percent_desconto = 0;
        List<Venda> vendas = new ArrayList();
        
        scanner.nextLine();
        while(flag){
            System.out.println("""
                           [1] - Calcular Preço Total + Cadastrar Venda
                           [2] - Calcular Troco    
                           [3] - Ver Vendas  
                           [4] - Sair""" //
                    //
            );
            int opcao = scanner.nextInt();
            switch (opcao){
                case 1 -> {
                    System.out.print("Informe a quantidade vendida:");
                    int qtd_venda = scanner.nextInt();
                    System.out.print("Informe o valor do item vendido:");
                    float vlr_item = scanner.nextFloat();
                    if (qtd_venda > 10) {
                        percent_desconto = 5;
                    }
                    Float total_venda = calculaPrecoDesconto(qtd_venda, vlr_item,percent_desconto);
                    vendas.add(new Venda(qtd_venda, total_venda, percent_desconto));
                    System.out.println("Total da compra: R$" + total_venda);
                }
                case 2 -> {
                    System.out.print("Informe o valor pago pelo cliente:");
                    float vlr_pago = scanner.nextFloat();
                    System.out.print("Informe o valor total da compra:");
                    float vlr_total = scanner.nextFloat();
                    System.out.println("Troco: R$" + calculaTroco(vlr_pago, vlr_total));
                }
                case 3 -> {
                    System.out.println("Vendas:");
                    for (Venda venda : vendas){
                        System.out.println(venda.toString());
                    }
                }
                case 4 -> {
                    System.out.println("Finalizando sistema...");
                    flag = false;
                }
                default -> System.out.println("Opção inválida");
            }
        }
        scanner.close();
    }
    
    private static float calculaPrecoTotal(Integer quantidade_vendida, Float valor_item){
        return Math.round((quantidade_vendida * valor_item) * 100)/100;
    }

    private static float calculaPrecoDesconto(Integer quantidade_vendida, Float valor_item, Integer valor_desconto){
        float total = Math.round((quantidade_vendida * valor_item) * 100)/100;
        total = total - (total*valor_desconto/100);
        return total;
    }

    private static float calculaTroco(float valor_pago, float valor_total){
        return valor_pago - valor_total;
    }
}
