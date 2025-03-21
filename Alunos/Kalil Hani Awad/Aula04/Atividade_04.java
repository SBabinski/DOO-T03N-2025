import java.util.ArrayList;
import java.util.Scanner;

class Atividade_04 {
    public static Scanner scanner = new Scanner(System.in);    ;
    public static ArrayList<Resultado_> Total_compras = new ArrayList<>();
    public static float totalCompra;
    public static int[][] dias = new int[30][12];

    public static void main(String[] args) {
        menu();
    }

    public static void menu() {
        boolean turn = true;

        while(turn) {
            System.out.println("\nBem-vindo ao Programa de Conta");
            System.out.println("1 - Calcular");
            System.out.println("2 - Troco");
            System.out.println("3 - Sair");
            System.out.println("4 - Ver Registro");
            System.out.println("5 - Total e Dias");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            switch (opcao) {
                case 1:
                    calcular();
                    break;
                case 2:
                    troco();
                    break;
                case 3:
                    turn = false;
                    sair();
                    break;
                case 4:
                    registro();
                    break;
                case 5:
                    dias_totais_vendas();
                    break;
                default:
                    System.out.println("Opção Inválida. Tente novamente.");
            }
        }

    }

    public static void calcular() {
        int CompraTotal = 0;
        float Desconto = 0.0F;
        System.out.print("Digite o valor do item: ");
        float precoItem = scanner.nextFloat();
        System.out.print("Digite a quantidade de itens: ");
        int quantidade = scanner.nextInt();
        totalCompra = precoItem * (float)quantidade;
        if (quantidade > 10) {
            Desconto = totalCompra * 0.05F;
            totalCompra -= Desconto;
        }

        Resultado_ Venda = new Resultado_(totalCompra, quantidade, Desconto);
        ++CompraTotal;
        System.out.printf("O valor total da compra é: R$ %.2f\n", totalCompra);
        Total_compras.add(Venda);
        System.out.print("Digite o dia da venda (1 ate 30): ");
        int dia = scanner.nextInt();
        System.out.print("Digite o mes da venda (1 ate 12): ");
        int mes = scanner.nextInt();
        if (dia >= 1 && dia <= 30) {
            int[] var10000 = dias[dia - 1];
            var10000[mes - 1] += CompraTotal;
        } else {
            System.out.println("Dia inválido!");
        }
    }

    public static void troco() {
        if (totalCompra == 0.0F) {
            System.out.println("Nenhuma compra foi registrada.");
        } else {
            System.out.print("Digite o valor do pagamento: ");
            float pagamento = scanner.nextFloat();
            float troco = pagamento - totalCompra;
            if (troco > 0.0F) {
                System.out.printf("O troco é: R$ %.2f\n", troco);
            } else if (troco < 0.0F) {
                System.out.println("Falta dinheiro:" + -1.0F * troco);
            } else {
                System.out.println("Pagamento exato! Sem troco.");
            }

        }
    }

    public static void sair() {
        System.out.println("Fim do Programa. Obrigado!");
    }

    public static void registro() {
        for(Resultado_ venda : Total_compras) {
            System.out.println(venda);
        }

    }

    public static void dias_totais_vendas() {
        int day = 0;

        while(true) {
            System.out.println("Gostaria de ver o total de vendas dos dias (Yes) ou (No)?");
            String resposta = scanner.next();
            if (resposta.equalsIgnoreCase("Yes")) {
                System.out.print("Digite o dia (1 ate 30): ");
                day = scanner.nextInt();
                System.out.print("Digite o mês da venda (1 ate 12): ");
                int mes = scanner.nextInt();
                if (day >= 1 && day <= 30 && mes >= 1 && mes <= 12) {
                    System.out.println("Total de vendas no dia " + day + " do mês " + mes + " : " + dias[day - 1][mes - 1] + " Venda(s) ");
                } else {
                    System.out.println("Dia ou Mês invalido!");
                }
            } else {
                if (resposta.equalsIgnoreCase("No")) {
                    System.out.println("Saindo");
                    return;
                }

                System.out.println("Resposta inválida!");
            }
        }
    }


}
