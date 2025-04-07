import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

class RegistroVendas {
    private List<Vendas> vendasList = new ArrayList<>();
    private static final DateTimeFormatter FORMATADOR = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public void registrarVenda(Scanner sc) {
        System.out.println("Quantas plantas serão vendidas?");
        BigDecimal quantidade = sc.nextBigDecimal();
        System.out.println("Qual é o valor de cada planta?");
        BigDecimal valorUnitario = sc.nextBigDecimal();
        BigDecimal subtotal = quantidade.multiply(valorUnitario);
        BigDecimal desconto = BigDecimal.ZERO;

        if (quantidade.compareTo(new BigDecimal("10")) > 0) {
            desconto = subtotal.multiply(new BigDecimal("0.05"));
        }
        BigDecimal total = subtotal.subtract(desconto);

        System.out.println("Total a pagar: R$" + total);
        System.out.println("Qual foi o valor recebido?");
        BigDecimal recebido = sc.nextBigDecimal();
        BigDecimal troco = recebido.subtract(total);

        if (troco.compareTo(BigDecimal.ZERO) < 0) {
            System.out.println("Erro: Valor recebido insuficiente.");
            return;
        }
        System.out.println("O troco será de: R$" + troco);

        vendasList.add(new Vendas(quantidade, valorUnitario, desconto, subtotal, total));
    }

    public void exibirHistorico() {
        if (vendasList.isEmpty()) {
            System.out.println("Nenhuma venda registrada.");
        } else {
            vendasList.forEach(System.out::println);
        }
    }

    public void consultarVendasPorDia(Scanner sc) {
        System.out.println("Digite o dia (DD/MM/AAAA):");
        LocalDate data = LocalDate.parse(sc.next(), FORMATADOR);
        int pedidosDia = 0;
        BigDecimal totalDia = BigDecimal.ZERO;
        
        for (Vendas v : vendasList) {
            if (v.getData().equals(data)) {
                totalDia = totalDia.add(v.getTotal());
                pedidosDia++;
            }
        }
        System.out.println("Total de vendas no dia " + data.format(FORMATADOR) + ": R$" + totalDia + " | Pedidos realizados: " + pedidosDia);
    }

    public void consultarVendasPorMes(Scanner sc) {
        System.out.println("Digite o mês e ano (MM/AAAA):");
        String[] partes = sc.next().split("/");
        int mes = Integer.parseInt(partes[0]);
        int ano = Integer.parseInt(partes[1]);
        int pedidosMes = 0;
        BigDecimal totalMes = BigDecimal.ZERO;
        
        for (Vendas v : vendasList) {
            if (v.getData().getYear() == ano && v.getData().getMonthValue() == mes) {
                totalMes = totalMes.add(v.getTotal());
                pedidosMes++;
            }
        }
        System.out.println("Total de vendas no mês " + mes + "/" + ano + ": R$" + totalMes + " | Pedidos realizados: " + pedidosMes);
    }
}
