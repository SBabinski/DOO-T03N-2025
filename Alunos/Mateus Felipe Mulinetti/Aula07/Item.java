package objetos;

public class Item {
    int id;
    public String nome;
    public String tipo;
    public double valor;

    public Item(int id, String nome, String tipo, double valor) {
        this.id = id;
        this.nome = nome;
        this.tipo = tipo;
        this.valor = valor;
    }

    public void descricaoItem() {
        System.out.println("Item numero: " + id + ", Nome: " + nome + ", Tipo: " + tipo + ", Valor: R$ " + valor);
    }
}