import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        
        for (int i = 0; i != 3; i++) {
            
        
            
        System.out.println("Escolha uma opção");
        System.out.println("1. conta");
        System.out.println("2. troco");
        System.out.println("3. SAIR");
        int opcao = scanner.nextInt();
        switch (opcao) {
            case 1:
                conta();
                break;
            case 2:
                troco();
                break;
            case 3:
                System.out.println("Saindo...");
                break;
            default:
                System.out.println("Opção inválida");
                break;
        }
    }
}

    public static void conta() {
        try {
            System.out.println("quantas plantas você pegou");
            int plantas = scanner.nextInt();
            System.out.println("qual o preço dela");
            float preco = scanner.nextFloat();
            float total = plantas * preco;
            System.out.println("o total é: " + total);
        } catch (InputMismatchException e) {
            System.out.println("Entrada inválida. Por favor, insira um número inteiro.");
            scanner.next(); 
        }
    }

    public static void troco(){
        try {
            System.out.println("qual o valor da planta");
            float valor = scanner.nextFloat();
            System.out.println("qual o valor que você deu");
            float pago = scanner.nextFloat();
            float troco = pago - valor;
            System.out.println("o troco é: " + troco);
        } catch (InputMismatchException e) {
            System.out.println("Entrada inválida. Por favor, insira um número inteiro.");
            scanner.next(); 
        }
    }
}