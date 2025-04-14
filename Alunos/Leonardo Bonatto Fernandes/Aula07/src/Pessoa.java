public class Pessoa {
    public String nome;
    public int idade;
    public Endereco endereco;
    
    public void apresentar() {
        System.out.println("Nome: " + nome + ", Idade: " + idade);
    }
}