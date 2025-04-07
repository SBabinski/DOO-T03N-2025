import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
@SuppressWarnings("unused")

class Vendas {
    private BigDecimal quantidade;
    private BigDecimal valorUnitario;
    private BigDecimal desconto;
    private BigDecimal subtotal;
    private BigDecimal total;
    private LocalDate data;
    private static final DateTimeFormatter FORMATADOR = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public Vendas(BigDecimal quantidade, BigDecimal valorUnitario, BigDecimal desconto, BigDecimal subtotal, BigDecimal total) {
        this.quantidade = quantidade;
        this.valorUnitario = valorUnitario;
        this.desconto = desconto;
        this.subtotal = subtotal;
        this.total = total;
        this.data = LocalDate.now();
    }

    public BigDecimal getQuantidade() {
        return quantidade;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public LocalDate getData() {
        return data;
    }

    @Override
    public String toString() {
        return "Data: " + data.format(FORMATADOR) + " | Quantidade: " + quantidade + " | Total: R$" + total;
    }
}
