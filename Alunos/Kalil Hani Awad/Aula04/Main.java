import java.util.ArrayList;
import java.util.Scanner;

class Resultado {
    private float preco_total;
    private int quantidade;
    private float valor_menor;

    public Resultado(float preco_total, int quantidade,float valor_menor ){
        this.preco_total = preco_total;
        this.quantidade = quantidade;
        this.valor_menor = valor_menor;
    }

    public float getPreco_total() {
        return preco_total;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public float getValor_menor() {
        return valor_menor;
    }

    public void setPreco_total(float preco_total) {
        this.preco_total = preco_total;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public void setValor_menor(float valor_menor) {
        this.valor_menor = valor_menor;
    }

    @Override
    public String toString() {
        return "Resultado{" +
                "/Total: R$ " + preco_total+"/" +
                ",/Quantidade:" + quantidade+"/" +
                ",/Valor Descontado:" + valor_menor +"/"+
                '}';
    }

}

class Atividade_4 {
    public static Scanner scanner = new Scanner(System.in);
    public static ArrayList<Resultado> Total_compras = new ArrayList<>();
    public static float totalCompra = 0;
    public static int t = 0;
    public static int dias[][] = new int[12][30];

    public static void main(String[] args) {
        menu();
    }

    public static void menu() {
        boolean turn = true;
        while (turn) {
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
                    break;
            }
        }
    }

    public static void calcular() {
        int CompraTotal = 0;
        float Desconto = 0;
        System.out.print("Digite o valor do item: ");
        float precoItem = scanner.nextFloat();

        System.out.print("Digite a quantidade de itens: ");
        int quantidade = scanner.nextInt();
        totalCompra = precoItem * quantidade;
        if (quantidade > 10) {
            Desconto = totalCompra * 0.05f;
            totalCompra -= Desconto;

        }
        Resultado Venda = new Resultado(totalCompra, quantidade, Desconto);
        CompraTotal += 1;
        System.out.printf("O valor total da compra é: R$ %.2f\n", totalCompra);
        Total_compras.add(Venda);

        System.out.print("Digite o dia da venda (1 ate 30): ");
        int dia = scanner.nextInt();

        System.out.print("Digite o mes da venda (1 ate 12): ");
        int mes = scanner.nextInt();

        if (dia < 1 || dia > 30) {
            System.out.println("Dia inválido!");
            return;
        }

        dias[dia - 1][mes - 1] += CompraTotal;

    }

    public static void troco() {
        if (totalCompra == 0) {
            System.out.println("Nenhuma compra foi registrada.");
            return;
        }

        System.out.print("Digite o valor do pagamento: ");
        float pagamento = scanner.nextFloat();

        float troco = pagamento - totalCompra;

        if (troco > 0) {
            System.out.printf("O troco é: R$ %.2f\n", troco);

        } else if (troco < 0) {
            System.out.println("Falta dinheiro:" + (-1 * troco));

        } else {
            System.out.println("Pagamento exato! Sem troco.");

        }

    }

    public static void sair() {
        System.out.println("Fim do Programa. Obrigado!");
    }

    public static void registro() {
        for (Resultado venda : Total_compras) {
            System.out.println(venda);
        }
    }

    public static void dias_totais_vendas() {
       int day = 0;
       String resposta;
while (true) {
    System.out.println("Gostaria de ver o total de vendas dos dias (Yes) ou (No)?");
    resposta = scanner.next();
    if (resposta.equalsIgnoreCase("Yes")) {
        System.out.print("Digite o dia (1 ate 30): ");
        day = scanner.nextInt();

        System.out.print("Digite o mês da venda (1 ate 12): ");
        int mes = scanner.nextInt();

        if ((day >= 1 && day <= 30)&&(mes >= 1 && mes <= 12)) {
            System.out.println("Total de vendas no dia " + day + " do mês " + mes + " : " +dias[day - 1][mes - 1] +"Venda(s)");
        } else {
            System.out.println("Dia ou Mês invalido!");
        }
    } else if (resposta.equalsIgnoreCase("No")) {
        System.out.println("Saindo");
      break;
    } else {
        System.out.println("Resposta inválida!");
    }
}
    }
}

