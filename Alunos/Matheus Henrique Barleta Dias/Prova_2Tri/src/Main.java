import java.awt.CardLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import api.TVMazeAPI;
import model.Serie;
import model.TipoSerie;
import model.Usuario;

public class Main {
    public static void main(String[] args) {
        // Cria frame
        JFrame frame = new JFrame("App de séries");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Painel principal que usa CardLayout
        JPanel cards = new JPanel(new CardLayout());

        // Tela de login
        String nomeUsuario = JOptionPane.showInputDialog(null, "Digite seu nome ou apelido:");
        if (nomeUsuario == null || nomeUsuario.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nome inválido. Encerrando.");
            System.exit(0);
        }
        Usuario usuario = Usuario.carregarOuCriarUsuario(nomeUsuario);
        JOptionPane.showMessageDialog(null, "Bem-vindo(a), " + usuario.getNome() + "!");

        // Tela principal
        JPanel telaPrincipal = new JPanel();
        telaPrincipal.setBackground(Color.WHITE);
        telaPrincipal.setLayout(new GridLayout(2,2,10,10));
        JButton buscarSerie = new JButton("Procurar série por nome");
        JButton listaFavoritos = new JButton("Gerenciar lista de favoritos");
        JButton seriesAssistida = new JButton("Gerenciar séries já assistidas");
        JButton desejaAssistir = new JButton("Gerenciar séries que deseja assistir");
        telaPrincipal.add(buscarSerie);
        telaPrincipal.add(listaFavoritos);
        telaPrincipal.add(seriesAssistida);
        telaPrincipal.add(desejaAssistir);

        // Tela buscar série
        JPanel telaBuscarSerie = new JPanel();
        telaBuscarSerie.setLayout(new GridLayout(3, 1, 5, 5));
        telaBuscarSerie.setBackground(Color.LIGHT_GRAY);
        JTextField nomeSerie = new JTextField(20);
        JButton pesquisar = new JButton("Pesquisar");
        JButton voltarTelaBuscarSerie = new JButton("Voltar");
        telaBuscarSerie.add(nomeSerie);
        telaBuscarSerie.add(pesquisar);
        telaBuscarSerie.add(voltarTelaBuscarSerie);

        // Tela de resultados
        JPanel telaResultados = new JPanel();

        // Tela favoritos
        JPanel telaFavoritos = new JPanel();
        JTextArea areaTextoFavoritos = new JTextArea(10, 30);
        for (Serie serie : usuario.getFavoritas()) {
            areaTextoFavoritos.append(serie.getNome() + "\n");
        }
        areaTextoFavoritos.setEditable(false);
        
        telaFavoritos.add(areaTextoFavoritos);
        

        // Tela séries assistidas
        JPanel telaAssistidas = new JPanel();
        
        // Tela séries deseja assistir
        JPanel telaDesejaAssistir = new JPanel();

        // Adiciona os cards ao frame
        cards.add(telaPrincipal, "telaPrincipal");
        cards.add(telaBuscarSerie, "telaBuscarSerie");
        cards.add(telaFavoritos, "telaFavoritos");
        cards.add(telaAssistidas, "telaAssistidas");
        cards.add(telaDesejaAssistir, "telaDesejaAssistir");
        cards.add(telaResultados, "telaResultados");
        frame.add(cards);
        frame.setVisible(true);
        CardLayout cl = (CardLayout) cards.getLayout();

        //Mudança de telas
        buscarSerie.addActionListener(e -> cl.show(cards, "telaBuscarSerie"));
        listaFavoritos.addActionListener(e -> {
            telaFavoritos.removeAll();
            telaFavoritos.setLayout(new GridLayout(0, 2, 10, 10));
            JButton voltarTelaFavoritos = new JButton("Voltar");
            
            for (Serie serie : usuario.getFavoritas()) {
                JLabel labelNomeSerie = new JLabel(serie.getNome(), JLabel.CENTER);
                JButton removerSerie = new JButton("Remover série");
                telaFavoritos.add(labelNomeSerie);
                telaFavoritos.add(removerSerie);

                removerSerie.addActionListener(a -> { 
                    usuario.removerSerie(TipoSerie.FAVORITA, serie);
                    JOptionPane.showMessageDialog(frame, serie.getNome() + " Removida dos favoritos.");
                    cl.show(cards, "telaFavoritos");
                });
            }

            telaFavoritos.add(voltarTelaFavoritos);
            cl.show(cards, "telaFavoritos");
            voltarTelaFavoritos.addActionListener(b -> cl.show(cards, "telaPrincipal"));
        });
        
        seriesAssistida.addActionListener(e -> {
            telaAssistidas.removeAll();
            telaAssistidas.setLayout(new GridLayout(0, 2, 10, 10));
            JButton voltarTelaAssistidas = new JButton("Voltar");
            
            for (Serie serie : usuario.getAssistidas()) {
                JLabel labelNomeSerie = new JLabel(serie.getNome(), JLabel.CENTER);
                JButton removerSerie = new JButton("Remover série");
                telaAssistidas.add(labelNomeSerie);
                telaAssistidas.add(removerSerie);

                removerSerie.addActionListener(a -> { 
                    usuario.removerSerie(TipoSerie.ASSISTIDA, serie);
                    JOptionPane.showMessageDialog(frame, serie.getNome() + " Removida das assistidas.");
                    cl.show(cards, "telaAssistidas");
                });
            }

            telaAssistidas.add(voltarTelaAssistidas);
            voltarTelaAssistidas.addActionListener(b -> cl.show(cards, "telaPrincipal"));
            cl.show(cards, "telaAssistidas");
        });

        desejaAssistir.addActionListener(e -> {
            telaDesejaAssistir.removeAll();
            telaDesejaAssistir.setLayout(new GridLayout(0, 2, 10, 10));
            JButton voltarTelaDesejaAssistir = new JButton("Voltar");
            
            for (Serie serie : usuario.getDesejoAssistir()) {
                JLabel labelNomeSerie = new JLabel(serie.getNome(), JLabel.CENTER);
                JButton removerSerie = new JButton("Remover série");
                telaDesejaAssistir.add(labelNomeSerie);
                telaDesejaAssistir.add(removerSerie);

                removerSerie.addActionListener(a -> { 
                    usuario.removerSerie(TipoSerie.DESEJO_ASSISTIR, serie);
                    JOptionPane.showMessageDialog(frame, serie.getNome() + " Removida de Desejo Assistir.");
                    cl.show(cards, "telaDesejaAssistir");
                });
            }

            telaDesejaAssistir.add(voltarTelaDesejaAssistir);
            voltarTelaDesejaAssistir.addActionListener(b -> cl.show(cards, "telaPrincipal"));
            cl.show(cards, "telaDesejaAssistir");
        });

        seriesAssistida.addActionListener(e -> cl.show(cards, "telaAssistidas"));
        desejaAssistir.addActionListener(e -> cl.show(cards, "telaDesejaAssistir"));
        voltarTelaBuscarSerie.addActionListener(e -> cl.show(cards, "telaPrincipal"));
        frame.setVisible(true);

        //Pesquisa série
        pesquisar.addActionListener(e -> {
            if (nomeSerie.getText().isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Digite o nome da série.");
                return;
            }

            List<Serie> resultados = TVMazeAPI.buscarSeries(nomeSerie.getText());

            if (resultados.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Nenhuma série encontrada.");
            } else {
                // Adiciona a tela de resultados
                telaResultados.removeAll();
                telaResultados.setLayout(new GridLayout(0, 4, 10, 10));
                JLabel filtro = new JLabel("Filtrar por:");
                JButton ordAlfabetica = new JButton("Ordem alfabética");
                JButton ordNota = new JButton("Nota geral");
                JButton ordData = new JButton("Data de conclusão");
                telaResultados.add(filtro);
                telaResultados.add(ordAlfabetica);
                telaResultados.add(ordNota);
                telaResultados.add(ordData);

                for (int i = 0; i < resultados.size(); i++) {
                    JButton t = new JButton();
                    Serie serie = resultados.get(i);
                    t.setText(serie.getNome());
                    JButton favButton = new JButton("Favoritar");
                    JButton assistButton = new JButton("Assistida");
                    JButton desejaButton = new JButton("Deseja Assistir");
                    telaResultados.add(t);
                    telaResultados.add(favButton);
                    telaResultados.add(assistButton);
                    telaResultados.add(desejaButton);

                    t.addActionListener(a -> {
                        JOptionPane.showMessageDialog(frame, 
                                "Nome: " + serie.getNome() + "\n" +
                                "Idioma: " + serie.getIdioma() + "\n" +
                                "Gênero: " + serie.getGeneros() + "\n" +
                                "Nota: " + serie.getNota() + "\n" +
                                "Status: " + serie.getStatus() + "\n" +
                                "Data de estreia: " + serie.getDataEstreia() + "\n" +
                                "Data de conclusão: " + serie.getDataFim() + "\n" +
                                "Emissora: " + serie.getEmissora());
                    });    

                    favButton.addActionListener(a -> {
                        usuario.adicionarSerie(TipoSerie.FAVORITA, serie);
                        JOptionPane.showMessageDialog(frame, serie.getNome() + " Adicionada aos favoritos.");
                    });

                    assistButton.addActionListener(a -> {
                        usuario.adicionarSerie(TipoSerie.ASSISTIDA, serie);
                        JOptionPane.showMessageDialog(frame, serie.getNome() + " Adicionada ao assistidos.");
                    });

                    desejaButton.addActionListener(a -> {
                        usuario.adicionarSerie(TipoSerie.DESEJO_ASSISTIR, serie);
                        JOptionPane.showMessageDialog(frame, serie.getNome() + " Adicionada ao desejo assistir.");
                    });
                }

                ordAlfabetica.addActionListener(a -> {
                    resultados.sort((s1, s2) -> s1.getNome().compareTo(s2.getNome()));
                    for(Serie resultado : resultados){
                        System.out.println(resultado);
                    }
                });

                JButton voltarResultados = new JButton("Voltar");
                voltarResultados.addActionListener(a -> cl.show(cards, "telaBuscarSerie"));
                telaResultados.add(voltarResultados);

                cl.show(cards, "telaResultados");
            }
        });
    }
}
