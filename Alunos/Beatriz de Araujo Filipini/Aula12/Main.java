import java.io.IOException;
import java.util.Scanner;


public class Main {

    private static VisualCrossingApiService apiService = new VisualCrossingApiService();
    private static ClimaDataManager dataManager = new ClimaDataManager();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Bem-vindo(a) ao Aplicativo de Clima!");
        menuPrincipal();
        System.out.println("Obrigado(a). Até mais!");
        scanner.close();
    }

    private static void menuPrincipal() {
        int opcao;
        do {
            System.out.println("\n--- Menu Principal ---");
            System.out.println("1. Buscar Clima por Cidade");
            System.out.println("2. Ver Última Consulta Salva");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = lerInteiro();

            switch (opcao) {
                case 1:
                    buscarClima();
                    break;
                case 2:
                    exibirUltimaConsulta();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 0);
    }

    private static void buscarClima() {
        System.out.print("Digite o nome da cidade (ex: São Paulo, Londrina): ");
        String nomeCidade = scanner.nextLine();

        try {
        
            Clima clima = apiService.buscarClimaPorCidade(nomeCidade);

            if (clima != null) {
                exibirDetalhesClima(clima);
            
                dataManager.salvarUltimaConsulta(clima);
            } else {
                System.out.println("Não foi possível obter os dados do clima para " + nomeCidade + ". Tente outro nome ou verifique sua API Key.");
            }
        } catch (IOException | InterruptedException e) {
            System.err.println("Erro ao buscar o clima: " + e.getMessage());
            System.err.println("Verifique sua conexão com a internet ou tente novamente mais tarde.");
        }
    }

    private static void exibirDetalhesClima(Clima clima) {
        System.out.println("\n--- Clima em " + clima.getAddress() + " ---");
        System.out.println("Temperatura Atual: " + String.format("%.1f", clima.getCurrentTemp()) + "°C");
        System.out.println("Temperatura Máxima (Hoje): " + String.format("%.1f", clima.getMaxTemp()) + "°C");
        System.out.println("Temperatura Mínima (Hoje): " + String.format("%.1f", clima.getMinTemp()) + "°C");
        System.out.println("Umidade do Ar: " + String.format("%.1f", clima.getHumidity()) + "%");
        System.out.println("Condição do Tempo: " + clima.getConditions());
        System.out.println("Precipitação (Hoje): " + String.format("%.1f", clima.getPrecipitation()) + " mm");
        System.out.println("Velocidade do Vento: " + String.format("%.1f", clima.getWindSpeed()) + " km/h");
        System.out.println("Direção do Vento: " + clima.getWindDirectionText() + " (" + String.format("%.0f", clima.getWindDir()) + "°)");
    }

    private static void exibirUltimaConsulta() {
        try {
            Clima ultimaConsulta = dataManager.carregarUltimaConsulta();
            if (ultimaConsulta != null) {
                System.out.println("\n--- Última Consulta Salva ---");
                exibirDetalhesClima(ultimaConsulta);
            } else {
                System.out.println("Nenhuma consulta anterior encontrada. Realize uma busca primeiro.");
            }
        } catch (IOException e) {
            System.err.println("Erro ao carregar a última consulta: " + e.getMessage());
        }
    }

    private static int lerInteiro() {
        while (!scanner.hasNextInt()) {
            System.out.println("Entrada inválida. Por favor, digite um número.");
            scanner.next(); 
            System.out.print("Escolha uma opção: ");
        }
        int valor = scanner.nextInt();
        scanner.nextLine();
        return valor;
    }
}