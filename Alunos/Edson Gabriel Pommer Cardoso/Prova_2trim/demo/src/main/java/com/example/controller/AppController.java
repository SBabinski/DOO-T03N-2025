//Autenticação do usuário
//Exibe menu principal
//Chama serviços conforme opção escolhida

package com.example.controller;

import com.example.model.DadosUsuario;
import com.example.repository.UsuarioRepository;
import com.example.service.ListService;
import com.example.service.SeriesService;

import java.util.List;
import java.util.Scanner;

public class AppController {
    private static final String API_URL = "https://api.tvmaze.com";
    private final Scanner scanner = new Scanner(System.in);
    private final UsuarioRepository repo = new UsuarioRepository();
    private final SeriesService seriesService = new SeriesService(API_URL, scanner);
    private final ListService listService = new ListService(scanner);

    public void start() {
        DadosUsuario dados = authenticate();
        if (dados == null) return;

        int opt;
        do {
            opt = showMenu(dados.getUsuario());
            switch (opt) {
                case 1 -> seriesService.procurarSerie(dados);
                case 2 -> listService.exibir(dados);
                case 3 -> listService.remover(dados);
                case 4 -> listService.mover(dados);
                case 5 -> listService.ordenar(dados);
                case 10 -> {
                    repo.salvarDados(dados);
                    System.out.println("Saindo... Até logo, " + dados.getUsuario() + "!");
                }
                default -> System.out.println("Opção inválida.");
            }
        } while (opt != 10);
    }


    private DadosUsuario authenticate() {
        DadosUsuario dados = null;
        while (dados == null) {
            System.out.println("Deseja logar em um usuário já existente?");
            System.out.println("[1] - Sim");
            System.out.println("[2] - Não");
            System.out.println("[0] - Encerrar programa");
            String escolha = scanner.nextLine();

            if (escolha.equals("1")) {
                List<String> usuarios = repo.listarUsuarios();
                if (usuarios.isEmpty()) {
                    System.out.println("Nenhum usuário cadastrado. Crie um novo usuário.");
                    continue;
                }
                System.out.println("Usuários disponíveis:");
                for (int i = 0; i < usuarios.size(); i++) {
                    System.out.println("[" + (i + 1) + "] - " + usuarios.get(i));
                }
                System.out.println("[0] - Voltar");
                int idx;
                try {
                    idx = Integer.parseInt(scanner.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println("Digite um número válido.");
                    continue;
                }
                if (idx == 0)
                    continue;
                if (idx < 1 || idx > usuarios.size()) {
                    System.out.println("Opção inválida.");
                    continue;
                }
                String usuarioEscolhido = usuarios.get(idx - 1);
                dados = repo.carregarDadosUsuario(usuarioEscolhido);
                if (dados == null) {
                    System.out.println("Erro ao carregar usuário.");
                    continue;
                }
                System.out.println("Bem-vindo(a) de volta, " + dados.getUsuario() + "!");
            } else if (escolha.equals("2")) {
                System.out.print("Digite seu nome ou apelido para criar um novo usuário: ");
                String novoUsuario = scanner.nextLine();
                if (novoUsuario.trim().isEmpty()) {
                    System.out.println("Nome inválido.");
                    continue;
                }
                if (repo.usuarioExiste(novoUsuario)) {
                    System.out.println("Usuário já existe. Escolha outro nome ou faça login.");
                    continue;
                }
                dados = new DadosUsuario();
                dados.setUsuario(novoUsuario);
                repo.salvarDados(dados);
                System.out.println("Usuário criado e logado com sucesso! Bem-vindo(a), " + dados.getUsuario() + "!");
            } else if (escolha.equals("0")) {
                System.out.println("Encerrando o programa.");
                return null;
            } else {
                System.out.println("Opção inválida.");
            }
        }
        return dados;
    }

    private int showMenu(String usuario) {
        System.out.println("\n=====================");
        System.out.println("Bem-vindo(a) ao gerenciador de séries, " + usuario + "!");
        System.out.println("=====================");
        System.out.println("[1] - Procurar série pelo nome");
        System.out.println("[2] - Exibir listas");
        System.out.println("[3] - Remover série de alguma lista");
        System.out.println("[4] - Mover série entre listas");
        System.out.println("[5] - Ordenar listas");
        System.out.println("[10] - SAIR");
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}
