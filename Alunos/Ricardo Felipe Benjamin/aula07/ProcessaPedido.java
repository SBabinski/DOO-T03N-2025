import java.util.List;
import java.util.Date;

public class ProcessaPedido {

     public Pedido processar(int id, Pessoa cliente, Pessoa vendedor, String loja, List<Item> itens,
                            Date dataCriacao, Date dataPagamento, Date dataVencimentoReserva) {
        if (!confirmarPagamento(dataVencimentoReserva)) {
            System.out.println("Pagamento recusado: reserva vencida.");
            return null;
        }

        Pedido pedido = new Pedido(id, dataCriacao, dataPagamento, dataVencimentoReserva, cliente, vendedor, loja, itens);
        System.out.println("Pedido processado com sucesso!");
        pedido.gerarDescricaoVenda();
        return pedido;
    }

    private boolean confirmarPagamento(Date dataVencimentoReserva) {
        Date agora = new Date();
        return agora.before(dataVencimentoReserva);
    }
    
}
