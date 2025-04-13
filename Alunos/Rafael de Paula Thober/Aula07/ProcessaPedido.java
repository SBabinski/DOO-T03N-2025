import java.util.Date;
import java.util.List;

public class ProcessaPedido {

    public Pedido processar(int id, Date criacao, Date vencimento, Cliente cliente, Vendedor vendedor, String loja, List<Item> itens) {
        Pedido pedido = new Pedido(id, criacao, vencimento, cliente, vendedor, loja, itens);
        if (confirmarPagamento(pedido)) {
            pedido.dataPagamento = new Date();
            System.out.println("Pagamento confirmado com sucesso.");
        } else {
            System.out.println("Erro: reserva vencida. Pagamento não confirmado.");
        }
        return pedido;
    }

    private boolean confirmarPagamento(Pedido pedido) {
        Date hoje = new Date();
        return hoje.compareTo(pedido.dataVencimentoReserva) <= 0;
    }
}
