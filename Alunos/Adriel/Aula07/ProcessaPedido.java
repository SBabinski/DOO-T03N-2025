import java.util.Date;
import java.util.List;

public class ProcessaPedido {
    public Pedido processar(int id, Cliente cliente, Vendedor vendedor, Loja loja, List<Item> itens) {
        Date dataCriacao = new Date();
        Pedido pedido = new Pedido(id, dataCriacao, cliente, vendedor, loja, itens);
        
        // Simula pagamento após 1 dia
        Date dataPagamento = new Date(dataCriacao.getTime() + (24L * 60 * 60 * 1000));
        pedido.setDataPagamento(dataPagamento);
        
        if (confirmarPagamento(pedido)) {
            System.out.println("Pagamento confirmado para o pedido " + id);
        } else {
            System.out.println("Pagamento não confirmado - reserva vencida para o pedido " + id);
        }
        
        return pedido;
    }

    private boolean confirmarPagamento(Pedido pedido) {
        Date agora = new Date();
        return !agora.after(pedido.getDataVencimentoReserva());
    }
}