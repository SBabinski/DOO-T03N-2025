package ui;

import com.trabalhotvmaze.series.GerenciadorDeDados;
import com.trabalhotvmaze.series.Usuario;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainFrame extends JFrame {
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private LoginPanel loginPanel;
    private AppPanel appPanel;

    private GerenciadorDeDados gerenciadorDeDados;
    private Usuario usuarioAtual;

    public MainFrame() {
        super("TV Series Tracker");
        this.gerenciadorDeDados = new GerenciadorDeDados();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 600);
        setLocationRelativeTo(null); // Centraliza a janela

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        loginPanel = new LoginPanel(this);
        mainPanel.add(loginPanel, "login");

        // O AppPanel será criado após o login
        appPanel = new AppPanel(this);
        mainPanel.add(appPanel, "app");

        add(mainPanel);

        // Listener para salvar os dados ao fechar a janela
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if (usuarioAtual != null) {
                    gerenciadorDeDados.salvarUsuario(usuarioAtual);
                    System.out.println("Dados de " + usuarioAtual.getNome() + " salvos.");
                }
                super.windowClosing(e);
            }
        });
    }

    public void login(String username) {
        this.usuarioAtual = gerenciadorDeDados.carregarUsuario(username);
        if (this.usuarioAtual == null) {
            this.usuarioAtual = new Usuario(username);
            JOptionPane.showMessageDialog(this,
                    "Bem-vindo, " + username + "! Um novo perfil foi criado.",
                    "Novo Usuário",
                    JOptionPane.INFORMATION_MESSAGE);
        } else {
             JOptionPane.showMessageDialog(this,
                    "Bem-vindo de volta, " + username + "!",
                    "Login com Sucesso",
                    JOptionPane.INFORMATION_MESSAGE);
        }
        
        // Configura o AppPanel com o usuário e mostra
        appPanel.setUsuario(this.usuarioAtual);
        cardLayout.show(mainPanel, "app");
    }
    
    public Usuario getUsuarioAtual() {
        return usuarioAtual;
    }
}