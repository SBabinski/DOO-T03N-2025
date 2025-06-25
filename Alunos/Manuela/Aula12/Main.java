package br.com.meuprojeto.tempo;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);

		System.out.println("--- Consulta de Clima e Tempo ---");

		System.out.print("Digite o nome da cidade (ex: Sao Paulo): ");

		String cidade = scanner.nextLine();

		if (cidade == null || cidade.trim().isEmpty()) {
			System.out.println("Nome da cidade inválido.");
			scanner.close();
			return;

		}

		ServicoClima weatherService = new ServicoClima();

		try {

			System.out.println("\nBuscando dados para '" + cidade + "'...");

			DadosClimaticos dadosDoTempo = ServicoClima.DadosClimaticos(cidade);

			// O método toString() formatado do nosso record será chamado aqui

			System.out.println(dadosDoTempo);

		} catch (Exception e) {

			System.err.println("\nOcorreu um erro ao buscar os dados do tempo: " + e.getMessage());

			// Para depuração, você pode descomentar a linha abaixo para ver o stack trace
			// completo

			// e.printStackTrace();

		} finally {

			scanner.close();

		}

	}

}
