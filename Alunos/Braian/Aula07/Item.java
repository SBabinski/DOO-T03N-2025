package Alunos.Braian.Aula07;

public class Item {
  private final int id;
  private final String nome;
  private final String tipo;
  private final double valor;

  public Item(int id, String nome, String tipo, double valor) {
    this.id = id;
    this.nome = nome;
    this.tipo = tipo;
    this.valor = valor;
  }

  public int getId(){
    return id;
  }
  public String getNome() {
    return nome;
  }
  public String getTipo(){
    return tipo;
  }
  public double getValor(){
    return valor;
  }
  public void gerarDescricao() {
    System.out.println("Item [ID: " + this.id + ", Nome: " + this.nome + ", Tipo: " + this.tipo + ", Valor: R$" + this.valor + "]");
  }
}
