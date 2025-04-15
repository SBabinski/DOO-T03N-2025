public class Item {
    private int id;
    private String nome;
    private String tipo;
    private double valor;

    public Item(int id, String nome, String tipo, double valor) {
        this.id = id;
        this.nome = nome;
        this.tipo = tipo;
        this.valor = valor;
    }

    public void gerarDescricao() {
        System.out.printf("Item ID: %d - %s (%s) - R$ %.2f%n", id, nome, tipo, valor);
    }

    // Getters and setters
    public int getId() { return id; }
    public String getNome() { return nome; }
    public String getTipo() { return tipo; }
    public double getValor() { return valor; }
    public void setId(int id) { this.id = id; }
    public void setNome(String nome) { this.nome = nome; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public void setValor(double valor) { this.valor = valor; }
}