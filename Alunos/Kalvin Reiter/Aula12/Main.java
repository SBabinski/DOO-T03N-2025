import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        ClimaService service = new ClimaService();

        System.out.println("Bem-vindo ao VisualCrossing, o sistema de consulta de clima!");

        while (true) {

            System.out.print("\nDigite a cidade desejada para buscar o clima (-1 para sair): ");
            String cidade = sc.nextLine();

            if (cidade.trim().equals("-1")) {

                System.out.println("\nSaindo do sistema");
                break;

            }

            try {

                ClimaInfo info = service.obterClima(cidade);
                System.out.println("\n----------Informacoes do clima:----------\n" + info + "\n-----------------------------------------");

            } catch (Exception e) {

                System.out.println("\nErro ao buscar informacoes do clima: " + e.getMessage());

            }

        }

        sc.close();

    }

}