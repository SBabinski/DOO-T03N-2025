package fag;

public class Cliente {
    String nome;
    int idade;
    Endereco endereco;

    public Cliente(String nome, int idade, Endereco endereco) {
        this.nome = nome;
        this.idade = idade;
        this.endereco = endereco;
    }

    public void apresentarSe() {
        System.out.println("Cliente: " + nome);
        System.out.println("Idade: " + idade);
    }
}
