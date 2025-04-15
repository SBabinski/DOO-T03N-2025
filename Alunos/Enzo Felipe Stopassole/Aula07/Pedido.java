import java.util.Date;
import java.util.List;

public class Pedido {
    private int id;
    private Date dataCriacao;
    private Date dataPagamento;
    private Date dataVencimentoReserva;
    private Cliente cliente;
    private Vendedor vendedor;
    private Loja loja;
    private List<Item> itens;

    public Pedido(int id, Date dataCriacao, Cliente cliente, Vendedor vendedor, 
                 Loja loja, List<Item> itens, int diasVencimento) {
        this.id = id;
        this.dataCriacao = dataCriacao;
        this.cliente = cliente;
        this.vendedor = vendedor;
        this.loja = loja;
        this.itens = itens;
        this.dataVencimentoReserva = new Date(dataCriacao.getTime() + (long) diasVencimento * 24 * 60 * 60 * 1000);
    }

    public double calcularValorTotal() {
        return itens.stream().mapToDouble(Item::getValor).sum();
    }

    public void gerarDescricaoVenda() {
        System.out.println("Pedido criado em: " + dataCriacao + 
                         ", Valor total: R$" + calcularValorTotal());
    }

    // Getters
    public Date getDataVencimentoReserva() { return dataVencimentoReserva; }
    public Date getDataPagamento() { return dataPagamento; }
    public void setDataPagamento(Date dataPagamento) { this.dataPagamento = dataPagamento; }
}