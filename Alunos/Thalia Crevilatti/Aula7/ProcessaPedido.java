import java.util.Date;

public class ProcessaPedido {
    
    public void processar(int idPedido, String cliente, String vendedor, String loja, Item[] itens) {
        Date dataCriacao = new Date(); 
        Date dataPagamento = new Date(); 
        Date dataVencimentoReserva = new Date(System.currentTimeMillis() + 86400000); // 1 dia após a criação (reserva de 1 dia)

        Pedido pedido = new Pedido(idPedido, dataCriacao, dataPagamento, dataVencimentoReserva, cliente, vendedor, loja, itens);
        pedido.gerarDescricaoVenda();
        confirmarPagamento(pedido);
    }

    private void confirmarPagamento(Pedido pedido) {
        Date hoje = new Date();
        if (hoje.before(pedido.dataVencimentoReserva)) {
            System.out.println("\nPagamento confirmado!");
        } else {
            System.out.println("\nPagamento não confirmado! A reserva venceu.");
        }
    }
}