package aula02;

import java.util.Scanner;

/**
 *
 * @author dougbruno
 */
public class Aula02 {

    /**
     * @param args the command line arguments
     */
    
    static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        menu();
    }
    
    public static void menu() {
        System.out.println("-- Menu --\n"
                + "1 - Calcular Preço Total\n"
                + "2 - Calcular Troco\n"
                + "3 - Sair\n");
        
        System.out.print("Escolha uma opção: ");
        int opcao = scanner.nextInt();
        
        switch (opcao) {
            case 1:
                calcularPreco();
                break;
            case 2:
                calcularTroco();
                break;
            case 3:
                System.exit(0);
            default:
                System.out.println("Selecione uma opção!");
                menu();
        }
        
        menu();
    }
    
    public static void calcularPreco() {
        System.out.print("Digite a quantidade da planta vendida: ");
        int quantidade = scanner.nextInt();
        System.out.print("Digite o valor de planta: ");
        float valor = scanner.nextFloat();
        System.out.println("O valor total será: R$" + quantidade * valor);
    }
    
    public static void calcularTroco() {
        System.out.print("Digite o valor recebido: ");
        float valorRecebido = scanner.nextFloat();
        System.out.print("Digite o valor total: ");
        float valorTotalCompra = scanner.nextFloat();
        System.out.println("O valor do troco será: R$" + (valorRecebido - valorTotalCompra));
    }
    
}
