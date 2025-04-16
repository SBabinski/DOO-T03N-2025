public class Item {
    int id;
    String nome;
    String tipo;
    float valor;

    public Item(int id, String nome, String tipo, float valor) {
        this.id = id;
        this.nome = nome;
        this.tipo = tipo;
        this.valor = valor;
    }

    public void gerarDescricao() {
        System.out.println("ID: " + id + " | Nome: " + nome + " | Tipo: " + tipo + " | Valor: R$" + valor);
    }
}
