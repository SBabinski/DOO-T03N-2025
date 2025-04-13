import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FloricAdd {
    private static List<String> flores = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public void menu() {
        int opcao = 0;
        
        while (opcao != 3) {
            System.out.println("\nConfigurações de Histórico:");
            System.out.println("[1] - Adicionar Flor");
            System.out.println("[2] - Listar Flores");
            System.out.println("[3] - Voltar ao Menu Principal");

            opcao = scanner.nextInt();
            scanner.nextLine(); 

            switch (opcao) {
                case 1:
                    System.out.println("Digite o nome da flor: ");
                    String flor = scanner.nextLine();
                    flores.add(flor);
                    System.out.println("Flor adicionada!");
                    break;

                case 2:
                    System.out.println("Flores Registradas:");
                    if (flores.isEmpty()) {
                        System.out.println("Nenhuma flor registrada.");
                    } else {
                        for (String f : flores) {
                            System.out.println(f);
                        }
                    }
                    break;

                case 3:
                    System.out.println("Voltando ao menu principal...");
                    break;

                default:
                    System.out.println("*Opção inválida*");
                    break;
            }
        }
    }
}
