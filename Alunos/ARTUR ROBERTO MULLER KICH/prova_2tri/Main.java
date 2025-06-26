package org.example;

import org.example.ui.SistemaSeriesUI;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.err.println("Não foi possível configurar a aparência do sistema: " + e.getMessage());
        }
        
        
        try {
            setDefaultFont(new Font("Segoe UI", Font.PLAIN, 12));
        } catch (Exception e) {
            System.err.println("Não foi possível configurar a fonte padrão: " + e.getMessage());
        }
        
        
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    
                    JFrame splashFrame = new JFrame("Carregando...");
                    JLabel splashLabel = new JLabel("Iniciando Sistema de Séries de TV...", JLabel.CENTER);
                    splashLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
                    splashFrame.add(splashLabel);
                    splashFrame.setSize(400, 100);
                    splashFrame.setLocationRelativeTo(null);
                    splashFrame.setUndecorated(true);
                    splashFrame.setVisible(true);
                    

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        // Ignora interrupção
                    }
                    

                    SwingUtilities.invokeLater(() -> {
                        try {
                            new SistemaSeriesUI();
                            splashFrame.dispose();
                        } catch (Exception e) {
                            splashFrame.dispose();
                            JOptionPane.showMessageDialog(null, 
                                    "Erro ao iniciar o sistema: " + e.getMessage(), 
                                    "Erro", JOptionPane.ERROR_MESSAGE);
                            e.printStackTrace();
                            System.exit(1);
                        }
                    });
                    
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, 
                            "Erro crítico ao iniciar o sistema: " + e.getMessage(), 
                            "Erro Fatal", JOptionPane.ERROR_MESSAGE);
                    e.printStackTrace();
                    System.exit(1);
                }
            }
        });
    }
    

    private static void setDefaultFont(Font font) {
        UIManager.put("Button.font", font);
        UIManager.put("ToggleButton.font", font);
        UIManager.put("RadioButton.font", font);
        UIManager.put("CheckBox.font", font);
        UIManager.put("ComboBox.font", font);
        UIManager.put("Label.font", font);
        UIManager.put("List.font", font);
        UIManager.put("MenuBar.font", font);
        UIManager.put("MenuItem.font", font);
        UIManager.put("RadioButtonMenuItem.font", font);
        UIManager.put("CheckBoxMenuItem.font", font);
        UIManager.put("Menu.font", font);
        UIManager.put("PopupMenu.font", font);
        UIManager.put("OptionPane.font", font);
        UIManager.put("Panel.font", font);
        UIManager.put("ProgressBar.font", font);
        UIManager.put("ScrollPane.font", font);
        UIManager.put("Viewport.font", font);
        UIManager.put("TabbedPane.font", font);
        UIManager.put("Table.font", font);
        UIManager.put("TableHeader.font", font);
        UIManager.put("TextField.font", font);
        UIManager.put("PasswordField.font", font);
        UIManager.put("TextArea.font", font);
        UIManager.put("TextPane.font", font);
        UIManager.put("EditorPane.font", font);
        UIManager.put("TitledBorder.font", font);
        UIManager.put("ToolBar.font", font);
        UIManager.put("ToolTip.font", font);
        UIManager.put("Tree.font", font);
    }
}