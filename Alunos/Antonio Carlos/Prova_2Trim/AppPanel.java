package ui;

import com.trabalhotvmaze.series.Serie;
import com.trabalhotvmaze.series.TvMazeApiClient;
import com.trabalhotvmaze.series.Usuario;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class AppPanel extends JPanel {
    private MainFrame mainFrame;
    private Usuario usuario;
    private TvMazeApiClient apiClient;

    // Componentes da UI
    private JTextField searchField;
    private JButton searchButton;
    private JList<Serie> seriesList;
    private DefaultListModel<Serie> listModel;
    private JTextArea detailsArea;
    private JButton viewFavoritesButton, viewWatchedButton, viewToWatchButton;
    private JButton addToFavoritesButton, addToWatchedButton, addToToWatchButton;
    private JButton removeButton;
    private JComboBox<String> sortComboBox;
    
    private List<Serie> currentDisplayedList;

    public AppPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        this.apiClient = new TvMazeApiClient();
        
        // --- CONFIGURAÇÃO DE LAYOUT E CORES PRINCIPAIS ---
        setLayout(new BorderLayout(10, 10));
        setBackground(UICores.BACKGROUND);
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // ----- PAINEL NORTE (BUSCA) -----
        JPanel northPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        northPanel.setBackground(UICores.BACKGROUND);
        JLabel searchLabel = new JLabel("Buscar Série:");
        searchLabel.setForeground(UICores.FOREGROUND);
        northPanel.add(searchLabel);
        
        searchField = new JTextField(30);
        customizeTextField(searchField);
        
        searchButton = new JButton("Buscar");
        customizeButton(searchButton);
        
        northPanel.add(searchField);
        northPanel.add(searchButton);
        add(northPanel, BorderLayout.NORTH);

        // ----- PAINEL CENTRAL (LISTA E DETALHES) -----
        listModel = new DefaultListModel<>();
        seriesList = new JList<>(listModel);
        customizeJList(seriesList);
        seriesList.setCellRenderer(new SerieListCellRenderer());
        JScrollPane listScrollPane = new JScrollPane(seriesList);
        customizeScrollPane(listScrollPane);

        detailsArea = new JTextArea("Selecione uma série para ver os detalhes.");
        detailsArea.setEditable(false);
        detailsArea.setLineWrap(true);
        detailsArea.setWrapStyleWord(true);
        customizeTextArea(detailsArea);
        JScrollPane detailsScrollPane = new JScrollPane(detailsArea);
        customizeScrollPane(detailsScrollPane);

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, listScrollPane, detailsScrollPane);
        splitPane.setDividerLocation(300);
        splitPane.setBorder(null);
        splitPane.setBackground(UICores.BACKGROUND);
        splitPane.setUI(new javax.swing.plaf.basic.BasicSplitPaneUI() {
            public javax.swing.plaf.basic.BasicSplitPaneDivider createDefaultDivider() {
                return new javax.swing.plaf.basic.BasicSplitPaneDivider(this) {
                    public void setBorder(javax.swing.border.Border b) {}
                    @Override
                    public void paint(Graphics g) {
                        g.setColor(UICores.BACKGROUND);
                        g.fillRect(0, 0, getSize().width, getSize().height);
                        super.paint(g);
                    }
                };
            }
        });
        add(splitPane, BorderLayout.CENTER);

        // ----- PAINEL SUL (BOTÕES DE LISTAS) -----
        JPanel southPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        southPanel.setBackground(UICores.BACKGROUND);
        viewFavoritesButton = new JButton("Ver Favoritos");
        viewWatchedButton = new JButton("Ver Assistidas");
        viewToWatchButton = new JButton("Ver 'Para Assistir'");
        customizeButton(viewFavoritesButton);
        customizeButton(viewWatchedButton);
        customizeButton(viewToWatchButton);
        southPanel.add(viewFavoritesButton);
        southPanel.add(viewWatchedButton);
        southPanel.add(viewToWatchButton);
        add(southPanel, BorderLayout.SOUTH);

        // ----- PAINEL LESTE (AÇÕES E ORDENAÇÃO) -----
        JPanel eastPanel = new JPanel();
        eastPanel.setLayout(new BoxLayout(eastPanel, BoxLayout.Y_AXIS));
        eastPanel.setBackground(UICores.BACKGROUND);
        TitledBorder eastPanelBorder = BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(UICores.BORDER_COLOR),
            "Painel de Controle",
            TitledBorder.CENTER,
            TitledBorder.TOP,
            new Font("Segoe UI", Font.BOLD, 12),
            UICores.FOREGROUND
        );
        eastPanel.setBorder(eastPanelBorder);

        JPanel actionsPanel = new JPanel(new GridLayout(0, 1, 5, 8));
        actionsPanel.setOpaque(false);
        actionsPanel.setBorder(BorderFactory.createEmptyBorder(10, 5, 10, 5));
        
        addToFavoritesButton = new JButton("Add aos Favoritos");
        addToWatchedButton = new JButton("Add às Assistidas");
        addToToWatchButton = new JButton("Add à Lista 'Para Assistir'"); 
        removeButton = new JButton("Remover da Lista");
        
        customizeButton(addToFavoritesButton);
        customizeButton(addToWatchedButton);
        customizeButton(addToToWatchButton);
        customizeButton(removeButton);

        actionsPanel.add(addToFavoritesButton);
        actionsPanel.add(addToWatchedButton);
        actionsPanel.add(addToToWatchButton);
        actionsPanel.add(removeButton);

        JPanel sortPanel = new JPanel(new BorderLayout());
        sortPanel.setOpaque(false);
        sortPanel.setBorder(BorderFactory.createEmptyBorder(15, 5, 10, 5));
        JLabel sortLabel = new JLabel("Ordenar por:");
        sortLabel.setForeground(UICores.FOREGROUND);
        sortLabel.setHorizontalAlignment(SwingConstants.CENTER);
        
        String[] sortOptions = {"Padrão", "Nome (A-Z)", "Nota (Maior)", "Status", "Data de Estreia"};
        sortComboBox = new JComboBox<>(sortOptions);
        sortComboBox.setBackground(UICores.COMPONENT_BACKGROUND);
        sortComboBox.setForeground(UICores.FOREGROUND);
        
        sortPanel.add(sortLabel, BorderLayout.NORTH);
        sortPanel.add(sortComboBox, BorderLayout.CENTER);

        eastPanel.add(actionsPanel);
        eastPanel.add(Box.createVerticalGlue());
        eastPanel.add(sortPanel);

        add(eastPanel, BorderLayout.EAST);
        
        addListeners();
    }
    
    // --- MÉTODOS DE CUSTOMIZAÇÃO DE COMPONENTES ---

    private void customizeButton(JButton button) {
        button.setBackground(UICores.ACCENT_COLOR_BLUE);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setFont(new Font("Segoe UI", Font.BOLD, 12));
        button.setBorder(BorderFactory.createEmptyBorder(8, 18, 8, 18));
    }
    
    private void customizeTextComponent(JTextComponent textComponent) {
        textComponent.setBackground(UICores.COMPONENT_BACKGROUND);
        textComponent.setForeground(UICores.FOREGROUND);
        textComponent.setCaretColor(Color.WHITE);
        textComponent.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(UICores.BORDER_COLOR, 1),
            BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        textComponent.setSelectionColor(UICores.ACCENT_COLOR_BLUE);
        textComponent.setSelectedTextColor(Color.WHITE);
    }

    private void customizeTextField(JTextField textField) {
        customizeTextComponent(textField);
    }
    
    private void customizeTextArea(JTextArea textArea) {
        customizeTextComponent(textArea);
    }
    
    private void customizeJList(JList<?> list) {
        list.setBackground(UICores.COMPONENT_BACKGROUND);
        list.setForeground(UICores.FOREGROUND);
        list.setSelectionBackground(UICores.ACCENT_COLOR_BLUE);
        list.setSelectionForeground(Color.WHITE);
    }
    
    private void customizeScrollPane(JScrollPane scrollPane) {
        scrollPane.setBorder(BorderFactory.createLineBorder(UICores.BORDER_COLOR, 1));
        scrollPane.getViewport().setBackground(UICores.COMPONENT_BACKGROUND);
        scrollPane.getVerticalScrollBar().setUI(new javax.swing.plaf.basic.BasicScrollBarUI() {
            @Override
            protected void configureScrollBarColors() {
                this.thumbColor = UICores.ACCENT_COLOR_BLUE;
                this.trackColor = UICores.COMPONENT_BACKGROUND;
            }
            @Override
            protected JButton createDecreaseButton(int orientation) {
                return createZeroButton();
            }
            @Override    
            protected JButton createIncreaseButton(int orientation) {
                return createZeroButton();
            }
            private JButton createZeroButton() {
                JButton jbutton = new JButton();
                jbutton.setPreferredSize(new Dimension(0, 0));
                jbutton.setMinimumSize(new Dimension(0, 0));
                jbutton.setMaximumSize(new Dimension(0, 0));
                return jbutton;
            }
        });
    }

    // --- MÉTODOS DE LÓGICA E CONTROLE ---

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
        showFavorites();
    }

    private void addListeners() {
        searchButton.addActionListener(e -> searchSeries());
        searchField.addActionListener(e -> searchSeries());
        seriesList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                Serie selected = seriesList.getSelectedValue();
                displayDetails(selected);
            }
        });
        viewFavoritesButton.addActionListener(e -> showFavorites());
        viewWatchedButton.addActionListener(e -> showWatched());
        viewToWatchButton.addActionListener(e -> showToWatch());
        addToFavoritesButton.addActionListener(e -> addSelectedTo(usuario.getFavoritos(), "Favoritos"));
        addToWatchedButton.addActionListener(e -> addSelectedTo(usuario.getSeriesAssistidas(), "Assistidas"));
        addToToWatchButton.addActionListener(e -> addSelectedTo(usuario.getSeriesParaAssistir(), "'Para Assistir'"));
        removeButton.addActionListener(e -> removeSelected());
        sortComboBox.addActionListener(e -> sortCurrentList());
    }

    private void searchSeries() {
        String query = searchField.getText().trim();
        if (query.isEmpty()) return;
        
        List<Serie> results = apiClient.buscarSeries(query);
        updateList(results, "Resultados da Busca");
    }
    
    private void showFavorites() {
        if(usuario != null) updateList(usuario.getFavoritos(), "Favoritos");
    }
    
    private void showWatched() {
        if(usuario != null) updateList(usuario.getSeriesAssistidas(), "Séries Assistidas");
    }
    
    private void showToWatch() {
        if(usuario != null) updateList(usuario.getSeriesParaAssistir(), "'Para Assistir'");
    }

    private void updateList(List<Serie> series, String title) {
        currentDisplayedList = series;
        listModel.clear();
        if (series != null) {
            series.forEach(listModel::addElement);
        }
        
        TitledBorder border = BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(UICores.BORDER_COLOR),
            title,
            TitledBorder.DEFAULT_JUSTIFICATION,
            TitledBorder.DEFAULT_POSITION,
            new Font("Segoe UI", Font.BOLD, 14),
            UICores.FOREGROUND
        );
        this.setBorder(border);
        
        if (sortComboBox != null) {
            sortComboBox.setSelectedIndex(0); 
        }
        displayDetails(null);
        this.repaint();
    }
    
    private void displayDetails(Serie serie) {
        if (serie == null) {
            detailsArea.setText("Selecione uma série para ver os detalhes.");
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Nome: ").append(serie.getNome()).append("\n\n");
        sb.append("Idioma: ").append(serie.getIdioma() != null ? serie.getIdioma() : "N/A").append("\n");
        sb.append("Gêneros: ").append(serie.getGenerosFormatado()).append("\n\n");
        sb.append("Nota: ").append(String.format("%.1f", serie.getNota())).append("\n");
        sb.append("Status: ").append(serie.getStatus() != null ? serie.getStatus() : "N/A").append("\n\n");
        sb.append("Data de Estreia: ").append(serie.getDataEstreia() != null ? serie.getDataEstreia() : "N/A").append("\n");
        sb.append("Emissora: ").append(serie.getNomeEmissora()).append("\n");
        detailsArea.setText(sb.toString());
        detailsArea.setCaretPosition(0);
    }
    
    private void addSelectedTo(List<Serie> targetList, String listName) {
        Serie selected = seriesList.getSelectedValue();
        if(selected == null) {
            JOptionPane.showMessageDialog(this, "Nenhuma série selecionada.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if(!targetList.contains(selected)){
            targetList.add(selected);
            JOptionPane.showMessageDialog(this, "'" + selected.getNome() + "' adicionada a " + listName + ".", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Esta série já está na lista " + listName + ".", "Aviso", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    private void removeSelected() {
        Serie selected = seriesList.getSelectedValue();
        if(selected == null || currentDisplayedList == null || currentDisplayedList.isEmpty()) {
             JOptionPane.showMessageDialog(this, "Nenhuma série selecionada para remover.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        int confirm = JOptionPane.showConfirmDialog(this, "Tem certeza que deseja remover '" + selected.getNome() + "' desta lista?", "Confirmar Remoção", JOptionPane.YES_NO_OPTION);
        
        if(confirm == JOptionPane.YES_OPTION) {
            if (currentDisplayedList == usuario.getFavoritos()) usuario.removerFavorito(selected);
            else if (currentDisplayedList == usuario.getSeriesAssistidas()) usuario.removerAssistida(selected);
            else if (currentDisplayedList == usuario.getSeriesParaAssistir()) usuario.removerParaAssistir(selected);
            
            listModel.removeElement(selected);
            JOptionPane.showMessageDialog(this, "Série removida.", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    private void sortCurrentList() {
        if(currentDisplayedList == null || currentDisplayedList.isEmpty()) return;
        
        int selectedIndex = sortComboBox.getSelectedIndex();
        Comparator<Serie> comparator = null;
        
        switch(selectedIndex) {
            case 1: comparator = Comparator.comparing(Serie::getNome, String.CASE_INSENSITIVE_ORDER); break;
            case 2: comparator = Comparator.comparing(Serie::getNota).reversed(); break;
            case 3: comparator = Comparator.comparing(Serie::getStatus, Comparator.nullsLast(String::compareTo)); break;
            case 4: comparator = Comparator.comparing(Serie::getDataEstreiaAsDate, Comparator.nullsLast(Comparator.naturalOrder())); break;
            default:
                TitledBorder border = (TitledBorder) getBorder();
                String currentTitle = border != null ? border.getTitle() : "";
                if ("Favoritos".equals(currentTitle)) showFavorites();
                else if ("Séries Assistidas".equals(currentTitle)) showWatched();
                else if ("'Para Assistir'".equals(currentTitle)) showToWatch();
                else { 
                    searchSeries();
                }
                return;
        }
        
        List<Serie> sortedList = currentDisplayedList.stream().sorted(comparator).collect(Collectors.toList());
        listModel.clear();
        sortedList.forEach(listModel::addElement);
    }

    // --- CLASSE INTERNA PARA RENDERIZAR A LISTA ---
    private static class SerieListCellRenderer extends DefaultListCellRenderer {
        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

            if (value instanceof Serie) {
                setText(((Serie) value).getNome());
            }
            
            setOpaque(true); 

            if (isSelected) {
                setBackground(list.getSelectionBackground());
                setForeground(list.getSelectionForeground());
            } else {
                setBackground(list.getBackground());
                setForeground(list.getForeground());
            }
            
            setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

            return this;
        }
    }
}