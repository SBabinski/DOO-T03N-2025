public class Item {
    private final int id;
    private final String nome;
    private final String tipo;
    private final double valor;

    public Item(int id, String nome, String tipo, double valor) {
        this.id = id;
        this.nome = nome;
        this.tipo = tipo;
        this.valor = valor;
    }

  
    public void gerarDescricao() {
        System.out.println("ID: " + id + " | Nome: " + nome + " | Tipo: " + tipo + " | Valor: R$" + valor);
    }

    public double getValor() {
        return valor;
    }
}
