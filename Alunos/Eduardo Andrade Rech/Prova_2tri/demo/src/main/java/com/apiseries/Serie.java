package com.apiseries;

import java.util.List;

public class Serie {
    int id;
    String nome;
    String idioma;
    List<String> generos;
    Double nota;
    String estado;
    String data_estreia, data_termino;
    String emissora;

    public Serie(int id, String nome, String idioma, List<String> generos, Double nota, String estado,
            String data_estreia, String data_termino, String emissora) {
        this.id = id;
        this.nome = nome;
        this.idioma = idioma;
        this.generos = generos;
        this.nota = nota;
        this.estado = estado;
        this.data_estreia = data_estreia;
        this.data_termino = data_termino;
        this.emissora = emissora;
    }

    public Serie() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public List<String> getGeneros() {
        return generos;
    }

    public void setGeneros(List<String> generos) {
        this.generos = generos;
    }

    public Double getNota() {
        return nota;
    }

    public void setNota(Double nota) {
        this.nota = nota;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getData_estreia() {
        return data_estreia;
    }

    public void setData_estreia(String data_estreia) {
        this.data_estreia = data_estreia;
    }

    public String getData_termino() {
        return data_termino;
    }

    public void setData_termino(String data_termino) {
        this.data_termino = data_termino;
    }

    public String getEmissora() {
        return emissora;
    }

    public void setEmissora(String emissora) {
        this.emissora = emissora;
    }

    public boolean printarSerie() {
        System.out.println("Nome: " + this.getNome());
        System.out.println("Idioma: " + this.getIdioma());
        System.out.println("Generos: ");
        for (String a : this.getGeneros()) {
            System.out.println("    * " + a);
        }
        System.out.println("Nota: " + this.getNota());
        System.out.println("Estado: " + this.getEstado());
        System.out.println("Data de estreia: " + this.getData_estreia());
        System.out.println("Data de termino: " + this.getData_termino());
        System.out.println("Emissora: " + this.getEmissora());
        return true;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
