package Fag;

import java.util.Scanner;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Calculadora {
    static List<Venda> registroVendas = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int opcao;

        do {
        	System.out.println("--------------------------------");
            System.out.println("1 - Calcular Preço Total");
            System.out.println("2 - Calcular Troco");
            System.out.println("3 - Registro de Vendas");
            System.out.println("4-Ver Vendas Por Data");
            System.out.println("5 - Sair");
            System.out.println("--------------------------------\n");
            opcao = scan.nextInt();

            switch (opcao) {
                case 1:
                    calcularPrecoTotal(scan);
                    break;
                case 2:
                    calcularTroco(scan);
                    break;
                case 3:
                    registrarVenda();
                    break;
                case 4:
                    verVendasPorData(scan);
                    break;
                case 5:
                	System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida! ");
            }
        } while (opcao != 5);
        
        scan.close();
    }

    private static void calcularPrecoTotal(Scanner scan) {
        System.out.println("Qual o nome da planta?");
        String nome = scan.next();
        System.out.println("Quantas unidades?");
        int unid = scan.nextInt();
        System.out.println("Qual o preço da unidade?");
        float preco = scan.nextFloat();

        float precoTotal = unid * preco;
        float desc = 0;

        if (unid > 10) {
            desc = precoTotal * 0.05f; 
            precoTotal -= desc; 
            System.out.printf("O valor da compra com 5%% de desconto: $%.2f\n", precoTotal);
        } else {
            System.out.printf("O valor de %d %s é: $%.2f\n", unid, nome, precoTotal);
        }

        registroVendas.add(new Venda(nome, unid, preco, desc, precoTotal, LocalDate.now()));
    }

    private static void calcularTroco(Scanner scan) {
        System.out.println("Valor total da compra?");
        float total = scan.nextFloat();
        System.out.println("Valor recebido?");
        float recebido = scan.nextFloat();

        if (recebido < total) {
            System.out.println("Valor insuficiente! ");
        } else {
            float troco = recebido - total;
            System.out.printf("O valor do troco é: $%.2f\n", troco);
        }
    }

    private static void registrarVenda() {
    	System.out.println("--------------------------------\n");
        System.out.println("Registro de vendas: ");
        if (registroVendas.isEmpty()) {
            System.out.println("Nenhuma venda registrada.");
        } else {
            for (Venda venda : registroVendas) {
                System.out.printf("Planta: %s\nUnidades: %d\nPreço Unitário: $%.2f\nDesconto: $%.2f\nPreço Total: $%.2f\n",
                        venda.nome, venda.unid, venda.preco, venda.desc, venda.precoTotal);
            }
        }
    }
    
    private static void verVendasPorData(Scanner scan) {
    	System.out.println("Qual o mês? 1-12");
    	int mes = scan.nextInt();
    	System.out.println("Qual o dia? 1-31 ou 0 Para o mês todo:");
    	int dia = scan.nextInt();
    	
    	List<Venda> vendasFiltradas;
    	 if(dia == 0) {
    		 vendasFiltradas = registroVendas.stream().filter(v -> v.data.getMonthValue() == mes).collect(Collectors.toList());
    	 }else {
    		 vendasFiltradas = registroVendas.stream()
                     .filter(v -> v.data.getMonthValue() == mes && v.data.getDayOfMonth() == dia).collect(Collectors.toList());
    	 }
    	 
    	 if(vendasFiltradas.isEmpty()) {
    		 System.out.println("Sem vendas registradas nessa data!");
    		 System.out.println("-----------------------------------------------\n");
    	 }else {
    		 System.out.println("--------------------------------\n");
    		 System.out.println("Vendas registradas: ");
    		 for(Venda venda : vendasFiltradas ) {
    			 System.out.printf("Data: %s\n Planta: %s\n Unidades: %s\n Preço Total: %.2f\n", venda.data, venda.nome, venda.unid, venda.precoTotal);
    			 System.out.println("-------------------------------------------\n");
    		 }
    	 }
    }
}
