package fag;

import fag.api.APIResponse;
import fag.model.Clima;
import fag.model.Dia;
import fag.model.Hora;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalTime;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Properties props = new Properties();

        try (FileInputStream input = new FileInputStream("src/main/resources/application.properties")) {
            props.load(input);
        } catch (IOException e) {
            System.err.println("Erro ao carregar o arquivo application.properties. Verifique se ele existe e tem a chave 'api.key'.");
            return;
        }

        boolean close = true;

        while (close) {
            System.out.println("\n--- Consulta de Clima ---");
            System.out.println("[1]- Ver Clima da minha cidade");
            System.out.println("[0]- Sair");
            System.out.print("Escolha uma opção: ");

            int option = -1;
            try {
                option = sc.nextInt();
            } catch (java.util.InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, digite um número.");
                sc.next();
                continue;
            }
            sc.nextLine();

            switch (option) {
                case 1:
                    System.out.print("Qual sua cidade? ");
                    String cidade = sc.nextLine();

                    if (!cidade.isEmpty()) {
                        String apiKey = props.getProperty("api.key");

                        try {
                            Clima clima = APIResponse.getWeatherData(cidade, apiKey);
                            Dia diaAtual = clima.days.get(0);
                            List<Hora> horasDoDia = diaAtual.hours;
                            int horaAtual = LocalTime.now().getHour();

                            if (horaAtual >= horasDoDia.size()) {
                                horaAtual = horasDoDia.size() - 1;
                            }
                            Hora infoHoraAtual = horasDoDia.get(horaAtual);

                            System.out.println("\n--- Clima em " + clima.resolvedAddress + " ---");
                            System.out.println("Descrição do dia: " + diaAtual.conditions);
                            System.out.println("Temperatura MÁXIMA para o dia: " + diaAtual.tempmax + "°C");
                            System.out.println("Temperatura MÍNIMA para o dia: " + diaAtual.tempmin + "°C");
                            System.out.println("\nDetalhes do Clima AGORA (" + infoHoraAtual.datetime.substring(0,5) + " hr):");
                            System.out.println("  Temperatura: " + infoHoraAtual.temp + "°C");
                            System.out.println("  Umidade: " + infoHoraAtual.humidity + "%");
                            System.out.println("  Probabilidade de Precipitação: " + infoHoraAtual.precipprob + "%");
                            System.out.println("  Quantidade de Precipitação (diária): " + diaAtual.precip + " mm");
                            System.out.println("  Velocidade do Vento: " + infoHoraAtual.windspeed + " km/h");
                            System.out.println("  Direção do Vento: " + infoHoraAtual.winddir + "°");
                            System.out.println("  Condição do Tempo: " + infoHoraAtual.conditions);

                        } catch (IOException | InterruptedException e) {
                            System.err.println("Ocorreu um erro ao buscar os dados do clima. Verifique a cidade e sua conexão.");
                        } catch (IndexOutOfBoundsException e) {
                            System.err.println("Não foi possível obter os dados do clima para a cidade informada ou dados incompletos. Tente novamente.");
                        }
                    } else {
                        System.out.println("Por favor, informe a cidade corretamente.");
                    }
                    break;

                case 0:
                    System.out.println("Obrigado por nos visitar. Tenha um ótimo dia!");
                    close = false;
                    break;
                default:
                    System.out.println("Opção inválida. Por favor, escolha 1 ou 0.");
            }
        }
        sc.close();
    }
}
