public class Resultado_ {
    private float preco_total;
    private int quantidade;
    private float valor_menor;

    public Resultado_(float preco_total, int quantidade,float valor_menor ){
        this.preco_total = preco_total;
        this.quantidade = quantidade;
        this.valor_menor = valor_menor;
    }

    public float getPreco_total() {
        return preco_total;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public float getValor_menor() {
        return valor_menor;
    }

    public void setPreco_total(float preco_total) {
        this.preco_total = preco_total;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public void setValor_menor(float valor_menor) {
        this.valor_menor = valor_menor;
    }

    @Override
    public String toString() {
        return "Resultado{" +
                "/Total: R$ " + preco_total+"/" +
                ",/Quantidade:" + quantidade+"/" +
                ",/Valor Descontado:" + valor_menor +"/"+
                '}';
    }

}


