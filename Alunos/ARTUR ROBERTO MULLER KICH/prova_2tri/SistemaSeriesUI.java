package org.example.ui;

import org.example.model.Serie;
import org.example.model.Usuario;
import org.example.service.TVMazeService;
import org.example.util.JsonPersistencia;
import org.example.util.Ordenacao;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class SistemaSeriesUI extends JFrame {
    private final TVMazeService tvMazeService;
    private Usuario usuario;
    

    private JTextField campoBusca;
    private JButton botaoBuscar;
    private JList<Serie> listaResultados;
    private DefaultListModel<Serie> modeloResultados;
    private JTextArea areaDetalhes;
    
    private JTabbedPane abas;
    private JList<Serie> listaFavoritos;
    private DefaultListModel<Serie> modeloFavoritos;
    private JList<Serie> listaAssistidas;
    private DefaultListModel<Serie> modeloAssistidas;
    private JList<Serie> listaParaAssistir;
    private DefaultListModel<Serie> modeloParaAssistir;
    
    private JComboBox<Ordenacao.TipoOrdenacao> comboOrdenacao;
    

    private final DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    
    public SistemaSeriesUI() {
        this.tvMazeService = new TVMazeService();
        

        setTitle("Sistema de Séries de TV");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        

        setLayout(new BorderLayout());
        

        inicializarComponentes();
        

        carregarOuCriarUsuario();
        

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                salvarDados();
            }
        });
        

        setVisible(true);
    }
    
    private void inicializarComponentes() {

        JPanel painelBusca = new JPanel(new BorderLayout());
        JLabel labelBusca = new JLabel("Buscar série:");
        campoBusca = new JTextField();
        botaoBuscar = new JButton("Buscar");
        
        painelBusca.add(labelBusca, BorderLayout.WEST);
        painelBusca.add(campoBusca, BorderLayout.CENTER);
        painelBusca.add(botaoBuscar, BorderLayout.EAST);
        

        JSplitPane divisorCentral = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        divisorCentral.setResizeWeight(0.3);
        

        JPanel painelEsquerdo = new JPanel(new BorderLayout());
        JLabel labelResultados = new JLabel("Resultados da busca:");
        modeloResultados = new DefaultListModel<>();
        listaResultados = new JList<>(modeloResultados);
        JScrollPane scrollResultados = new JScrollPane(listaResultados);
        
        painelEsquerdo.add(labelResultados, BorderLayout.NORTH);
        painelEsquerdo.add(scrollResultados, BorderLayout.CENTER);
        

        JPanel painelDireito = new JPanel(new BorderLayout());
        areaDetalhes = new JTextArea();
        areaDetalhes.setEditable(false);
        areaDetalhes.setLineWrap(true);
        areaDetalhes.setWrapStyleWord(true);
        JScrollPane scrollDetalhes = new JScrollPane(areaDetalhes);
        
        JPanel painelBotoes = new JPanel(new GridLayout(1, 3, 5, 0));
        JButton botaoFavorito = new JButton("Adicionar aos Favoritos");
        JButton botaoAssistida = new JButton("Marcar como Assistida");
        JButton botaoParaAssistir = new JButton("Adicionar Para Assistir");
        
        painelBotoes.add(botaoFavorito);
        painelBotoes.add(botaoAssistida);
        painelBotoes.add(botaoParaAssistir);
        
        painelDireito.add(scrollDetalhes, BorderLayout.CENTER);
        painelDireito.add(painelBotoes, BorderLayout.SOUTH);
        
        divisorCentral.setLeftComponent(painelEsquerdo);
        divisorCentral.setRightComponent(painelDireito);
        

        abas = new JTabbedPane();
        

        JPanel painelFavoritos = new JPanel(new BorderLayout());
        modeloFavoritos = new DefaultListModel<>();
        listaFavoritos = new JList<>(modeloFavoritos);
        JScrollPane scrollFavoritos = new JScrollPane(listaFavoritos);
        JButton botaoRemoverFavorito = new JButton("Remover dos Favoritos");
        
        painelFavoritos.add(scrollFavoritos, BorderLayout.CENTER);
        painelFavoritos.add(botaoRemoverFavorito, BorderLayout.SOUTH);
        

        JPanel painelAssistidas = new JPanel(new BorderLayout());
        modeloAssistidas = new DefaultListModel<>();
        listaAssistidas = new JList<>(modeloAssistidas);
        JScrollPane scrollAssistidas = new JScrollPane(listaAssistidas);
        JButton botaoRemoverAssistida = new JButton("Remover das Assistidas");
        
        painelAssistidas.add(scrollAssistidas, BorderLayout.CENTER);
        painelAssistidas.add(botaoRemoverAssistida, BorderLayout.SOUTH);
        

        JPanel painelParaAssistir = new JPanel(new BorderLayout());
        modeloParaAssistir = new DefaultListModel<>();
        listaParaAssistir = new JList<>(modeloParaAssistir);
        JScrollPane scrollParaAssistir = new JScrollPane(listaParaAssistir);
        JButton botaoRemoverParaAssistir = new JButton("Remover da Lista Para Assistir");
        
        painelParaAssistir.add(scrollParaAssistir, BorderLayout.CENTER);
        painelParaAssistir.add(botaoRemoverParaAssistir, BorderLayout.SOUTH);
        
        abas.addTab("Favoritos", painelFavoritos);
        abas.addTab("Assistidas", painelAssistidas);
        abas.addTab("Para Assistir", painelParaAssistir);
        

        JPanel painelOrdenacao = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel labelOrdenacao = new JLabel("Ordenar por:");
        comboOrdenacao = new JComboBox<>(Ordenacao.TipoOrdenacao.values());
        
        painelOrdenacao.add(labelOrdenacao);
        painelOrdenacao.add(comboOrdenacao);
        
        JPanel painelInferior = new JPanel(new BorderLayout());
        painelInferior.add(painelOrdenacao, BorderLayout.NORTH);
        painelInferior.add(abas, BorderLayout.CENTER);
        

        add(painelBusca, BorderLayout.NORTH);
        add(divisorCentral, BorderLayout.CENTER);
        add(painelInferior, BorderLayout.SOUTH);
        

        botaoBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarSeries();
            }
        });
        
        listaResultados.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    Serie serieSelecionada = listaResultados.getSelectedValue();
                    exibirDetalhesSerie(serieSelecionada);
                }
            }
        });
        
        listaFavoritos.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    Serie serieSelecionada = listaFavoritos.getSelectedValue();
                    exibirDetalhesSerie(serieSelecionada);
                }
            }
        });
        
        listaAssistidas.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    Serie serieSelecionada = listaAssistidas.getSelectedValue();
                    exibirDetalhesSerie(serieSelecionada);
                }
            }
        });
        
        listaParaAssistir.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    Serie serieSelecionada = listaParaAssistir.getSelectedValue();
                    exibirDetalhesSerie(serieSelecionada);
                }
            }
        });
        
        botaoFavorito.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Serie serieSelecionada = listaResultados.getSelectedValue();
                if (serieSelecionada != null) {
                    usuario.adicionarFavorito(serieSelecionada);
                    atualizarListaFavoritos();
                }
            }
        });
        
        botaoAssistida.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Serie serieSelecionada = listaResultados.getSelectedValue();
                if (serieSelecionada != null) {
                    usuario.adicionarAssistida(serieSelecionada);
                    atualizarListaAssistidas();
                }
            }
        });
        
        botaoParaAssistir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Serie serieSelecionada = listaResultados.getSelectedValue();
                if (serieSelecionada != null) {
                    usuario.adicionarParaAssistir(serieSelecionada);
                    atualizarListaParaAssistir();
                }
            }
        });
        
        botaoRemoverFavorito.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Serie serieSelecionada = listaFavoritos.getSelectedValue();
                if (serieSelecionada != null) {
                    usuario.removerFavorito(serieSelecionada);
                    atualizarListaFavoritos();
                }
            }
        });
        
        botaoRemoverAssistida.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Serie serieSelecionada = listaAssistidas.getSelectedValue();
                if (serieSelecionada != null) {
                    usuario.removerAssistida(serieSelecionada);
                    atualizarListaAssistidas();
                }
            }
        });
        
        botaoRemoverParaAssistir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Serie serieSelecionada = listaParaAssistir.getSelectedValue();
                if (serieSelecionada != null) {
                    usuario.removerParaAssistir(serieSelecionada);
                    atualizarListaParaAssistir();
                }
            }
        });
        
        comboOrdenacao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ordenarListas();
            }
        });
    }
    
    private void carregarOuCriarUsuario() {
        try {

            usuario = JsonPersistencia.carregarUsuario();
            

            if (usuario == null) {
                String nome = JOptionPane.showInputDialog(this, "Digite seu nome ou apelido:", "Bem-vindo ao Sistema de Séries", JOptionPane.QUESTION_MESSAGE);
                if (nome == null || nome.trim().isEmpty()) {
                    nome = "Usuário";
                }
                usuario = new Usuario(nome);
                

                setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                JOptionPane.showMessageDialog(this, 
                        "Carregando dados iniciais de séries.\nIsso pode levar alguns segundos...", 
                        "Carregando", JOptionPane.INFORMATION_MESSAGE);
                

                try {
                    List<Serie> seriesPreCarregadas = tvMazeService.carregarDadosPreDefinidos();
                    if (!seriesPreCarregadas.isEmpty()) {

                        for (int i = 0; i < seriesPreCarregadas.size(); i++) {
                            Serie serie = seriesPreCarregadas.get(i);
                            if (serie != null) {
                                if (i % 3 == 0) {
                                    usuario.adicionarFavorito(serie);
                                } else if (i % 3 == 1) {
                                    usuario.adicionarAssistida(serie);
                                } else {
                                    usuario.adicionarParaAssistir(serie);
                                }
                            }
                        }
                    }
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(this, 
                            "Erro ao carregar dados pré-definidos: " + e.getMessage() + 
                            "\nO sistema continuará funcionando, mas sem dados iniciais.", 
                            "Aviso", JOptionPane.WARNING_MESSAGE);
                    e.printStackTrace();
                } finally {
                    setCursor(Cursor.getDefaultCursor());
                }
                

                try {
                    JsonPersistencia.salvarUsuario(usuario);
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(this, 
                            "Erro ao salvar dados do usuário: " + e.getMessage(), 
                            "Aviso", JOptionPane.WARNING_MESSAGE);
                }
            }
            

            setTitle("Sistema de Séries de TV - " + usuario.getNome());
            
            // Atualiza as listas
            atualizarTodasListas();
            
        } catch (Exception e) {

            JOptionPane.showMessageDialog(this, 
                    "Erro ao inicializar usuário: " + e.getMessage() + 
                    "\nUm novo usuário será criado.", 
                    "Erro", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            
            usuario = new Usuario("Usuário");
            setTitle("Sistema de Séries de TV - Usuário");
        }
    }
    
    private void buscarSeries() {
        String query = campoBusca.getText().trim();
        if (query.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Digite um termo para busca", "Busca vazia", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        
        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        
        try {
            List<Serie> series = tvMazeService.buscarSeries(query);
            modeloResultados.clear();
            
            if (series.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Nenhuma série encontrada para: " + query, "Busca sem resultados", JOptionPane.INFORMATION_MESSAGE);
            } else {
                for (Serie serie : series) {
                    modeloResultados.addElement(serie);
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Erro ao buscar séries: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        } finally {
            setCursor(Cursor.getDefaultCursor());
        }
    }
    
    private void exibirDetalhesSerie(Serie serie) {
        if (serie == null) {
            areaDetalhes.setText("");
            return;
        }
        
        StringBuilder detalhes = new StringBuilder();
        detalhes.append("Nome: ").append(serie.getName()).append("\n\n");
        
        detalhes.append("Idioma: ").append(serie.getLanguage() != null ? serie.getLanguage() : "Não informado").append("\n\n");
        
        detalhes.append("Gêneros: ");
        if (serie.getGenres() != null && !serie.getGenres().isEmpty()) {
            detalhes.append(String.join(", ", serie.getGenres()));
        } else {
            detalhes.append("Não informados");
        }
        detalhes.append("\n\n");
        
        detalhes.append("Nota: ");
        if (serie.getNotaMedia() != null) {
            detalhes.append(serie.getNotaMedia());
        } else {
            detalhes.append("Não avaliada");
        }
        detalhes.append("\n\n");
        
        detalhes.append("Estado: ").append(serie.getStatus() != null ? serie.getStatus() : "Não informado").append("\n\n");
        
        detalhes.append("Data de Estreia: ");
        if (serie.getPremiered() != null) {
            detalhes.append(formatarData(serie.getPremiered()));
        } else {
            detalhes.append("Não informada");
        }
        detalhes.append("\n\n");
        
        detalhes.append("Data de Término: ");
        if (serie.getEnded() != null) {
            detalhes.append(formatarData(serie.getEnded()));
        } else {
            detalhes.append("Em andamento ou não informada");
        }
        detalhes.append("\n\n");
        
        detalhes.append("Emissora: ").append(serie.getEmissora());
        
        areaDetalhes.setText(detalhes.toString());
        areaDetalhes.setCaretPosition(0);
    }
    
    private String formatarData(LocalDate data) {
        if (data == null) {
            return "Não informada";
        }
        return data.format(formatoData);
    }
    
    private void atualizarListaFavoritos() {
        modeloFavoritos.clear();
        List<Serie> favoritos = new ArrayList<>(usuario.getFavoritos());
        ordenarLista(favoritos);
        for (Serie serie : favoritos) {
            modeloFavoritos.addElement(serie);
        }
    }
    
    private void atualizarListaAssistidas() {
        modeloAssistidas.clear();
        List<Serie> assistidas = new ArrayList<>(usuario.getAssistidas());
        ordenarLista(assistidas);
        for (Serie serie : assistidas) {
            modeloAssistidas.addElement(serie);
        }
    }
    
    private void atualizarListaParaAssistir() {
        modeloParaAssistir.clear();
        List<Serie> paraAssistir = new ArrayList<>(usuario.getParaAssistir());
        ordenarLista(paraAssistir);
        for (Serie serie : paraAssistir) {
            modeloParaAssistir.addElement(serie);
        }
    }
    
    private void atualizarTodasListas() {
        atualizarListaFavoritos();
        atualizarListaAssistidas();
        atualizarListaParaAssistir();
    }
    
    private void ordenarListas() {
        ordenarLista(usuario.getFavoritos());
        ordenarLista(usuario.getAssistidas());
        ordenarLista(usuario.getParaAssistir());
        atualizarTodasListas();
    }
    
    private void ordenarLista(List<Serie> series) {
        Ordenacao.TipoOrdenacao tipoOrdenacao = (Ordenacao.TipoOrdenacao) comboOrdenacao.getSelectedItem();
        if (tipoOrdenacao != null) {
            Ordenacao.ordenarSeries(series, tipoOrdenacao);
        }
    }
    
    private void salvarDados() {
        try {
            JsonPersistencia.salvarUsuario(usuario);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Erro ao salvar dados: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
