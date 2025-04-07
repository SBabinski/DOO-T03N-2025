import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class Main {

    private static final BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    private static final DecimalFormat brl = UtilCurrency.getBrlFormat();

    private static final List<Venda> vendasHistory = new ArrayList<>();

    public static void main(String[] args) {
        menu();
    }

    private static void menu(){
        while (true) {
            try {
                printMenu(); //Função para evitar bloco de codigo enorme do println
                int userChoice = readInt();

                switch (userChoice) {
                    case 1:
                        calcPrecoTotal();
                        break;
                    case 2:
                        calcTroco();
                        break;
                    case 3:
                        displayVendas();
                        break;
                    case 4:
                        return;
                    default:
                        System.out.println("Não é uma opção válida!");
                        break;
                }
            } catch (Exception ignored) {}
        }
    }


    private static void calcPrecoTotal() {
        while (true) {
            try {
                BigDecimal precoProduto;
                BigDecimal valorTotal;
                boolean desconto = false;
                long quantidade;

                System.out.println("Coloque quantidade de um(a) planta/produto vendido(a): ");
                quantidade = Long.parseLong(input.readLine());

                System.out.println("Coloque o preço unitário: ");
                precoProduto = readBig();

                valorTotal = precoProduto.multiply(new BigDecimal(quantidade));

                if (quantidade > 10) {
                    desconto = true;
                    valorTotal = valorTotal.multiply(new BigDecimal("0.95"));
                }

                vendasHistory.add(new Venda(quantidade, valorTotal, desconto));

                System.out.println("O preço total foi: " + brl.format(valorTotal) + (desconto ? " com desconto de 5% por mais de 10 quantidade" : ""));
                break;
            } catch (Exception e) {
                System.out.println("Valor incorreto!");
            }
        }
    }

    private static void calcTroco() {
        while (true) {
            try {
                BigDecimal valorRecebido;
                BigDecimal valorTotal;

                System.out.println("Coloque o valor recebido: ");
                valorRecebido = readBig();

                System.out.println("Coloque o valor total: ");
                valorTotal = readBig();

                System.out.println("O valor do troco foi: " + brl.format(valorRecebido.subtract(valorTotal)));
                break;

            } catch (Exception e) {
                System.out.println("Valor incorreto!");
            }
        }

    }

    private static void displayVendas() {
        if(vendasHistory.isEmpty()){
            System.out.println("Sem vendas ainda!");
            return;
        }

        int cont = 0;
        for(Venda v : vendasHistory) {
            cont++;
            System.out.println("[Venda " + cont + "] " +  v);
        }
    }

    //Função para evitar bloco de codigo enorme do println
    private static void printMenu() {
        System.out.println
                (
                        """
                        [1] - Calcular Preço Total
                        [2] - Calcular Troco
                        [3] - Ver Relatório de vendas
                        [4] - Sair\
                        """
                );
    }

    //Função para facilitar compreensão do código
    private static int readInt() throws IOException {
        return Integer.parseInt(input.readLine());
    }

    //Função para facilitar compreensão do código
    private static BigDecimal readBig() throws IOException {
        return new BigDecimal(input.readLine());
    }

}
