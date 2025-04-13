public class Vendedor extends Pessoa {
  String loja;
  double salarioBase;
  double[] salarioRecebido;

  public Vendedor(String nome, int idade, Endereco endereco, String loja, double salarioBase, double[] salarios) {
      this.nome = nome;
      this.idade = idade;
      this.endereco = endereco;
      this.loja = loja;
      this.salarioBase = salarioBase;
      this.salarioRecebido = salarios;
  }

  @Override
  public void apresentarse() {
      System.out.println("Vendedor: " + nome + ", Idade: " + idade + ", Loja: " + loja);
      endereco.apresentarLogradouro();
  }

  public double calcularMedia() {
      double soma = 0;
      for (double s : salarioRecebido) soma += s;
      return soma / salarioRecebido.length;
  }

  public double calcularBonus() {
      return salarioBase * 0.2;
  }
}
