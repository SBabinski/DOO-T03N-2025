package src;

public class Produtos {
    private String Nome;
    private Double Valor;

    public Produtos(String Nome, Double Valor){
        this.Nome = Nome;
        this.Valor = Valor;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public Double getValor() {
        return Valor;
    }

    public void setValor(Double valor) {
        Valor = valor;
    }
    @Override
    public String toString() {
        return Nome + "- $" + String.format("%.2f",Valor);
    }

}
