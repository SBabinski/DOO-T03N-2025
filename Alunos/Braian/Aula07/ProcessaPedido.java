package Alunos.Braian.Aula07;
import java.util.Date;

public class ProcessaPedido {
  public Pedido processar(int id, String cliente, String vendedor, String loja, Item[] itens, Date dataCriacao, Date dataPagamento, Date dataVencimentoReserva) {
    Pedido pedido = new Pedido(id, dataCriacao, dataPagamento, dataVencimentoReserva, cliente, vendedor, loja, itens);

    if (confirmarPagamento(pedido)) {
      System.out.println("Pagamento confirmado!");
    } else {
      System.out.println("Pagamento NÃO confirmado. Reserva vencida.");
    }
    return pedido;
  }

  private boolean confirmarPagamento(Pedido pedido) {
    Date hoje = new Date();
    return hoje.compareTo(pedido.getDataVencimentoReserva()) <= 0;
  }

  public static void testeConfirmacao() {
    Item[] itens = { new Item(1, "Orquidêa", "leve", 150.0) };
    Date hoje = new Date();
    Date vencimentoFuturo = new Date(hoje.getTime() + 86400000);
    ProcessaPedido processador = new ProcessaPedido();
    processador.processar(1, "Maria", "Carlos", "Loja A", itens, hoje, hoje, vencimentoFuturo);
  }

}
