package Alunos.Gabriel.Aula07;


import java.time.LocalDate;

public class ProcessaPedido {
    public void processar(Pedido pedido){
        confimarPagamento(
        pedido.getDataPagamento(), 
        pedido.getDataVencimentoReserva());

    }


    private void confimarPagamento(
        LocalDate dataPagamento, 
        LocalDate dataVencimentoReserva){
        if(!dataPagamento.isAfter(dataVencimentoReserva)){
            System.out.println("Pagamento dentro do prazo.");
        }
        else{
            System.out.println("Pagamento atrasado!");
        }

    }

}

