import java.math.BigDecimal;

public record Venda(long quantidade, BigDecimal precoTotal, boolean isDesconto) {



    @Override
    public String toString() {
        return quantidade + (quantidade == 1 ? " planta" : " plantas") +  " " + UtilCurrency.getBrlFormat().format(precoTotal) + " " + (isDesconto ? "(Desconto 5%)" : "");
    }
}
