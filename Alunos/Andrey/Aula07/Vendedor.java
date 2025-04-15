package Objeto;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Vendedor {
	protected String nome;
	protected Integer idade;
	protected Integer loja;
	protected String cidade;
	protected String bairro;
	protected String rua;
	protected double salarioBase;
	protected double salarioRece;
	private static List<Vendedor> vendedor = new ArrayList<Vendedor>();
	private static Scanner scan = new Scanner(System.in);
	
	
	public Vendedor(String nome, Integer idade, Integer loja, String cidade, String bairro, String rua,
			double salarioBase, double salarioRece) {
		super();
		this.nome = nome;
		this.idade = idade;
		this.loja = loja;
		this.cidade = cidade;
		this.bairro = bairro;
		this.rua = rua;
		this.salarioBase = salarioBase;
		this.salarioRece = salarioRece;
	}
	
	public static void contarVendedor() {
		Integer contador = 0;
		for (Vendedor vendedor : vendedor) {
            contador = contador + 1;
        }
		System.out.println("temos " + contador + " vendedores \n");
	}
	
	public static void addVendedor() {
		System.out.println("informe nome do vendedor \n");
		String nome = scan.next();
		System.out.println("informe idade do vendedor \n");
		Integer idade = scan.nextInt();
		System.out.println("informe loja de trabalho do vendedor \n");
		Integer loja = scan.nextInt();
		System.out.println("informe cidade do vendedor \n");
		String cidade = scan.next();
		System.out.println("informe bairro do vendedor \n");
		String bairro = scan.next();
		System.out.println("informe rua do vendedor \n");
		String rua = scan.next();
		System.out.println("informe salario base do vendedor \n");
		double salarioBase = scan.nextDouble();
		Integer resp = 0;
		Integer cont = 0;
		while(resp != 2 && cont <= 3) {
			System.out.println("informe salario recebido \n");
			double salarioRece = scan.nextDouble();
			cont = cont ++;
			vendedor.add(new Vendedor(nome, idade, loja, cidade, bairro, rua, salarioBase, salarioRece));
			System.out.println("deseja add mais um salario recebido 1 - sim 2 - nao");
			resp = scan.nextInt();
		}
	}
	
	public static double calcularMediaSalarial(String nome) {
		double somaSalarios = 0;
		Integer cont = 0;
		for (Vendedor vendedor : vendedor) {
            if(vendedor.nome.equalsIgnoreCase(nome)) {
            	somaSalarios = somaSalarios + vendedor.salarioRece;
            	cont = cont + 1;
            }
        }
		return somaSalarios/cont;
	}
	
	private static double calcularBonus(String nome) {
		double bonus = 0;
		for (Vendedor vendedor : vendedor) {
            if(vendedor.nome.equalsIgnoreCase(nome)) {
            	 bonus = vendedor.salarioBase*0.2;
            	
            }
        } 
		return bonus;
	}
	
	public static void mostrarVendedor() {
		System.out.println("digite nome do vendedor \n");
		String nome = scan.next();
		for (Vendedor vendedor : vendedor) {
            if(vendedor.nome.equalsIgnoreCase(nome)) {
            	System.out.println("nome do vendedor " + vendedor.nome + "\n"
            			+ "idade do vendedor " + vendedor.idade + "\n "
            			+ "loja do vendedor " + vendedor.loja);
            }
        }
	}
	
	public static void apresentarMediaAndBonus() {
		System.out.println("digite nome do vendedor \n");
		String nome = scan.next();
		double media = calcularMediaSalarial(nome);
		double bonus = calcularBonus(nome);
		System.out.println("media salarial " + media + "\n"
				+ "bonus de salario pela meta " + bonus);
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
	public Integer getLoja() {
		return loja;
	}
	public void setLoja(Integer loja) {
		this.loja = loja;
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
	public double getSalarioBase() {
		return salarioBase;
	}
	public void setSalarioBase(double salarioBase) {
		this.salarioBase = salarioBase;
	}
	public double getSalarioRece() {
		return salarioRece;
	}
	public void setSalarioRece(double salarioRece) {
		this.salarioRece = salarioRece;
	}

	@Override
	public String toString() {
		return "Vendedor [nome=" + nome + ", idade=" + idade + ", loja=" + loja + ", cidade=" + cidade + ", bairro="
				+ bairro + ", rua=" + rua + ", salarioBase=" + salarioBase + ", salarioRece=" + salarioRece + "]";
	}
	
	
	
}
