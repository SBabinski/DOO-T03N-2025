package Objetos;

public class Cliente {
    public String nome;
    public int idade;
    public String cidade;
    public String bairro;
    public String rua;

    public Cliente(String nome, int idade, String cidade, String bairro, String rua) {
        this.nome = nome;
        this.idade = idade;
        this.cidade = cidade;
        this.bairro = bairro;
        this.rua = rua;
    }

    public void apresentarCliente() {
        System.out.println("Nome: " + this.nome + "\nIdade: " + this.idade);
    }
}