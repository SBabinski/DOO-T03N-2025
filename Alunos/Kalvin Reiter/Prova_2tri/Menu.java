import java.util.*;

public class Menu {

    private Usuario usuario;
    private Map<String, Usuario> usuariosSalvos;

    public Menu(Usuario usuario, Map<String, Usuario> usuariosSalvos) {

        this.usuario = usuario;
        this.usuariosSalvos = usuariosSalvos;

    }

    public void exibir() {

        Scanner sc = new Scanner(System.in);

        while (true) {

            try {

                System.out.println("\n|---------------------------------------------------|");
                System.out.println("| Usuario: " + usuario.getNome() + " \n|");
                System.out.println("|-------------Escolha uma opcao abaixo--------------|");
                System.out.println("| 1 - Buscar series pelo nome                       |");
                System.out.println("| 2 - Adicionar/remover a lista de favoritos        |");
                System.out.println("| 3 - Adicionar/remover a lista 'ja assistidas'     |");
                System.out.println("| 4 - Adicionar/remover a lista 'desejo assistir'   |");
                System.out.println("| 5 - Exibir listas                                 |");
                System.out.println("| 6 - Sair                                          |");
                System.out.println("|                                                   |");

                String opcao;

                while (true) {

                    System.out.print("| Opcao: ");
                    opcao = sc.nextLine();

                    if (opcao.matches("[1-6]")) break;

                    System.out.println("| Opcao invalida! Por favor, tente novamente.");
                    System.out.println("|-                                                 -|");

                }

                switch (opcao) {

                    case "1":

                        buscarSeries(sc);
                        break;

                    case "2":

                        gerenciarListaPorNome(sc, "favoritos");
                        break;

                    case "3":

                        gerenciarListaPorNome(sc, "assistidas");
                        break;

                    case "4":

                        gerenciarListaPorNome(sc, "desejo");
                        break;

                    case "5":

                        exibirListas(sc);
                        break;

                    case "6":

                        salvarUsuarioAtual();
                        System.out.println("| Salvando dados e encerrando o sistema.");
                        System.out.println("|-                                                 -|");
                        System.out.println("|---------------------------------------------------|");
                        System.out.println("|---Aproveite as series que ainda nao cancelamos!---|");
                        System.out.println("|---------------------------------------------------|");
                        return;

                }

                salvarUsuarioAtual(); 

            } catch (Exception e) {

                System.out.println("Erro: " + e.getMessage());

            }

        }

    }

    private void salvarUsuarioAtual() {

        String chavePadrao = Armazenamento.padronizaChave(usuario.getNome());
        usuariosSalvos.put(chavePadrao, usuario);
        Armazenamento.salvar(usuariosSalvos);

    }

    private void buscarSeries(Scanner sc) {

        while (true) {

            System.out.print("| Nome da serie: ");
            String nome = sc.nextLine();
            List<Serie> series = TVMazeAPI.buscarSeries(nome);

            if (series.isEmpty()) {

                System.out.println("|-                                                 -|");
                System.out.println("| Nenhuma serie encontrada com esse nome! Por favor, tente novamente.");

                while (true) {

                    System.out.print("| Digite 's' para tentar novamente ou pressione 'Enter' para voltar: ");
                    String opcao = sc.nextLine().trim();

                    if (opcao.equalsIgnoreCase("s")) {

                        break;

                    } else if (opcao.isEmpty()) {

                        System.out.println("|---------------------------------------------------|\n");
                        return;

                    } else {

                        System.out.println("|-                                                 -|");
                        System.out.println("| Opcao invalida! Por favor, tente novamente.");

                    }

                }

                continue;

            }

            System.out.println("|-                                                 -|");
            System.out.println("|          --Selecione a serie desejada--           |");

            for (int i = 0; i < Math.min(10, series.size()); i++) {

                Serie s = series.get(i);
                System.out.println("| " + (i + 1) + " - " + s.getNome() + " (" + s.getIdioma() + ") - " + s.getEmissora());

            }

            String idx;

            while (true) {

                System.out.println("|-                                                 -|");
                System.out.print("| Digite o numero da serie para detalhes ou 'Enter' para voltar: ");
                idx = sc.nextLine();

                if (idx.isEmpty()) {

                    System.out.println("|---------------------------------------------------|\n");
                    return;

                }

                try {

                    int n = Integer.parseInt(idx);

                    if (n >= 1 && n <= Math.min(10, series.size())) {

                        System.out.println("|-                                                 -|");
                        System.out.println("| Resultado da busca:\n|");
                        System.out.println(series.get(n - 1));
                        System.out.println("|---------------------------------------------------|\n");
                        return;

                    } else {

                        System.out.println("| Numero fora da lista! Por favor, tente novamente.");

                    }

                } catch (NumberFormatException e) {

                    System.out.println("| Valor invalido! Por favor, tente novamente.");

                }

            }

        }

    }

    private void gerenciarListaPorNome(Scanner sc, String lista) {

        Map<Long, Serie> map = switch (lista) {

            case "favoritos" -> usuario.getFavoritos();
            case "assistidas" -> usuario.getAssistidas();
            case "desejo" -> usuario.getDesejo();
            default -> null;

        };

        String acao;

        while (true) {

            System.out.print("| Você deseja adicionar (a) ou remover (r) uma serie? [a/r]: ");
            acao = sc.nextLine().trim().toLowerCase();

            if (acao.equals("a") || acao.equals("r")) break;

            System.out.println("|-                                                 -|");
            System.out.println("| Opcao invalida! Digite 'a' para adicionar ou 'r' para remover.");

        }

        if (acao.equals("r")) {

            List<Serie> listaSeries = usuario.getLista(lista);

            if (listaSeries.isEmpty()) {

                System.out.println("|-                                                 -|");
                System.out.println("| Sua lista esta vazia. Nao ha nada para remover.");
                System.out.println("|---------------------------------------------------|\n");
                return;

            }

            while (true) {

                System.out.println("|-                                                 -|");
                System.out.println("|        --Selecione a serie para remover--         |");

                for (int i = 0; i < listaSeries.size(); i++) {

                    Serie s = listaSeries.get(i);
                    System.out.println("| " + (i + 1) + " - " + s.getNome() + " (" + s.getIdioma() + ") - " + s.getEmissora());

                }

                System.out.println("|-                                                 -|");
                System.out.print("| Digite o numero da serie para remover ou 'Enter' para cancelar: ");
                String idx = sc.nextLine();

                if (idx.isEmpty()) {

                    System.out.println("| Operacao cancelada.");
                    System.out.println("|---------------------------------------------------|\n");
                    return;

                }

                try {

                    int n = Integer.parseInt(idx);

                    if (n >= 1 && n <= listaSeries.size()) {

                        Serie escolhida = listaSeries.get(n - 1);
                        System.out.println("|-                                                 -|");
                        System.out.println(escolhida);
                        System.out.println("|-                                                 -|");

                        while (true) {

                            System.out.print("| Deseja realmente remover esta serie? (s/n): ");
                            String resposta = sc.nextLine().trim().toLowerCase();
                            
                            if (resposta.equals("s") || resposta.equals("sim")) {

                                usuario.removerSerie(lista, escolhida.getId());
                                System.out.println("| SSerie removida da lista.");
                                System.out.println("|---------------------------------------------------|\n");
                                return;
                                
                            } else if (resposta.equals("n") || resposta.equals("nao") || resposta.equals("não")) {
                               
                                System.out.println("| Remocao cancelada.");
                                System.out.println("|---------------------------------------------------|\n");
                                return;

                            } else {

                                System.out.println("| Resposta invalida! Por favor, tente novamente.");
                                System.out.println("|-                                                 -|");

                            }

                        }

                    } else {

                        System.out.println("| Numero fora da lista! Por favor, tente novamente.");

                    }

                } catch (NumberFormatException e) {

                    System.out.println("| Valor invalido! Por favor, tente novamente.");

                }

            }

        } else {

            while (true) {

                System.out.print("| Digite o nome da serie: ");
                String nomeBusca = sc.nextLine();

                if (nomeBusca.trim().isEmpty()) {

                    System.out.println("| Operacao cancelada.");
                    System.out.println("|---------------------------------------------------|\n");
                    return;

                }

                List<Serie> series = TVMazeAPI.buscarSeries(nomeBusca);

                if (series.isEmpty()) {

                    System.out.println("|-                                                 -|");
                    System.out.println("| Nenhuma serie encontrada com esse nome. Tente novamente ou pressione 'Enter' para cancelar.");
                    continue;

                }

                System.out.println("|-                                                 -|");
                System.out.println("|          --Selecione a serie desejada--           |");

                for (int i = 0; i < Math.min(10, series.size()); i++) {

                    Serie s = series.get(i);
                    System.out.println("| " + (i + 1) + " - " + s.getNome() + " (" + s.getIdioma() + ") - " + s.getEmissora());

                }

                String idx;
                Serie escolhida = null;

                while (true) {

                    System.out.print("|\n| Digite o numero da serie desejada ou 'Enter' para cancelar: ");
                    idx = sc.nextLine();

                    if (idx.isEmpty()) {

                        System.out.println("| Operacao cancelada.");
                        System.out.println("|---------------------------------------------------|\n");
                        return;

                    }

                    try {
                        
                        int n = Integer.parseInt(idx);

                        if (n >= 1 && n <= Math.min(10, series.size())) {

                            escolhida = series.get(n - 1);
                            break;

                        } else {

                            System.out.println("| Numero fora da lista! Por favor, tente novamente.");
                            System.out.println("|-                                                 -|");

                        }

                    } catch (NumberFormatException e) {

                        System.out.println("| Valor invalido! Por favor, tente novamente.");

                    }

                }

                if (escolhida != null) {

                    if (map != null && map.containsKey(escolhida.getId())) {

                        System.out.println("| A serie ja esta na lista!");

                    } else {

                        usuario.adicionarSerie(lista, escolhida);
                        System.out.println("|-                                                 -|");
                        System.out.println("| Serie adicionada a lista: \n|");
                        System.out.println(escolhida);

                    }

                    System.out.println("|---------------------------------------------------|\n");
                    return;

                }

            } 

        }

    }

    private void exibirListas(Scanner sc) {

        String lista = null;

        while (true) {

            System.out.println("|           --Qual lista deseja exibir?--           |");
            System.out.println("| 1 - Lista de favoritos                            |");
            System.out.println("| 2 - Lista de 'já assistidas'                      |");
            System.out.println("| 3 - Lista de 'desejo assistir'                    |");
            System.out.print("|\n| Escolha: ");
            String op = sc.nextLine();

            lista = switch(op) {

                case "1" -> "favoritos";
                case "2" -> "assistidas";
                case "3" -> "desejo";
                default -> null;

            };

            if (lista == null) {

                System.out.println("| Opcao invalida! Por favor, tente novamente.");
                System.out.println("|-                                                 -|");

            } else {

                break;

            }
        }

        List<Serie> listaSeries = usuario.getLista(lista);

        if (listaSeries.isEmpty()) {

            System.out.println("| A lista selecionada esta vazia.");
            System.out.println("|---------------------------------------------------|\n");
            return;

        }

        Comparator<Serie> comp = null;

        while (true) {

            System.out.println("|             --Como deseja ordenar?--              |");
            System.out.println("| 1 - Ordem alfabetica                              |");
            System.out.println("| 2 - Nota geral                                    |");
            System.out.println("| 3 - Estado da serie                               |");
            System.out.println("| 4 - Data de estreia                               |");
            System.out.print("|\n| Escolha: ");
            String ord = sc.nextLine();

            comp = switch(ord) {

                case "1" -> {

                    System.out.println("| Ordenando por ordem alfabética:");
                    yield Comparator.comparing(Serie::getNome, Comparator.nullsLast(String::compareToIgnoreCase));

                }

                case "2" -> {

                    System.out.println("| Ordenando por nota geral (maior para menor):");
                    yield Comparator.comparing(Serie::getNota, Comparator.nullsLast(Comparator.reverseOrder()));

                }

                case "3" -> {

                    System.out.println("| Ordenando por estado da serie:");
                    yield Comparator.comparing(Serie::getEstado, Comparator.nullsLast(String::compareToIgnoreCase));
                    
                }

                case "4" -> {

                    System.out.println("| Ordenando por data de estreia:");
                    yield Comparator.comparing(Serie::getEstreia, Comparator.nullsLast(String::compareTo));

                }

                default -> null;

            };

            if (comp != null) {

                break;

            } else {
                
                System.out.println("| Opçao de ordenacao invalida! Por favor, tente novamente.");
                System.out.println("|-                                                 -|");

            }

        }

        listaSeries.sort(comp);

        for (Serie s : listaSeries) {

            System.out.println("|-                                                 -|");
            System.out.println(s);

        }

        System.out.println("|---------------------------------------------------|\n");

    }
}