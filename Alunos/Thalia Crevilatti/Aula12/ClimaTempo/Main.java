import java.util.Scanner;

 // Inicializa

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println(" Bem-vindo ao Consultor de Clima!");
        System.out.println(" Digite o nome da cidade que deseja consultar o clima: \n");
        String cidade = scanner.nextLine();

        try {
            ServicoClima servico = new ServicoClima();
            String json = servico.buscarClimaHoje(cidade);

            ClimaDia clima = ClimaParser.parse(json);
            System.out.println("\n Clima agora:\n");
            System.out.println(clima);

        } catch (Exception e) {
            System.err.println("Erro ao consultar clima: " + e.getMessage());
        } finally {
            System.out.println("\n Obrigado por usar o Consultor de Clima!");
        }

        scanner.close();
    }
}