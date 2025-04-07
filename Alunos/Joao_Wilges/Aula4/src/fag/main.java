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
                           [3] - Analise de vendas  
                           [4] - Ver Vendas
                           [5] - Sair""" //
                    //
            );
            int opcao = scanner.nextInt();
            switch (opcao){
                case 1 -> {
                    System.out.print("Informe o número do mês: ");
                    int data_mes = scanner.nextInt();
                    System.out.print("Informe o dia: ");
                    int data_dia = scanner.nextInt();
                    System.out.print("Informe a quantidade vendida:");
                    int qtd_venda = scanner.nextInt();
                    System.out.print("Informe o valor do item vendido:");
                    float vlr_item = scanner.nextFloat();
                    if (qtd_venda > 10) {
                        percent_desconto = 5;
                    }
                    Float total_venda = calculaPrecoDesconto(qtd_venda, vlr_item,percent_desconto);
                    vendas.add(new Venda(qtd_venda, total_venda, percent_desconto, data_mes, data_dia));
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
                    System.out.println("** Gerencial Vendas **");
                    System.out.println("Informe o mês de consulta: ");
                    int data_consulta_mes = scanner.nextInt();
                    System.out.println("Informe o dia de consulta: ");
                    int data_consulta_dia = scanner.nextInt();
                    float total_vendas = 0;
                    for(Venda venda: vendas){
                        if(venda.getDataDia() == data_consulta_dia && venda.getDataMes() == data_consulta_mes){
                            total_vendas += venda.getValorVenda();
                        }
                    }
                    System.out.println("Total de vendas no dia " +
                                    data_consulta_dia +
                                    " e mês: " +
                                    data_consulta_mes +
                                    " R$: "+
                            total_vendas);
                }
                case 4 -> {
                    System.out.println("Vendas:");
                    for (Venda venda : vendas){
                        System.out.println(venda.toString());
                    }
                }
                case 5 -> {
                    System.out.println("Finalizando sistema...");
                    flag = false;
                }
                default -> System.out.println("Opção inválida");
            }
        }
        scanner.close();
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
