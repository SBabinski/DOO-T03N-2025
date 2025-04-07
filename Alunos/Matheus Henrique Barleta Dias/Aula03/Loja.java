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
            System.out.println("[3] Sair");
            int opcao = sc.nextInt();

            BigDecimal quant;
            BigDecimal val;
            BigDecimal receb;
            BigDecimal troco = BigDecimal.ZERO;
            BigDecimal desc = BigDecimal.ZERO;

            switch (opcao) {
                case 1:
                    System.out.println("Quantas plantas serão vendidas?");
                    quant = sc.nextBigDecimal();
                    System.out.println("Qual é o valor de cada planta?");
                    val = sc.nextBigDecimal();
                    total = quant.multiply(val);

                    if (quant.compareTo(new BigDecimal("10")) > 0) {
                        desc = total.multiply(new BigDecimal("0.05"));
                        total = total.subtract(desc);
                    }

                    System.out.println("Total a pagar: R$"+ total);

                    Vendas venda = new Vendas(quant, val, desc);
                    registroVendas.add(venda);
                    break;

                case 2:
                    if (total.compareTo(BigDecimal.ZERO) == 0) { 
                        System.out.println("Erro: Nenhuma venda foi registrada ainda.");
                    } else {
                        System.out.println("Qual foi o valor recebido?");
                        receb = sc.nextBigDecimal();
                        if (receb.compareTo(total) < 0) {
                            System.out.println("O valor recebido é menor do que o total do pedido!");
                        } else {
                            troco = receb.subtract(total);
                        }
                        System.out.println("O troco será de: R$" + troco);
                    }
                    break;

                case 3:
                    i = 3;
                    System.out.println("Você saiu.");
                    break;

                default:
                    System.out.println("O número não é válido!");
                    break;
            }
        } while (i != 3);

        sc.close();
    }
}

class Vendas {
    private BigDecimal quant;
    private BigDecimal val;
    private BigDecimal desc;

    public Vendas(BigDecimal quant, BigDecimal val, BigDecimal desc) {
        this.quant = quant;
        this.val = val;
        this.desc = desc;
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

    public String toString() {
        return "Venda: \nQuantidade = " + quant + "\nValor = R$" + val + "\nDesconto = R$" + desc;
    }
}
