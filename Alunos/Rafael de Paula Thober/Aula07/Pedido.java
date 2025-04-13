import java.util.Date;
import java.util.List;

public class Pedido {
    int id;
    Date dataCriacao;
    Date dataPagamento;
    Date dataVencimentoReserva;
    Cliente cliente;
    Vendedor vendedor;
    String loja;
    List<Item> itens;

    public Pedido(int id, Date criacao, Date vencimento, Cliente cliente, Vendedor vendedor, String loja, List<Item> itens) {
        this.id = id;
        this.dataCriacao = criacao;
        this.dataVencimentoReserva = vencimento;
        this.cliente = cliente;
        this.vendedor = vendedor;
        this.loja = loja;
        this.itens = itens;
    }

    public double calcularValorTotal() {
        return itens.stream().mapToDouble(item -> item.valor).sum();
    }

    public void gerarDescricaoVenda() {
        System.out.printf("Pedido #%d criado em %s - Total: R$ %.2f%n",
                id, dataCriacao.toString(), calcularValorTotal());
    }
}
