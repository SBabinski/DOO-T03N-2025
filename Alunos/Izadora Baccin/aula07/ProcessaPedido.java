package fag;
import java.time.LocalDate;
import java.util.Scanner;

public class ProcessaPedido {

    public void processar() {
        Scanner scan = new Scanner(System.in);

        System.out.print("Digite o nome do cliente: ");
        String cliente = scan.nextLine();

        System.out.print("Digite o nome do produto: ");
        String produto = scan.nextLine();

        System.out.print("Digite a data de vencimento da reserva (formato: yyyy-mm-dd): ");
        String dataVencimentoStr = scan.nextLine();
        LocalDate dataVencimentoReserva = LocalDate.parse(dataVencimentoStr);

        if (confirmarPagamento(dataVencimentoReserva)) {
            System.out.println("Pedido de " + cliente + " para o produto " + produto + " processado com sucesso!");
        } else {
            System.out.println("Pagamento não confirmado. A reserva está vencida.");
        }
    }

    private boolean confirmarPagamento(LocalDate dataVencimentoReserva) {
        LocalDate dataAtual = LocalDate.now();
        return !dataAtual.isAfter(dataVencimentoReserva); 
    }
}
