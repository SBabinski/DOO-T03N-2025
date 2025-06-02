package org.example;

public class Pessoa {

    private String name;
    private int Idade;
    private int cpf;
    private Maratona seriando;

    public Pessoa(String nome, int idade, int cpf) {
        this.name = nome;
        Idade = idade;
        this.cpf = cpf;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getIdade() {

        return Idade;
    }

    public int getCpf() {

        return cpf;
    }

    public void setNome(String name) {

        this.name = name;
    }

    public void setIdade(int idade) {

        Idade = idade;
    }

    public void setCpf(int cpf) {

        this.cpf = cpf;
    }

    @Override
    public String toString() {
        return "Alguem{" +
                "nome='" + name + '\'' +
                ", Idade=" + Idade +
                ", cpf=" + cpf +
                '}';
    }

}
