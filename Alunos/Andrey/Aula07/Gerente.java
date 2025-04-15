package Objeto;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Gerente extends Vendedor {
	private static Scanner scan = new Scanner(System.in);
	private static List<Gerente> gerente = new ArrayList<Gerente>();
	
	public Gerente(String nome, Integer idade, Integer loja, String cidade, String bairro, String rua,
			double salarioBase, double salarioRece) {
		super(nome, idade, loja, cidade, bairro, rua, salarioBase, salarioRece);
	}
	
	public static void addGerente() {
		System.out.println("informe nome do gerente \n");
		String nome = scan.next();
		System.out.println("informe idade do gerente \n");
		Integer idade = scan.nextInt();
		System.out.println("informe loja de trabalho do gerente \n");
		Integer loja = scan.nextInt();
		System.out.println("informe cidade do gerente \n");
		String cidade = scan.next();
		System.out.println("informe bairro do gerente \n");
		String bairro = scan.next();
		System.out.println("informe rua do gerente \n");
		String rua = scan.next();
		System.out.println("informe salario base do gerente \n");
		double salarioBase = scan.nextDouble();
		Integer resp = 0;
		Integer cont = 0;
		while(resp != 2 && cont <= 3) {
			System.out.println("informe salario recebido \n");
			double salarioRece = scan.nextDouble();
			cont = cont ++;
			gerente.add(new Gerente(nome, idade, loja, cidade, bairro, rua, salarioBase, salarioRece));
			System.out.println("deseja add mais um salario recebido 1 - sim 2 - nao");
			resp = scan.nextInt();
		}
	}

	public static double calcularBonusGerente(String nome) {
		double bonus = 0;
		for (Gerente gerente : gerente) {
            if(gerente.nome.equalsIgnoreCase(nome)) {
            	 bonus = gerente.salarioBase*0.35;
            }
        } 
		return bonus;
	}
	
	public static double calcularMediaSalarial(String nome) {
		double somaSalarios = 0;
		Integer cont = 0;
		for (Gerente gerente : gerente) {
            if(gerente.nome.equalsIgnoreCase(nome)) {
            	somaSalarios = somaSalarios + gerente.salarioRece;
            	cont = cont + 1;
            }
        }
		return somaSalarios/cont;
	}
	
	public static void mostrarGerente() {
		System.out.println("digite nome do vendedor \n");
		String nome = scan.next();
		for (Gerente gerente : gerente) {
            if(gerente.nome.equalsIgnoreCase(nome)) {
            	System.out.println("nome do vendedor " + gerente.nome + "\n"
            			+ "idade do vendedor " + gerente.idade + "\n "
            			+ "loja do vendedor " + gerente.loja);
            }
        }
	}
	
	public static void apresentarMediaAndBonus() {
		System.out.println("digite nome do vendedor \n");
		String nome = scan.next();
		double media = calcularMediaSalarial(nome);
		double bonus = calcularBonusGerente(nome);
		System.out.println("media salarial " + media + "\n"
				+ "bonus de salario pela meta " + bonus);
	}
}
