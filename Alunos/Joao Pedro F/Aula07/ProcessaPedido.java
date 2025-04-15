import java.util.Date;
import java.util.List;

public class ProcessaPedido {

    
    public Pedido processar(int id, Date dataCriacao, Date dataVencimentoReserva,
                            Pessoa cliente, Pessoa vendedor, String loja, List<Item> itens) {
        
        Pedido pedido = new Pedido(id, dataCriacao, dataVencimentoReserva, cliente, vendedor, loja, itens);

        
        if (confirmarPagamento(pedido)) {

            pedido.setDataPagamento(new Date()); // Data de pagamento será a data atual
            System.out.println("Pagamento confirmado.");

        } else {
            System.out.println("Reserva vencida. Pagamento não processado.");
        }

        return pedido;
    }

    // Método privado que confirma se o pagamento pode ser realizado
    private boolean confirmarPagamento(Pedido pedido) {
        Date hoje = new Date(); // Data atual
        return hoje.before(pedido.getDataVencimentoReserva()); // Verifica se a data atual é anterior à data de vencimento
    }
}
