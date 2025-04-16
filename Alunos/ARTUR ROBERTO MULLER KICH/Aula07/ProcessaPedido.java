import java.util.Date;

public class ProcessaPedido {

    public Pedido processar(int id, Cliente cliente, Vendedor vendedor, Loja loja, Item[] itens, Date dataVencimentoReserva) {
        Date agora = new Date();
        Pedido pedido = new Pedido(id, agora, null, dataVencimentoReserva, cliente, vendedor, loja, itens);

        if (confirmarPagamento(pedido)) {
            pedido.dataPagamento = agora;
            System.out.println("Pagamento confirmado.");
        } else {
            System.out.println("Reserva vencida. Pagamento não confirmado.");
        }

        return pedido;
    }

    private boolean confirmarPagamento(Pedido pedido) {
        Date agora = new Date();
        return !agora.after(pedido.dataVencimentoReserva);
    }
}
