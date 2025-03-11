import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("---SEJA BEM VINDO---\n");
        System.out.println("Selecione uma opção a baixo: \n");
        System.out.println("[1]- Calcular preço total \n[2]- Calcular troco \n[3]- Sair");
        int opcao = sc.nextInt();

        switch (opcao) {
            case 1: CalcularPreco();
                break;

            case 2: CalcularTroco();
                break;

            case 3:
                break;
        }
    }
    public static void CalcularPreco (){
        Scanner sc = new Scanner(System.in);
        System.out.println("Quantas plantas comprou?");
        int planta = sc.nextInt();

        System.out.println("Qual valor unitario dela:");
        double valorUnitario = sc.nextDouble();

        double valorTotal =+ planta * valorUnitario;
        System.out.println("Preço total da compra foi : "+valorTotal);
    }
    public static void CalcularTroco(){
        Scanner sc = new Scanner(System.in);

        System.out.println("Valor recebido pelo cliente;");
        double valorRecebido = sc.nextDouble();

        System.out.println("Valor total da compra:");
        double valorTotal = sc.nextDouble();

        double Troco = valorTotal - valorRecebido;

        if (valorRecebido < valorTotal) {
            System.out.println("Valor insuficiente");
        }
        else {
            System.out.println("Valor do troco: "+Troco);
        }
    }
}