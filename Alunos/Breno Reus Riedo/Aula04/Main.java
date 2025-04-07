import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    private static final BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    private static final DecimalFormat brl = UtilCurrency.getBrlFormat();
    private static final Map<LocalDateTime, Venda> vendasHistory = new HashMap<>();

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

                System.out.println("Coloque o mês da realização da venda: ");
                int mes = readInt();

                System.out.println("Coloque o dia do mês da realização da venda: ");
                int dia = readInt();

                LocalDateTime date = getDateTimeMonthAndDay(mes, dia);

                vendasHistory.put(date, new Venda(quantidade, valorTotal, desconto));

                System.out.println("O preço total foi: " + brl.format(valorTotal) + (desconto ? " com desconto de 5% por mais de 10 quantidade" : ""));
                break;
            } catch (Exception e) {
                System.out.println("Valor ou data incorreta!");
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

        while (true) {
            try {
                Map<Integer, List<Venda>> vendasPorMes = vendasHistory.entrySet()
                        .stream()
                        .collect(Collectors.groupingBy(
                                entry -> entry.getKey().getMonthValue(),
                                TreeMap::new,
                                Collectors.mapping(Map.Entry::getValue, Collectors.toList())
                        ));

                for (Integer mesInt : vendasPorMes.keySet()) {
                    List<Venda> tempVenda = vendasPorMes.getOrDefault(mesInt, new ArrayList<>());
                    System.out.println("[Mês " + mesInt + "] " + tempVenda.size() + (tempVenda.size() == 1 ? " venda." : " vendas."));
                }

                System.out.println("Digite 0 para sair");
                System.out.println("Selecione um mês: ");
                int userMesChoice = readInt();

                if(userMesChoice == 0){break;}

                Map<Integer, List<Venda>> vendasPorMesDia = vendasHistory.entrySet().stream()
                        .filter(entry ->
                                entry.getKey().getMonthValue() == userMesChoice)
                        .collect(Collectors.groupingBy(
                                entry -> entry.getKey().getDayOfMonth(),
                                TreeMap::new,
                                Collectors.mapping(Map.Entry::getValue, Collectors.toList()))
                        );

                for (Integer diaInt : vendasPorMesDia.keySet()) {
                    List<Venda> tempVenda = vendasPorMesDia.getOrDefault(diaInt, new ArrayList<>());
                    System.out.println("[Dia " + diaInt + "] " + tempVenda.size() + (tempVenda.size() == 1 ? " venda." : " vendas."));
                }

                System.out.println("Selecione um dia: ");
                int userDiaChoice = readInt();

                for(Venda venda : vendasPorMesDia.get(userDiaChoice)){
                    System.out.println(venda.toString());
                }


                System.out.print("\n");


            }catch (Exception e) {
                System.out.println("Valor incorreto!");
            }
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

    //Função para retornar objeto LocalDateTime com os parâmetros especificados com campos de horas e anos ignorados setados default meia-noite,
    private static LocalDateTime getDateTimeMonthAndDay(int mes, int dia) throws ParseException {
        return LocalDateTime.now().withMonth(mes).withDayOfMonth(dia).withHour(0).withMinute(0).withSecond(0);
    }

}
