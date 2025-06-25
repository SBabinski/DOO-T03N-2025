package org.example;

import org.example.models.Serie;
import org.example.models.Usuario;
import org.example.services.Persistencia;
import org.example.services.ServicoSeries;
import org.example.utils.OrdenacaoUtils;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        Usuario usuario = Persistencia.carregarDados();
        if (usuario == null) {
            System.out.print("Olá! Qual é seu nome ou apelido? ");
            String nome = scanner.nextLine();
            usuario = new Usuario(nome);
        }

        System.out.println("\nBem-vindo, " + usuario.getNome() + "!");

        int opcao = -1;

        while (opcao != 6) {
            System.out.println("\n===== MENU =====");
            System.out.println("[1] Buscar série por nome");
            System.out.println("[2] Ver séries favoritas");
            System.out.println("[3] Ver séries assistidas");
            System.out.println("[4] Ver séries para assistir");
            System.out.println("[5] Salvar dados");
            System.out.println("[6] Sair");
            System.out.print("Escolha: ");
            try {
                opcao = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Por favor, digite um número válido.");
                continue;
            }

            switch (opcao) {
                case 1 -> buscarSerie(scanner, usuario);
                case 2 -> mostrarLista("Favoritas", usuario.getFavoritas(), scanner, usuario::removerFavorita);
                case 3 -> mostrarLista("Assistidas", usuario.getAssistidas(), scanner, usuario::removerAssistida);
                case 4 -> mostrarLista("Deseja Assistir", usuario.getDesejoAssistir(), scanner, usuario::removerDesejoAssistir);
                case 5 -> {
                    Persistencia.salvarDados(usuario);
                    System.out.println("Dados salvos com sucesso!");
                }
                case 6 -> System.out.println("Até mais, " + usuario.getNome() + "!");
                default -> System.out.println("Opção inválida.");
            }
        }

        scanner.close();
    }

    private static void buscarSerie(Scanner scanner, Usuario usuario) {
        System.out.print("Digite o nome da série: ");
        String nomeBusca = scanner.nextLine();

        List<Serie> resultados = ServicoSeries.buscarSeries(nomeBusca);

        if (resultados == null || resultados.isEmpty()) {
            System.out.println("Nenhuma série encontrada.");
        } else {
            for (int i = 0; i < resultados.size(); i++) {
                System.out.println("[" + (i + 1) + "] " + resultados.get(i).getNome());
            }

            System.out.print("\nEscolha uma série pelo número (ou 0 para cancelar): ");
            int escolha;
            try {
                escolha = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Operação cancelada.");
                return;
            }

            if (escolha > 0 && escolha <= resultados.size()) {
                Serie selecionada = resultados.get(escolha - 1);
                System.out.println("\nDetalhes:\n" + selecionada);

                System.out.println("\nDeseja adicionar à:");
                System.out.println("[1] Favoritas");
                System.out.println("[2] Já assistidas");
                System.out.println("[3] Deseja assistir");
                System.out.println("[4] Cancelar");
                System.out.print("Escolha: ");

                int destino;
                try {
                    destino = Integer.parseInt(scanner.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println("Opção inválida.");
                    return;
                }

                switch (destino) {
                    case 1 -> usuario.adicionarFavorita(selecionada);
                    case 2 -> usuario.adicionarAssistida(selecionada);
                    case 3 -> usuario.adicionarDesejoAssistir(selecionada);
                    case 4 -> System.out.println("Operação cancelada.");
                    default -> System.out.println("Opção inválida.");
                }
            } else {
                System.out.println("Opção fora do intervalo. Operação cancelada.");
            }
        }
    }

    private static void mostrarLista(String titulo, List<Serie> lista, Scanner scanner, java.util.function.Consumer<Serie> removerCallback) {
        System.out.println("\n=== " + titulo + " ===");

        if (lista.isEmpty()) {
            System.out.println("Lista vazia.");
            return;
        }

        System.out.println("Deseja ordenar a lista?");
        System.out.println("[1] Nome");
        System.out.println("[2] Nota geral");
        System.out.println("[3] Estado (Exibição, Finalizada, Cancelada)");
        System.out.println("[4] Data de estreia");
        System.out.println("[0] Não ordenar");
        System.out.print("Escolha: ");

        int ordem;
        try {
            ordem = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida. A lista não será ordenada.");
            ordem = 0;
        }

        switch (ordem) {
            case 1 -> OrdenacaoUtils.ordenarPorNome(lista);
            case 2 -> OrdenacaoUtils.ordenarPorNota(lista);
            case 3 -> OrdenacaoUtils.ordenarPorEstado(lista);
            case 4 -> OrdenacaoUtils.ordenarPorDataEstreia(lista);
        }

        for (int i = 0; i < lista.size(); i++) {
            System.out.println("\n[" + (i + 1) + "]\n" + lista.get(i));
            System.out.println("----------------------------------");
        }

        System.out.print("Deseja remover alguma série? Digite o número (ou 0 para sair): ");
        int remover;
        try {
            remover = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida. Nenhuma série será removida.");
            return;
        }

        if (remover > 0 && remover <= lista.size()) {
            Serie serieRemovida = lista.get(remover - 1);
            removerCallback.accept(serieRemovida);
            System.out.println("Série removida da lista.");
        }
    }
}
