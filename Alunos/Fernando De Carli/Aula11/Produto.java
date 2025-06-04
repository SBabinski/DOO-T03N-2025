public class Produto {
    String nome;
    double preco;


    public Produto(String nome, double preco) {
        this.nome = nome;
        this.preco = preco;
    }

    @Override
    public String toString() {
        return "Produto{" +
                "nome = '" + nome + '\'' +
                ", preco = " + preco +
                '}';
    }
}