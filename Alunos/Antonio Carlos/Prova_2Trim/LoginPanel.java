package ui;

import javax.swing.*;
import java.awt.*;

public class LoginPanel extends JPanel {
    private MainFrame mainFrame;
    private JTextField usernameField;
    private JButton loginButton;

    public LoginPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        setLayout(new GridBagLayout());
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel label = new JLabel("Digite seu nome de usuário:");
        usernameField = new JTextField(20);
        loginButton = new JButton("Entrar");

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(label, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(usernameField, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(loginButton, gbc);

        loginButton.addActionListener(e -> attemptLogin());
        usernameField.addActionListener(e -> attemptLogin()); // Permite logar com Enter
    }
    
    private void attemptLogin() {
        String username = usernameField.getText().trim();
        if (!username.isEmpty()) {
            mainFrame.login(username);
        } else {
            JOptionPane.showMessageDialog(this,
                    "Por favor, digite um nome de usuário.",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}