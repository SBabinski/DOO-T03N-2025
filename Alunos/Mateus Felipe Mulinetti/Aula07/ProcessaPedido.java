package objetos;

import java.util.Date;

public class ProcessaPedido {
    public Pedido processar(int idPedido, Cliente cliente, Vendedor vendedor, Loja loja, Item[] itens,
                            Date dataCriacao, Date dataPagamento, Date dataVencimentoReserva,double[] valorTotal) {

        if (pagamentoConfirmado(dataPagamento, dataVencimentoReserva)) {
            return new Pedido(idPedido, dataCriacao, dataPagamento, dataVencimentoReserva, cliente, vendedor, loja, itens,valorTotal );
        } else {
            System.out.println("Erro! A o Pedido está vencido.");
            return null;
        }
    }

    private boolean pagamentoConfirmado(Date dataPagamento, Date dataVencimentoReserva) {
        return !dataPagamento.after(dataVencimentoReserva);
    }
}