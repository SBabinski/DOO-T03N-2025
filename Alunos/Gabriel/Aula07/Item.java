package Alunos.Gabriel.Aula07;

public class Item {

    private int id;
    private String nome;
    private String tipo;
    private Double valor;

    public Item(int id, String nome, String tipo, Double valor) {
        this.id = id;
        this.nome = nome;
        this.tipo = tipo;
        this.valor = valor;
    }

    public void gerarDescricao(){
        System.out.println(
        "ID:"+getId()+", "+
        "Nome:"+getNome()+", "+
        "Tipo:"+getTipo()+", "+
        "Valor:"+getValor()+".");

    }
    





    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getTipo() {
        return tipo;
    }

    public Double getValor() {
        return valor;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }









}

