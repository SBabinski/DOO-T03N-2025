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

    public Pedido(int id, Date dataCriacao, Cliente cliente, Vendedor vendedor, Loja loja, List<Item> itens) {
        this.id = id;
        this.dataCriacao = dataCriacao;
        this.cliente = cliente;
        this.vendedor = vendedor;
        this.loja = loja;
        this.itens = itens;
        this.dataVencimentoReserva = new Date(dataCriacao.getTime() + (3L * 24 * 60 * 60 * 1000));
    }

    public double calcularValorTotal() {
        double total = 0;
        for (Item item : itens) {
            total += item.getValor();
        }
        return total;
    }

    public void gerarDescricaoVenda() {
        System.out.printf("Pedido criado em %s - Valor total: R$ %.2f%n", dataCriacao, calcularValorTotal());
    }

    // Getters
    public List<Item> getItens() {
        return this.itens;
    }

    public Date getDataVencimentoReserva() {
        return dataVencimentoReserva;
    }

    public void setDataPagamento(Date dataPagamento) {
        this.dataPagamento = dataPagamento;
    }
    
    public int getId() {
        return this.id;
    }
}