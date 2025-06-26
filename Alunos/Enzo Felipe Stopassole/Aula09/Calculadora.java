import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class Calculadora extends JFrame {
    private JTextField display;
    private String currentInput = "";
    private final Color BG_COLOR = new Color(45, 45, 45);
    private final Color BTN_COLOR = new Color(60, 60, 60);
    private final Color OPERATOR_COLOR = new Color(255, 149, 0);

    public Calculadora() {
        configurarJanela();
        criarDisplay();
        criarBotoes();
        setVisible(true);
    }

    private void configurarJanela() {
        setTitle("Calculadora Moderna");
        setSize(350, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(BG_COLOR);
        setLayout(new BorderLayout(10, 10));
    }

    private void criarDisplay() {
        display = new JTextField();
        display.setFont(new Font("Segoe UI", Font.BOLD, 40));
        display.setBackground(BG_COLOR);
        display.setForeground(Color.WHITE);
        display.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        display.setEditable(false);
        display.setHorizontalAlignment(JTextField.RIGHT);
        add(display, BorderLayout.NORTH);
    }

    private void criarBotoes() {
        JPanel painelBotoes = new JPanel(new GridLayout(5, 4, 10, 10));
        painelBotoes.setBackground(BG_COLOR);
        painelBotoes.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        String[][] botoes = {
            {"C", "(", ")", "÷"},
            {"7", "8", "9", "×"},
            {"4", "5", "6", "-"},
            {"1", "2", "3", "+"},
            {".", "0", "⌫", "="}
        };

        for (String[] linha : botoes) {
            for (String texto : linha) {
                JButton btn = criarBotao(texto);
                painelBotoes.add(btn);
            }
        }

        add(painelBotoes, BorderLayout.CENTER);
    }

    private JButton criarBotao(String texto) {
        JButton btn = new JButton(texto);
        btn.setFont(new Font("Segoe UI", Font.PLAIN, 24));
        btn.setFocusPainted(false);
        btn.setForeground(Color.WHITE);
        btn.setBackground(texto.matches("[0-9.]") ? BTN_COLOR : OPERATOR_COLOR);
        btn.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        if (texto.equals("=")) btn.setBackground(new Color(40, 140, 230));

        btn.addActionListener(e -> tratarClique(texto));
        
        btn.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                btn.setBackground(btn.getBackground().brighter());
            }

            public void mouseExited(MouseEvent e) {
                btn.setBackground(btn.getBackground().darker());
            }
        });

        return btn;
    }

    private void tratarClique(String comando) {
        switch (comando) {
            case "C":
                currentInput = "";
                break;
            case "⌫":
                if (!currentInput.isEmpty()) {
                    currentInput = currentInput.substring(0, currentInput.length() - 1);
                }
                break;
            case "=":
                calcular();
                break;
            default:
                if (currentInput.length() < 25) {
                    currentInput += comando;
                }
        }
        display.setText(currentInput);
    }

    private void calcular() {
        try {
            String expressao = currentInput
                .replace("×", "*")
                .replace("÷", "/")
                .replace(",", ".");

            ScriptEngine engine = new ScriptEngineManager().getEngineByName("JavaScript");
            Object resultado = engine.eval(expressao);

            if (resultado instanceof Double) {
                double valor = (Double) resultado;
                currentInput = String.format("%.6f", valor).replace(".", ",");
                currentInput = currentInput.replaceAll(",?0+$", "");
            } else {
                currentInput = resultado.toString();
            }
        } catch (ScriptException | ArithmeticException e) {
            currentInput = "Erro";
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                new Calculadora();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}