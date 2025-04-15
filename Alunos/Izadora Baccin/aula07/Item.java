package fag;

public class Item {
	 int id;
	 String nome;
	 String tipo;
	 double valor;
	 
	 public Item(int id, String nome, String tipo, double valor) {
		 this.id = id;
		 this.nome = nome;
		 this.tipo = tipo;
		 this.valor = valor;
	 }
	 
	 public void gerarDescricao() {
		 System.out.println("Id \n"+id);
		 System.out.println("Nome: \n"+nome);
		 System.out.println("Tipo: \n"+tipo); 
		 System.out.printf("Valor: $%.2f \n",valor);  
	 }
}
