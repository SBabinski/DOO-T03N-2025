import java.util.Date;

public class ProcessaPedido {
    public Pedido processar(int id, Cliente cliente, Vendedor vendedor, 
                           Loja loja, List<Item> itens, int diasVencimento) {
        Pedido pedido = new Pedido(id, new Date(), cliente, vendedor, loja, itens, diasVencimento);
        confirmarPagamento(pedido);
        return pedido;
    }

    private boolean confirmarPagamento(Pedido pedido) {
        Date hoje = new Date();
        if (hoje.after(pedido.getDataVencimentoReserva())) {
            System.out.println("Pagamento não confirmado: reserva vencida");
            return false;
        }
        
        if (pedido.getDataPagamento() != null) {
            System.out.println("Pagamento confirmado");
            return true;
        }
        
        System.out.println("Aguardando pagamento");
        return false;
    }
}