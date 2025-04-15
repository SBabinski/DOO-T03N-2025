package Objeto;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Itens {
	public Integer id;
	private String nome;
	private String tipo;
	private double valor;
	private static List<Itens> produtos = new ArrayList<Itens>();
	private static Scanner scan = new Scanner(System.in);
	
	public Itens(Integer id, String nome, String tipo, double valor) {
		super();
		this.id = id;
		this.nome = nome;
		this.tipo = tipo;
		this.valor = valor;
	}
	
	public static void gerarDescricao(Integer id) {
		for (Itens produto : produtos) {
            if(produto.id == id) {
            	System.out.println("id de identificação " + produto.id + "\n" +
						"nome do produto " + produto.nome + "\n" +
						"tipo do produto " + produto.tipo + "\n" + 
						"valor do produto " + produto.valor + "\n"
            			);
            }
        }
				
	}
	
	
}
