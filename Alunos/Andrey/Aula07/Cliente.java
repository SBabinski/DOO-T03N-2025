package Objeto;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Cliente {
	private String nome;
	private Integer idade;
	private String cidade;
	private String bairro;
	private String rua;
	private static List<Cliente> cliente = new ArrayList<Cliente>();
	private static Scanner scan = new Scanner(System.in);
	
	
	public static void contarCliente() {
		Integer contador = 0;
		for (Cliente cliente : cliente) {
            contador = contador + 1;
        }
		System.out.println("temos " + contador + " clientes \n");
	}
	
	public static void addCliente() {
		System.out.println("informe nome do cliente \n");
		String nome = scan.next();
		System.out.println("informe idade do cliente \n");
		Integer idade = scan.nextInt();
		System.out.println("informe cidade do cliente \n");
		String cidade = scan.next();
		System.out.println("informe bairro do cliente \n");
		String bairro = scan.next();
		System.out.println("informe rua do cliente \n");
		String rua = scan.next();
		cliente.add(new Cliente(nome, idade, cidade, bairro, rua));
		
	}
	
	public static void mostrarCliente() {
		System.out.println("digite nome do cliente");
		String nome = scan.next();
		for (Cliente cliente : cliente) {
            if(cliente.nome.equalsIgnoreCase(nome)) {
            	System.out.println("nome do cliente " + cliente.nome + "\n"
            			+ "idade do cliente " + cliente.idade + "\n"
            			+ "cidade do cliente " + cliente.cidade);
            }
        }
	}
	
	public Cliente(String nome, Integer idade, String cidade, String bairro, String rua) {
		super();
		this.nome = nome;
		this.idade = idade;
		this.cidade = cidade;
		this.bairro = bairro;
		this.rua = rua;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Integer getIdade() {
		return idade;
	}
	public void setIdade(Integer idade) {
		this.idade = idade;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getRua() {
		return rua;
	}
	public void setRua(String rua) {
		this.rua = rua;
	}

	@Override
	public String toString() {
		return "Cliente [nome=" + nome + ", idade=" + idade + ", cidade=" + cidade + ", bairro=" + bairro + ", rua="
				+ rua + "]";
	}
	
	
}
