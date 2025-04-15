package objetos;

public class Cliente {
    public String nome;
    int idade;
    Endereco endereco;

    public Cliente(String nome, int idade, Endereco enderecoCliente) {
        this.nome = nome;
        this.idade = idade;
    }

    public void apresentarCliente() {
        System.out.println("Nome: " + nome + ", Idade: " + idade);
    }
}