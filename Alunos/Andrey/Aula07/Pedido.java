package Objeto;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Pedido {
	private Integer id;
	private Integer dataCriacao;
	private Integer dataPagamento;
	private Integer dataVencimento;
	private String cliente;
	private String vendedor;
	private Integer loja;
	private static List<Pedido> pedido = new ArrayList<Pedido>();
	private static Scanner scan = new Scanner(System.in);
	
	public Pedido(Integer id, Integer dataCriacao, Integer dataPagamento, Integer dataVencimento, String cliente,
			String vendedor, Integer loja) {
		super();
		this.id = id;
		this.dataCriacao = dataCriacao;
		this.dataPagamento = dataPagamento;
		this.dataVencimento = dataVencimento;
		this.cliente = cliente;
		this.vendedor = vendedor;
		this.loja = loja;
	}
	
	public static void calcularValorTotal() {
		Integer resp = 1;
		System.out.println("digite o id do pedido \n");
		Integer idPed = scan.nextInt();
		System.out.println("digite data de criaçao do pedido \n");
		Integer dataCria = scan.nextInt();
		System.out.println("digite data de vencimento \n");
		Integer dataVenci = scan.nextInt();
		System.out.println("digite nome do cliente \n");
		String cliente = scan.next();
		System.out.println("digite nome do vendedor \n");
		String vendedor = scan.next();
		System.out.println("digite loja da venda \n");
		Integer loja = scan.nextInt();
			while(resp != 2) {
				System.out.println("digite o id do produto que deseja adicionar no pedido \n");
				Integer id = scan.nextInt();
				
		}
	}
	
	public static void gerarDescVenda() {
		System.out.println("digite o id do pedido \n");
		Integer idPed = scan.nextInt();
		for (Pedido pedido : pedido) {
            if(pedido.id == idPed) {
            	System.out.println("data de criaçao do pedido " + pedido.dataCriacao + "\n");
            }
        }
	}
	
}
