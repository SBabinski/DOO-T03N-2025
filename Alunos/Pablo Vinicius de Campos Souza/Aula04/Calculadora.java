package Aula04;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Calculadora {
    private static final Scanner scanner = new Scanner(System.in);
    private static final RegistroDeVendas registroDeVendas = new RegistroDeVendas();
    private static final String MSG_ERRO_SEM_VENDAS = "Ainda não há vendas cadastradas. Adicione uma venda.\n";

    public static void main(String[] args) {
        while (true) {
            System.out.println("--------------------MENU--------------------");
            System.out.println("Selecione uma opção:");
            System.out.println("[1] - Adicionar venda");
            System.out.println("[2] - Exibir detalhes de uma venda");
            System.out.println("[3] - Exibir registro de todas as vendas");
            System.out.println("[4] - Exibir registro de vendas por data");
            System.out.println("[0] - Sair\n");

            int opcao = scanner.nextInt();
            switch (opcao) {
                case 0:
                    System.out.println("Encerrando calculadora...");
                    return;
                case 1:
                    menuAdicionarVenda();
                    break;
                case 2:
                    menuExibirVenda();
                    break;
                case 3:
                    menuExibirRegistroDeVendas();
                    break;
                case 4:
                    menuExibirRegistroDeVendasPorData();
                    break;
                default:
                    break;
            }
        }
    }

    private static void menuAdicionarVenda() {
        LocalDate dataVenda = obterDataConsole();
        if (dataVenda == null) return;

        System.out.println("Informe o nome da planta: ");
        String nome = scanner.next();

        System.out.println("Informe o preço da planta: ");
        double preco = scanner.nextDouble();

        Planta planta = new Planta(nome, preco);

        System.out.println("Informe a quantidade dessa planta: ");
        int quantidade = scanner.nextInt();

        Venda venda = new Venda(dataVenda, planta, quantidade);
        System.out.printf("O valor total da venda é: %.2f (R$ %.2f de desconto) %n", venda.getValorTotal(), venda.getValorDesconto());

        System.out.println("Informe o valor pago pelo cliente: ");
        double valorPago = scanner.nextDouble();
        venda.setValorPago(valorPago);

        double troco = venda.calcularTroco();
        if (troco > 0) {
            System.out.printf("Troco: R$ %.2f %n", troco);
        } else if (troco < 0) {
            System.out.printf("O cliente está devendo: R$ %.2f %n", troco);
        } else {
            System.out.println("O cliente pagou a quantia exata. Não é necessário troco.");
        }

        registroDeVendas.adicionarVenda(venda);
    }

    private static void menuExibirVenda() {
        if (registroDeVendas.getVendas().isEmpty()) {
            System.out.println(MSG_ERRO_SEM_VENDAS);
            return;
        }
        System.out.println("------------------VENDAS--------------------");
        for (Venda venda : registroDeVendas.getVendas()) {
            System.out.println(venda.identificarVenda());
        }

        while (true) {
            System.out.println("Digite o ID da venda a ser exibida (0 para voltar):");
            int input = scanner.nextInt();

            if (input == 0) {
                return;
            }

            Venda vendaSelecionada = registroDeVendas.pesquisarVendaPorId(input);
            if (vendaSelecionada == null) {
                System.out.printf("Não foi possível encontrar uma venda com o ID '%d'.%n", input);
            } else {
                System.out.println(vendaSelecionada.detalharVenda());
            }
        }
    }

    private static void menuExibirRegistroDeVendas() {
        if (registroDeVendas.getVendas().isEmpty()) {
            System.out.println(MSG_ERRO_SEM_VENDAS);
            return;
        }

        System.out.println(registroDeVendas.impressaoRegistroDeVendas(null));
    }

    private static void menuExibirRegistroDeVendasPorData() {
        if (registroDeVendas.getVendas().isEmpty()) {
            System.out.println(MSG_ERRO_SEM_VENDAS);
            return;
        }

        LocalDate dataVenda = obterDataConsole();
        if (dataVenda == null) return;
        System.out.println(registroDeVendas.impressaoRegistroDeVendas(dataVenda));
    }

    private static LocalDate obterDataConsole() {
        System.out.println("Informe a data (dd/MM/yyyy): ");
        String data = scanner.next();

        try {
            return LocalDateUtil.stringParaLocalDate(data);
        } catch (DateTimeParseException e) {
            System.out.println("Formato de data inválido, tente novamente.");
            return null;
        }
    }
}
