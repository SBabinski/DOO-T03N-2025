
public class Venda {
    private int quantidade;
    private double valorUnitario;
    private double valorTotal;
    private double descontoAplicado;

    public Venda(int quantidade, double valorUnitario) {
        this.quantidade = quantidade;
        this.valorUnitario = valorUnitario;

        double valorBruto = quantidade * valorUnitario;

        if (quantidade > 10) {
            this.descontoAplicado = valorBruto * 0.05; 
        } else {
            this.descontoAplicado = 0;
        }

        this.valorTotal = valorBruto - this.descontoAplicado;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public double getValorUnitario() {
        return valorUnitario;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public double getDescontoAplicado() {
        return descontoAplicado;
    }

    public void exibirVenda() {
        System.out.println("Quantidade: " + quantidade);
        System.out.println("Valor unitário: R$ " + valorUnitario);
        System.out.println("Desconto aplicado: R$ " + descontoAplicado);
        System.out.println("Valor total da venda: R$ " + valorTotal);
        System.out.println("-----------------------------------");
    }
}