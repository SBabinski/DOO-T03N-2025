package Aula07;

import java.util.Date;
import java.util.List;

public class ProcessaPedido {

    public Pedido processar(int id, Date dataCriacao, Date dataPagamento, Date dataVencimentoReserva, Cliente cliente,
                            Vendedor vendedor, Loja loja, List<Item> itens) {
        Pedido pedido = new Pedido(id, dataCriacao, dataPagamento, dataVencimentoReserva, cliente,
                vendedor, loja, itens);

        if (confirmarPagamento(pedido)) {
            System.out.println("Pagamento confirmado com sucesso.");
            System.out.println("Data de pagamento: " + dataPagamento);
        } else {
            System.out.println("Pagamento não confirmado: reserva vencida.");
        }

        return pedido;
    }

    private boolean confirmarPagamento(Pedido pedido) {
        if (pedido.getDataPagamento() == null || pedido.getDataPagamento().after(pedido.getDataVencimentoReserva())) {
            pedido.setDataPagamento(null);
            return false;
        }
        return true;
    }
}
