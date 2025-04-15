import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Pedido {
    private final int id;
    private final Date dataCriacao;
    private Date dataPagamento;
    private final Date dataVencimentoReserva;
    private final Pessoa cliente;
    private final Pessoa vendedor;
    private final String loja;
    private final List<Item> itens;

    public Pedido(int id, Date dataCriacao, Date dataVencimentoReserva,
                  Pessoa cliente, Pessoa vendedor, String loja, List<Item> itens) {
        this.id = id;
        this.dataCriacao = dataCriacao;
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
        //  deixando bonito a escritaa

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        System.out.println("\nPedido criado em: " + dateFormat.format(dataCriacao));
        System.out.println("Data de Vencimento da Reserva: " + dateFormat.format(dataVencimentoReserva));
        System.out.println("Loja: " + loja);
        System.out.println("Cliente: " + cliente.nome);
        System.out.println("Vendedor: " + vendedor.nome);

        //  itens do pedido
        System.out.println("\nItens do Pedido:");
        for (Item item : itens) {
            item.gerarDescricao(); 
        }

        // Exibir o valor total do pedido
        System.out.println("\nValor Total: R$" + calcularValorTotal());
    }

    public Date getDataVencimentoReserva() {
        return dataVencimentoReserva;
    }

    public void setDataPagamento(Date dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public Date getDataPagamento() {
        return dataPagamento;
    }
}
