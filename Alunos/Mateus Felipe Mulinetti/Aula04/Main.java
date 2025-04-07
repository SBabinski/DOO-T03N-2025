import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static final Map<Object, Integer> vendasDiarias = new HashMap<>();
    public static final Scanner scanner = new Scanner(System.in);
    public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static void main(String[] args) {
        int escolha = 0;
        while (escolha != 4) {
            System.out.println("[1] - Comprar/calcular preço \n" + "[2] - Calcular Troco \n" + "[3] - Vendas  \n" + "[4] - Sair");
            escolha = scanner.nextInt();
            switch (escolha) {
                case 1:
                    registrarVendas();
                    break;
                case 2:
                    troco();
                    break;
                case 3:
                    vendas();
                    break;
                case 4:
                    System.out.println("Ate mais!!");
                    break;
                default:
                    System.out.println("Opção inválida. Por favor, escolha uma opção válida.  \n ");
                    break;
            }
        }
    }

    private static void registrarVendas() {
        LocalDate dataVenda = null;
        while (dataVenda == null) {
            System.out.println("Digite a data da venda (dd/MM/yyyy): ");
            String dataStr = scanner.next();
            try {
                dataVenda = LocalDate.parse(dataStr, formatter);
            } catch (DateTimeParseException e) {
                System.out.println("Data inválida! Formato que devera ser feito!: dd/MM/yyyy. Tente novamente.");
            }
        }

        System.out.println("Digite a quantidade de vendas: ");
        float quantidade = scanner.nextFloat();

        System.out.println("Digite o valor unitario de cada planta:");
        float valor = scanner.nextFloat();


        float valorBruto = valor * quantidade;
        float valorTotal = valorBruto;

        if (quantidade > 10) {
            float temp = (valorBruto * 5) / 100;
            valorTotal = valorBruto - temp;
            System.out.println("Você comprou mais de 10 unidades então ganhara 5% de desconto o valor ficou R$: " +
                    String.format("%.2f", valorTotal));
        } else {
            System.out.println("O valor total da compra foi de R$: " + valorBruto);
        }


        vendasDiarias.put(dataVenda, (int) (vendasDiarias.getOrDefault(dataVenda, 0) + quantidade));
        System.out.println("Venda registrada com sucesso!");
    }

    private static void troco() {
        System.out.println("Calculadora de trocos \n " + "Digite o valor recebido do cliente:");
        float Dcliente = scanner.nextFloat();
        System.out.println("Digite o valor da compra do cliente:");
        float valorCompra = scanner.nextFloat();

        float troco = Dcliente - valorCompra;

        System.out.println("O troco serÁ de R$: " + troco);
    }

    private static void vendas() {
        LocalDate dataConsulta = null;
        while (dataConsulta == null) {
            System.out.println("Digite a data para consultar (dd/MM/yyyy): ");
            String dataStr = scanner.next();
            try {
                dataConsulta = LocalDate.parse(dataStr, formatter);
            } catch (DateTimeParseException e) {
                System.out.println("Data inválida! Formato que devera ser feito!: dd/MM/yyyy. Tente novamente.");
            }
        }

        int totalVendas = vendasDiarias.getOrDefault(dataConsulta, 0);
        System.out.println("Total de vendas no dia " + dataConsulta.format(formatter) + ": " + totalVendas + " plantas ");
    }

}
