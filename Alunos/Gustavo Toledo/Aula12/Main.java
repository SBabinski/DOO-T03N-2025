import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        boolean continuar = true;

        while (continuar) {
            Clima clima = null;

            while (clima == null) {
                System.out.print("Digite o nome da cidade: ");
                String cidade = sc.nextLine();

                clima = ServicoAPI.buscarClima(cidade);

                if (clima != null) {
                    clima.exibir();
                } else {
                    System.out.println("Não foi possível obter os dados dessa cidade. Tente novamente.");
                }
            }

            System.out.println("\nDeseja consultar outra cidade? (S/N)");
            String resposta = sc.nextLine();

            if (!resposta.equalsIgnoreCase("S")) {
                continuar = false;
                System.out.println("Encerrando o aplicativo...");
            }
        }

        sc.close();
    }
}
