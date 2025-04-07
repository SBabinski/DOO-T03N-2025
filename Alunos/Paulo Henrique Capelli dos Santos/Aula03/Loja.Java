import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Loja {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BigDecimal total = BigDecimal.ZERO;
        int i = 0;
        List<Vendas> registroVendas = new ArrayList<>();

        do {
            System.out.println("\n[1] Calcular Preço Total");
            System.out.println("[2] Calcular Troco");
            System.out.println("[3] Histórico de Pedidos");
            System.out.println("[4] Sair");
            int opcao = sc.nextInt();

            BigDecimal quant;
            BigDecimal val;
            BigDecimal receb;
            BigDecimal troco;
            BigDecimal stotal;
            BigDecimal desc = BigDecimal.ZERO;

            switch (opcao) {
                case 1:
                    System.out.println("Quantas plantas serão vendidas?");
                    quant = sc.nextBigDecimal();
                    System.out.println("Qual é o valor de cada planta?");
                    val = sc.nextBigDecimal();
                    total = quant.multiply(val);
                    stotal = total;

                    if (quant.compareTo(new BigDecimal("10")) > 0) {
                        desc = total.multiply(new BigDecimal("0.05"));
                        total = total.subtract(desc);
                    }

                    System.out.println("Total a pagar: R$"+ total);

                    Vendas venda = new Vendas(quant, val, desc, stotal, total);
                    registroVendas.add(venda);
                    break;

                case 2:
                    if (total.compareTo(BigDecimal.ZERO) == 0) { 
                        System.out.println("Erro: Nenhuma venda foi registrada ainda.");
                    } else {
                        System.out.println("Qual foi o valor recebido?");
                        receb = sc.nextBigDecimal();
                        troco = receb.subtract(total);
                        System.out.println("O troco será de: R$" + troco);
                    }
                    break;

                case 3:
                    i = 3;
                    int y = 1;
                    for(Vendas umaVenda : registroVendas){
                        System.out.println(y+"°"+umaVenda);
                        y++;
                    }
                    break;
                case 4:
                    i = 4;
                    System.out.println("Você saiu.");
                    break;
                default:
                    System.out.println("O número não é válido!");
                    break;
            }
        } while (i != 4);

        sc.close();
    }
}

class Vendas {
    private BigDecimal quant;
    private BigDecimal val;
    private BigDecimal desc;
    private BigDecimal stotal;
    private BigDecimal total;

    public Vendas(BigDecimal quant, BigDecimal val, BigDecimal desc, BigDecimal stotal, BigDecimal total) {
        this.quant = quant;
        this.val = val;
        this.desc = desc;
        this.stotal = stotal;
        this.total = total;
    }

    public BigDecimal getQuant() {
        return quant;
    }

    public void setQuant(BigDecimal quant) {
        this.quant = quant;
    }

    public BigDecimal getVal() {
        return val;
    }

    public void setVal(BigDecimal val) {
        this.val = val;
    }

    public BigDecimal getDesc() {
        return desc;
    }

    public void setDesc(BigDecimal desc) {
        this.desc = desc;
    }

    public BigDecimal getStotal() {
        return stotal;
    }

    public void setStotal(BigDecimal stotal) {
        this.stotal = stotal;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public String toString() {
        return "\nVenda: \nQuantidade = " + quant + "\nValor = R$" + val + "\nDesconto = R$" + desc + "\nSubtotal = R$" + stotal + "\nTotal = R$" + total;
    }
}