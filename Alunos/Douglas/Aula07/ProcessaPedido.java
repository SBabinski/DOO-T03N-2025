import java.util.Date;

public class ProcessaPedido {
    public Pedido processar(int idPedido, Cliente cliente, Vendedor vendedor, Loja loja, Item[] itens,
                            Date dataCriacao, Date dataPagamento, Date dataVencimentoReserva) {

        if (confirmarPagamento(dataPagamento, dataVencimentoReserva)) {
            Pedido pedido = new Pedido(idPedido, dataCriacao, dataPagamento, dataVencimentoReserva, cliente, vendedor, loja, itens);
            return pedido;
        } else {
            System.out.println("Pagamento não confirmado! A reserva está vencida.");
            return null;
        }
    }

    private boolean confirmarPagamento(Date dataPagamento, Date dataVencimentoReserva) {
        return !dataPagamento.after(dataVencimentoReserva);
    }
}
