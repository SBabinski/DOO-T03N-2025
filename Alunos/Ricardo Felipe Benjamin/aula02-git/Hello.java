import java.util.Scanner;

public class Hello {


    public static void HelloWorld(){
        System.out.println("Hello World");
    }
    public static void main(String[] args) {

    Scanner sc =  new Scanner(System.in);
    int option;

        System.out.println("Escolha uma Opção: ");
        System.out.println("[1]Imprimir Hello World");
        option = sc.nextInt();

            if(option == 1){
                HelloWorld();
            }
        
    }

}