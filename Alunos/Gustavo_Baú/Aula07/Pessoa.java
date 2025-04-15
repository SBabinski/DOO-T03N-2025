package Alunos.Gustavo_Baú.Aula07;

public class Pessoa {
    
    protected String nome;
    protected int idade;
    protected Endereco endereco;
    
    public Pessoa(String nome, int idade, Endereco endereco) {
        this.nome = nome;
        this.idade = idade;
        this.endereco = endereco;
    }
    
    public void seApresentar() {
        System.out.println("Olá, meu nome é " + nome + " e tenho " + idade + " anos.");
    }
}