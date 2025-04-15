package objetos;
import java.util.Arrays;
import java.util.Date;

public class Pedido {

    public int id;
    public Date dataCriacao;
    public Date dataPagamento;
    public Date dataVencimentoReserva;
    public Cliente cliente;
    public Vendedor vendedor;
    public Loja loja;
    public Item[] itens;
    public double[] valorTotal;

    public Pedido(int id, Date dataCriacao, Date dataPagamento, Date dataVencimentoReserva, Cliente cliente, Vendedor vendedor,
                  Loja loja, Item[] itens, double[] valorTotal) {
        this.id = id;
        this.dataCriacao = dataCriacao;
        this.dataPagamento = dataPagamento;
        this.dataVencimentoReserva = dataVencimentoReserva;
        this.cliente = cliente;
        this.vendedor = vendedor;
        this.loja = loja;
        this.itens = itens;
        this.valorTotal = valorTotal;
    }


    public void venda() {
        System.out.println("Data de Criação: " + dataCriacao + "\nValor Total do Pedido: " + Arrays.toString(valorTotal));
    }

}