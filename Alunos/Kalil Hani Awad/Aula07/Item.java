public class Item {
    private int id;
    private String nome;
    private String tipo;
    private Double valor;
    private Double quantidade;
    private int dia;
    private int mes;
    private int ano;
    public Item(int id, String nome, String tipo, Double valor, Double quantidade, int dia, int mes, int ano) {
        this.id = id;
        this.nome = nome;
        this.tipo = tipo;
        this.valor = valor;
        this.quantidade = quantidade;
        this.dia = dia;
        this.mes = mes;
        this.ano = ano;
    }

    public int getDia() {
        return dia;
    }

    public int getMes() {
        return mes;
    }

    public int getAno() {
        return ano;
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

    public Double getQuantidade() {
        return quantidade;
    }

    public void gerarDescricao(){
        System.out.println("id do item:"+id +"," +"nome:"+nome +"," +"tipo:"+tipo +"," +"valor:" +valor);
    }

}