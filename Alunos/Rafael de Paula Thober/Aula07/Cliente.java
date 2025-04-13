public class Cliente extends Pessoa {
  public Cliente(String nome, int idade, Endereco endereco) {
      this.nome = nome;
      this.idade = idade;
      this.endereco = endereco;
  }

  @Override
  public void apresentarse() {
      System.out.println("Cliente: " + nome + ", Idade: " + idade);
      endereco.apresentarLogradouro();
  }
}
