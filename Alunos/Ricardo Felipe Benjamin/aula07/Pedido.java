import java.util.List;
import java.util.Date;

public class Pedido {

    public Integer id;
    public Date dataCriacao;
    public Date dataPagamento;
    public Date dataVencimentoReserva;
    public Pessoa cliente;
    public Pessoa vendedor;
    public String loja;
    public List<Item> itens;
    
    public Pedido(int id, Date dataCriacao, Date dataPagamento, Date dataVencimentoReserva,
    Pessoa cliente, Pessoa vendedor, String loja, List<Item> itens) {
        this.id = id;
        this.dataCriacao = dataCriacao;
        this.dataPagamento = dataPagamento;
        this.dataVencimentoReserva = dataVencimentoReserva;
        this.cliente = cliente;
        this.vendedor = vendedor;
        this.loja = loja;
        this.itens = itens;
    }

    public double calcularValorTotal() {
        double total = 0;
        for (Item item : itens) {
            total += item.getValor();
        }
        return total;
    }

    public void gerarDescricaoVenda() {
        System.out.println("Pedido criado em: " + dataCriacao + " | Total: R$" + calcularValorTotal());
    }

    public Date getDataVencimentoReserva() {
        return dataVencimentoReserva;
    }
}
