package com.example;

import java.util.Scanner;

public class Usuario {
    private final String nome;

    public Usuario(Scanner scan) {

        System.out.print("Digite seu nome ou apelido: ");
        this.nome = scan.nextLine();
    }

    public String getNome() {
        return nome;
    }
}
