package fag;

public class Gerente {
	String nome;
	int idade;
	String loja;
	String cidade;
	String bairro;
	String rua;
	double salarioBase;
	double[] salarioRecebido = {3600.00, 3900.00, 4000.00}; 
	Endereco endereco;
	
	public Gerente(String nome, int idade, String loja, String cidade, String bairro, String rua, double salarioBase, Endereco endereco) {
	    this.nome = nome;
	    this.idade = idade;
	    this.loja = loja;
	    this.cidade = cidade;
	    this.bairro = bairro;
	    this.rua = rua;
	    this.salarioBase = salarioBase;
	    this.endereco = endereco;
	}
	
	public double calcularMedia() {
		double soma = 0;
		int cont = 0;
		  for(double sal : salarioRecebido) {
			    soma += sal; 
			    cont ++;
		  }
		  return soma/cont; 
	}
	
	public double calcularBonus() {
		return salarioBase*0.35;  
	}
	
	public void apresentarSe() {
		System.out.println("Nome: \n"+nome);
		System.out.println("Idade: \n"+idade);
		System.out.println("Loja: "+loja);
		endereco.apresentarLogradouro();
	}
}
