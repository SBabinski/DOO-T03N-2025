import java.awt.CardLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import api.TVMazeAPI;
import model.Serie;
import model.TipoSerie;
import model.Usuario;


public class Main2 {
    public static void main(String[] args) {
        // Cria o frame principal da aplicação
        JFrame frame = new JFrame("App de séries");
        frame.setSize(800, 600); // Aumenta o tamanho do frame para melhor visualização
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Painel principal que usa CardLayout para alternar entre as telas
        JPanel cards = new JPanel(new CardLayout());

        // --- Tela de login ---
        String nomeUsuario = JOptionPane.showInputDialog(null, "Digite seu nome ou apelido:");
        if (nomeUsuario == null || nomeUsuario.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nome inválido. Encerrando.");
            System.exit(0);
        }
        // Carrega ou cria o usuário diretamente na classe Usuario
        Usuario usuario = Usuario.carregarOuCriarUsuario(nomeUsuario);
        JOptionPane.showMessageDialog(null, "Bem-vindo(a), " + usuario.getNome() + "!");

        // --- Tela principal ---
        JPanel telaPrincipal = new JPanel();
        telaPrincipal.setBackground(Color.WHITE);
        telaPrincipal.setLayout(new GridLayout(2, 2, 10, 10)); // Layout de grade para os botões
        JButton buscarSerie = new JButton("Procurar série por nome");
        JButton listaFavoritos = new JButton("Gerenciar lista de favoritos");
        JButton seriesAssistida = new JButton("Gerenciar séries já assistidas");
        JButton desejaAssistir = new JButton("Gerenciar séries que deseja assistir");
        telaPrincipal.add(buscarSerie);
        telaPrincipal.add(listaFavoritos);
        telaPrincipal.add(seriesAssistida);
        telaPrincipal.add(desejaAssistir);

        // --- Tela buscar série ---
        JPanel telaBuscarSerie = new JPanel();
        telaBuscarSerie.setLayout(new GridLayout(3, 1, 5, 5)); // Layout de grade
        telaBuscarSerie.setBackground(Color.LIGHT_GRAY);
        JTextField nomeSerie = new JTextField(20);
        JButton pesquisar = new JButton("Pesquisar");
        JButton voltarTelaBuscarSerie = new JButton("Voltar");
        telaBuscarSerie.add(nomeSerie);
        telaBuscarSerie.add(pesquisar);
        telaBuscarSerie.add(voltarTelaBuscarSerie);

        // --- Telas de gerenciamento de listas (Favoritas, Assistidas, Desejo Assistir) ---
        JPanel telaFavoritos = new JPanel();
        JPanel telaAssistidas = new JPanel();
        JPanel telaDesejaAssistir = new JPanel();

        // Adiciona todas as telas (cards) ao painel principal
        cards.add(telaPrincipal, "telaPrincipal");
        cards.add(telaBuscarSerie, "telaBuscarSerie");
        cards.add(telaFavoritos, "telaFavoritos");
        cards.add(telaAssistidas, "telaAssistidas");
        cards.add(telaDesejaAssistir, "telaDesejaAssistir");

        // Tela de resultados da pesquisa (será criada dinamicamente para cada pesquisa)
        JPanel telaResultados = new JPanel();
        cards.add(telaResultados, "telaResultados");

        frame.add(cards); // Adiciona o painel de cards ao frame
        frame.setVisible(true); // Torna o frame visível
        CardLayout cl = (CardLayout) cards.getLayout(); // Obtém o CardLayout para alternar telas

        // --- Ações dos botões da tela principal ---
        buscarSerie.addActionListener(e -> cl.show(cards, "telaBuscarSerie"));

        // Ação para o botão "Gerenciar lista de favoritos"
        listaFavoritos.addActionListener(e -> {
            atualizarTelaGerenciamento(telaFavoritos, usuario.getFavoritas(), usuario, TipoSerie.FAVORITA, cl, cards, "telaFavoritos");
        });

        // Ação para o botão "Gerenciar séries já assistidas"
        seriesAssistida.addActionListener(e -> {
            atualizarTelaGerenciamento(telaAssistidas, usuario.getAssistidas(), usuario, TipoSerie.ASSISTIDA, cl, cards, "telaAssistidas");
        });

        // Ação para o botão "Gerenciar séries que deseja assistir"
        desejaAssistir.addActionListener(e -> {
            atualizarTelaGerenciamento(telaDesejaAssistir, usuario.getDesejoAssistir(), usuario, TipoSerie.DESEJO_ASSISTIR, cl, cards, "telaDesejaAssistir");
        });

        // Ação para o botão "Voltar" na tela de busca
        voltarTelaBuscarSerie.addActionListener(e -> cl.show(cards, "telaPrincipal"));

        // --- Ação de pesquisa de série ---
        pesquisar.addActionListener(e -> {
            if (nomeSerie.getText().isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Digite o nome da série.");
                return;
            }

            List<Serie> resultados = TVMazeAPI.buscarSeries(nomeSerie.getText());

            if (resultados.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Nenhuma série encontrada.");
            } else {
                // Exibe os resultados da pesquisa
                exibirResultadosPesquisa(telaResultados, resultados, usuario, cl, cards);
            }
        });
    }

    /**
     * @param panel O JPanel da tela a ser atualizada.
     * @param series A lista de séries a ser exibida.
     * @param usuario O objeto Usuario atual.
     * @param tipoSerie O TipoSerie da lista (para remoção e salvamento).
     * @param cl O CardLayout para alternar entre as telas.
     * @param cards O JPanel pai que contém os cards.
     * @param cardName O nome do card para exibir.
     */

    private static void atualizarTelaGerenciamento(JPanel panel, List<Serie> series, Usuario usuario, TipoSerie tipoSerie, CardLayout cl, JPanel cards, String cardName) {
        panel.removeAll(); // Limpa o painel antes de redesenhar

        // Define o layout como GridLayout com 5 colunas para a seção de ordenação e 3 para os itens da lista
        panel.setLayout(new GridLayout(0, 5, 5, 5)); 

        // Botões de ordenação
        JButton ordAlfabetica = new JButton("Ordem Alfabética");
        JButton ordNota = new JButton("Nota Geral");
        JButton ordStatus = new JButton("Status");
        JButton ordDataEstreia = new JButton("Data de Estreia");

        // Adiciona os botões de ordenação ao topo da tela
        panel.add(new JLabel("Ordenar por:", JLabel.CENTER));
        panel.add(ordAlfabetica);
        panel.add(ordNota);
        panel.add(ordStatus);
        panel.add(ordDataEstreia);

        // ActionListeners para os botões de ordenação
        ordAlfabetica.addActionListener(a -> {
            ordenarEAtualizar(series, usuario, tipoSerie, panel, cl, cards, cardName, "alfabetica");
        });
        ordNota.addActionListener(a -> {
            ordenarEAtualizar(series, usuario, tipoSerie, panel, cl, cards, cardName, "nota");
        });
        ordStatus.addActionListener(a -> {
            ordenarEAtualizar(series, usuario, tipoSerie, panel, cl, cards, cardName, "status");
        });
        ordDataEstreia.addActionListener(a -> {
            ordenarEAtualizar(series, usuario, tipoSerie, panel, cl, cards, cardName, "dataEstreia");
        });

        // Adiciona uma linha de separação para melhor visualização (opcional)
        panel.add(new JLabel("--- Séries ---", JLabel.CENTER));
        panel.add(new JLabel("")); 
        panel.add(new JLabel("")); 
        panel.add(new JLabel("")); 
        panel.add(new JLabel("")); 

        // Exibe as séries da lista
        if (series.isEmpty()) {
            panel.add(new JLabel("Nenhuma série nesta lista.", JLabel.CENTER));
            panel.add(new JLabel("")); 
            panel.add(new JLabel(""));
            panel.add(new JLabel(""));
            panel.add(new JLabel(""));
        } else {
            for (Serie serie : series) {
                JLabel labelNomeSerie = new JLabel(serie.getNome(), JLabel.CENTER);
                JButton removerSerie = new JButton("Remover");
                JButton verDetalhes = new JButton("Detalhes"); // Botão para ver detalhes da série

                // Adiciona os componentes da série, ajustando para 5 colunas
                panel.add(labelNomeSerie);
                panel.add(removerSerie);
                panel.add(verDetalhes);
                panel.add(new JLabel("")); 
                panel.add(new JLabel(""));

                // Ação para remover série da lista
                removerSerie.addActionListener(a -> {
                    usuario.removerSerie(tipoSerie, serie);
                    JOptionPane.showMessageDialog(panel, serie.getNome() + " Removida da lista.");
                    
                    // Atualiza a tela após remover para refletir a mudança
                    atualizarTelaGerenciamento(panel, series, usuario, tipoSerie, cl, cards, cardName);
                });

                // Ação para ver detalhes da série
                verDetalhes.addActionListener(a -> {
                    JOptionPane.showMessageDialog(panel,
                            "Nome: " + serie.getNome() + "\n" +
                                    "Idioma: " + serie.getIdioma() + "\n" +
                                    "Gênero: " + serie.getGeneros() + "\n" +
                                    "Nota: " + serie.getNota() + "\n" +
                                    "Status: " + serie.getStatus() + "\n" +
                                    "Data de estreia: " + serie.getDataEstreia() + "\n" +
                                    "Data de conclusão: " + serie.getDataFim() + "\n" +
                                    "Emissora: " + serie.getEmissora());
                });
            }
        }

        JButton voltarTela = new JButton("Voltar");
        panel.add(voltarTela);
        panel.add(new JLabel("")); 
        panel.add(new JLabel("")); 
        panel.add(new JLabel("")); 
        panel.add(new JLabel("")); 


        voltarTela.addActionListener(b -> cl.show(cards, "telaPrincipal")); // Volta para a tela principal

        cl.show(cards, cardName); // Exibe o card da tela de gerenciamento

        panel.revalidate(); 

        panel.repaint();   
    }

    /**
     * Aplica a ordenação à lista de séries do usuário e atualiza a exibição da tela.
     * @param series A lista de séries a ser ordenada (referência).
     * @param usuario O objeto Usuario.
     * @param tipoSerie O TipoSerie da lista.
     * @param panel O JPanel da tela.
     * @param cl O CardLayout.
     * @param cards O JPanel pai de cards.
     * @param cardName O nome do card.
     * @param tipoOrdenacao O critério de ordenação.
     */
    private static void ordenarEAtualizar(List<Serie> series, Usuario usuario, TipoSerie tipoSerie, JPanel panel, CardLayout cl, JPanel cards, String cardName, String tipoOrdenacao) {
        
        // Chama o método de ordenação apropriado no objeto Usuario
        switch (tipoSerie) {
            case FAVORITA:
                usuario.ordenarFavoritas(tipoOrdenacao);
                break;
            case ASSISTIDA:
                usuario.ordenarAssistidas(tipoOrdenacao);
                break;
            case DESEJO_ASSISTIR:
                usuario.ordenarDesejoAssistir(tipoOrdenacao);
                break;
        }
        // Após ordenar, atualiza a exibição da tela para refletir a nova ordem
        // Não é necessário salvar aqui, pois o objeto Usuario já foi modificado em memória
        // e as funções adicionar/remover Serie já chamam salvar().
        atualizarTelaGerenciamento(panel, series, usuario, tipoSerie, cl, cards, cardName);
    }

    /**
     * Exibe os resultados da pesquisa de séries em uma nova tela.
     * Inclui botões de ordenação para os resultados.
     *
     * @param telaResultados O JPanel da tela de resultados.
     * @param resultados A lista de séries encontradas na pesquisa.
     * @param usuario O objeto Usuario atual.
     * @param cl O CardLayout.
     * @param cards O JPanel pai de cards.
     */
    private static void exibirResultadosPesquisa(JPanel telaResultados, List<Serie> resultados, Usuario usuario, CardLayout cl, JPanel cards) {
        telaResultados.removeAll(); // Limpa o painel antes de redesenhar
        // Define o layout como GridLayout com 5 colunas para os botões de ordenação e 4 colunas para os resultados
        telaResultados.setLayout(new GridLayout(0, 5, 10, 10));

        JLabel filtro = new JLabel("Filtrar por:", JLabel.CENTER);
        JButton ordAlfabetica = new JButton("Ordem Alfabética");
        JButton ordNota = new JButton("Nota Geral");
        JButton ordDataEstreia = new JButton("Data de Estreia");
        JButton ordStatus = new JButton("Status");

        // Adiciona os botões de ordenação para os resultados da pesquisa
        telaResultados.add(filtro);
        telaResultados.add(ordAlfabetica);
        telaResultados.add(ordNota);
        telaResultados.add(ordDataEstreia);
        telaResultados.add(ordStatus);
        
        // Adiciona uma linha de separação para melhor visualização (opcional)
        telaResultados.add(new JLabel("--- Resultados ---", JLabel.CENTER));
        telaResultados.add(new JLabel(""));
        telaResultados.add(new JLabel(""));
        telaResultados.add(new JLabel("")); 
        telaResultados.add(new JLabel(""));


        // ActionListeners para os botões de ordenação dos resultados da pesquisa

        // A ordenação aqui é apenas para a lista de 'resultados' temporária
        ordAlfabetica.addActionListener(a -> {
            resultados.sort((s1, s2) -> s1.getNome().compareToIgnoreCase(s2.getNome()));
            exibirResultadosPesquisa(telaResultados, resultados, usuario, cl, cards); // Recarrega a tela com a nova ordem
        });

        ordNota.addActionListener(a -> {
            resultados.sort((s1, s2) -> Double.compare(s2.getNota(), s1.getNota())); // Maior nota primeiro
            exibirResultadosPesquisa(telaResultados, resultados, usuario, cl, cards);
        });

        ordStatus.addActionListener(a -> {
            resultados.sort((s1, s2) -> s1.getStatus().compareToIgnoreCase(s2.getStatus()));
            exibirResultadosPesquisa(telaResultados, resultados, usuario, cl, cards);
        });

        ordDataEstreia.addActionListener(a -> {
            resultados.sort((s1, s2) -> {
                try {
                    // Trata datas desconhecidas para ordenação
                    String data1 = s1.getDataEstreia().equals("Desconhecida") ? "0000-01-01" : s1.getDataEstreia();
                    String data2 = s2.getDataEstreia().equals("Desconhecida") ? "0000-01-01" : s2.getDataEstreia();
                    return data1.compareTo(data2);
                } catch (Exception ex) {
                    return 0; // Em caso de erro na data, não altera a ordem
                }
            });
            exibirResultadosPesquisa(telaResultados, resultados, usuario, cl, cards);
        });

        // Adiciona cada série encontrada com seus botões de ação
        for (Serie serie : resultados) {
            JButton detalhesButton = new JButton(serie.getNome()); // Botão para ver detalhes da série
            JButton favButton = new JButton("Favoritar");
            JButton assistButton = new JButton("Assistida");
            JButton desejaButton = new JButton("Deseja Assistir");

            // Adiciona os componentes da série, ajustando para 5 colunas
            telaResultados.add(detalhesButton);
            telaResultados.add(favButton);
            telaResultados.add(assistButton);
            telaResultados.add(desejaButton);
            telaResultados.add(new JLabel("")); 


            // Ação para exibir detalhes da série
            detalhesButton.addActionListener(a -> {
                JOptionPane.showMessageDialog(telaResultados,
                        "Nome: " + serie.getNome() + "\n" +
                                "Idioma: " + serie.getIdioma() + "\n" +
                                "Gênero: " + serie.getGeneros() + "\n" +
                                "Nota: " + serie.getNota() + "\n" +
                                "Status: " + serie.getStatus() + "\n" +
                                "Data de estreia: " + serie.getDataEstreia() + "\n" +
                                "Data de conclusão: " + serie.getDataFim() + "\n" +
                                "Emissora: " + serie.getEmissora());
            });

            // Ações para adicionar a série às listas do usuário
            favButton.addActionListener(a -> {
                usuario.adicionarSerie(TipoSerie.FAVORITA, serie); // Chama adicionarSerie, que já salva
                JOptionPane.showMessageDialog(telaResultados, serie.getNome() + " Adicionada aos favoritos.");
            });

            assistButton.addActionListener(a -> {
                usuario.adicionarSerie(TipoSerie.ASSISTIDA, serie); // Chama adicionarSerie, que já salva
                JOptionPane.showMessageDialog(telaResultados, serie.getNome() + " Adicionada aos assistidos.");
            });

            desejaButton.addActionListener(a -> {
                usuario.adicionarSerie(TipoSerie.DESEJO_ASSISTIR, serie); // Chama adicionarSerie, que já salva
                JOptionPane.showMessageDialog(telaResultados, serie.getNome() + " Adicionada ao desejo assistir.");
            });
        }

        JButton voltarResultados = new JButton("Voltar");
        telaResultados.add(voltarResultados); // Adiciona o botão de voltar
        telaResultados.add(new JLabel("")); 
        telaResultados.add(new JLabel("")); 
        telaResultados.add(new JLabel("")); 
        telaResultados.add(new JLabel("")); 


        voltarResultados.addActionListener(a -> cl.show(cards, "telaBuscarSerie"));
        
        cl.show(cards, "telaResultados"); // Exibe o card dos resultados

        telaResultados.revalidate(); // Revalida o layout

        telaResultados.repaint();    
    }
}
